package net.runelite.client.plugins.microbot.zalcano;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.NpcID;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.GraphicsObjectCreated;
import net.runelite.api.events.NpcDespawned;
import net.runelite.api.events.NpcSpawned;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.util.player.Rs2Player;
import net.runelite.client.plugins.microbot.util.walker.Rs2Walker;

import javax.inject.Inject;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

@PluginDescriptor(
        name = PluginDescriptor.Bee + "Zalcano",
        description = "Soporte completo para Zalcano: daño, loot, mascota y rocas",
        enabledByDefault = false
)
public class ZalcanoPlugin extends Plugin
{
    @Inject
    private OverlayManager overlayManager;

    @Inject
    private ZalcanoPanel zalcanoPanel;

    @Inject
    private ZalcanoRocksOverlay rocksOverlay;

    @Inject
    private Client client;

    private int shieldDamage = 0;
    private int healthDamage = 0;

    private static final int ROCK_FALL_ID = 1727;

    private boolean zalcanoActive = false;
    private boolean eligibleForLoot = false;

    private final Set<WorldPoint> fallingRocks = new HashSet<>();
    private final Random random = new Random();

    // Historial
    private final List<Integer> shieldHistory = new ArrayList<>();
    private final List<Integer> healthHistory = new ArrayList<>();
    private final List<Boolean> lootHistory = new ArrayList<>();
    private final List<Boolean> smolcanoHistory = new ArrayList<>();

    private MouseListener panelMouseListener;

    @Provides
    ZalcanoConfig provideConfig(net.runelite.client.config.ConfigManager configManager)
    {
        return configManager.getConfig(ZalcanoConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(zalcanoPanel);
        overlayManager.add(rocksOverlay);

        // Registrar MouseListener para botón Reset
        panelMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zalcanoPanel.handleMouseClick(e);
            }
        };
        client.getCanvas().addMouseListener(panelMouseListener);
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(zalcanoPanel);
        overlayManager.remove(rocksOverlay);
        resetState();
        clearHistory();

        if (panelMouseListener != null)
        {
            client.getCanvas().removeMouseListener(panelMouseListener);
        }
    }

    private void resetState()
    {
        shieldDamage = 0;
        healthDamage = 0;
        eligibleForLoot = false;
        zalcanoActive = false;
        fallingRocks.clear();
    }

    private void clearHistory()
    {
        shieldHistory.clear();
        healthHistory.clear();
        lootHistory.clear();
        smolcanoHistory.clear();
    }

    @Subscribe
    public void onNpcSpawned(NpcSpawned event)
    {
        if (event.getNpc().getId() == NpcID.ZALCANO)
        {
            if (provideConfig(Microbot.getConfigManager()).enableLogging())
                Microbot.log("⚔️ Zalcano apareció");

            resetState();
            zalcanoActive = true;
        }
    }

    @Subscribe
    public void onNpcDespawned(NpcDespawned event)
    {
        if (event.getNpc().getId() == NpcID.ZALCANO)
        {
            zalcanoActive = false;

            boolean gotLoot = eligibleForLoot;
            boolean gotPet = false;

            if (eligibleForLoot)
            {
                Microbot.log("✅ Contribución suficiente: loot y chance de Smolcano.");
                gotPet = calculateSmolcanoChance();
            }
            else
            {
                Microbot.log("⚠️ No alcanzaste el daño mínimo, sin loot.");
            }

            shieldHistory.add(shieldDamage);
            healthHistory.add(healthDamage);
            lootHistory.add(gotLoot);
            smolcanoHistory.add(gotPet);

            resetState();
        }
    }

    @Subscribe
    public void onGraphicsObjectCreated(GraphicsObjectCreated event)
    {
        if (event.getGraphicsObject().getId() == ROCK_FALL_ID)
        {
            WorldPoint rockTile = WorldPoint.fromLocal(client, event.getGraphicsObject().getLocation());
            fallingRocks.add(rockTile);

            if (provideConfig(Microbot.getConfigManager()).alertRocks())
                Microbot.log("⚠️ Roca cayendo en: " + rockTile);
        }
    }

    @Subscribe
    public void onGameTick(GameTick tick)
    {
        if (!zalcanoActive) return;

        ZalcanoConfig config = provideConfig(Microbot.getConfigManager());
        int lootThreshold = config.lootThreshold();

        if ((shieldDamage + healthDamage) >= lootThreshold)
            eligibleForLoot = true;

        WorldPoint playerTile = Rs2Player.getWorldLocation();
        if (playerTile != null && fallingRocks.contains(playerTile) && config.autoMoveFromRocks())
        {
            Microbot.log("🚨 Estás bajo una roca, muévete YA!");
            Rs2Walker.walkFastCanvas(playerTile.dx(2));
        }
    }

    private boolean calculateSmolcanoChance()
    {
        int chance = random.nextInt(512) + 1;
        boolean gotPet = (chance == 1);
        if (gotPet)
            Microbot.log("🎉 ¡Felicidades! Smolcano pet drop conseguido!");
        else
            Microbot.log("💀 No salió Smolcano esta vez. (1/512 chance)");
        return gotPet;
    }

    // Getters y setters
    public int getShieldDamage() { return shieldDamage; }
    public int getHealthDamage() { return healthDamage; }
    public Set<WorldPoint> getFallingRocks() { return fallingRocks; }
    public void addShieldDamage(int dmg) { shieldDamage += dmg; }
    public void addHealthDamage(int dmg) { healthDamage += dmg; }
    public Client getClient() { return client; }

    public List<Integer> getShieldHistory() { return shieldHistory; }
    public List<Integer> getHealthHistory() { return healthHistory; }
    public List<Boolean> getLootHistory() { return lootHistory; }
    public List<Boolean> getSmolcanoHistory() { return smolcanoHistory; }
}
