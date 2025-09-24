package net.runelite.client.plugins.microbot.Blackchinchompas.scripts;

import net.runelite.api.Player;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.breakhandler.BreakHandlerScript;
import net.runelite.client.plugins.microbot.Blackchinchompas.AutoHunterConfig;
import net.runelite.client.plugins.microbot.util.gameobject.Rs2GameObject;
import net.runelite.client.plugins.microbot.util.grounditem.Rs2GroundItem;
import net.runelite.client.plugins.microbot.util.inventory.Rs2Inventory;
import net.runelite.client.plugins.microbot.util.player.Rs2Player;
import net.runelite.client.plugins.microbot.util.walker.Rs2Walker;
import net.runelite.api.ItemID;
import net.runelite.api.ObjectID;

import java.util.*;
import java.util.concurrent.TimeUnit;

enum State {
    IDLE,
    CATCHING,
    DROPPING,
    LAYING,
    ESCAPING
}

public class AutoChinScript extends Script {

    public static final String version = "1.4.0-WildySafe";

    private boolean oneRun = false;
    private List<WorldPoint> boxtiles = new ArrayList<>();
    private State currentState = State.IDLE;

    private final List<Integer> trapIds = Arrays.asList(
            ItemID.BOX_TRAP,
            ObjectID.BOX_TRAP,
            ObjectID.BOX_TRAP_9385,
            ObjectID.BOX_TRAP_9380,
            ObjectID.SHAKING_BOX_9384,
            ObjectID.SHAKING_BOX_9383,
            ObjectID.SHAKING_BOX_9382,
            ObjectID.SHAKING_BOX
    );

    private final List<Integer> shakingTrapIds = Arrays.asList(
            ObjectID.SHAKING_BOX_9384,
            ObjectID.SHAKING_BOX_9383,
            ObjectID.SHAKING_BOX_9382
    );

    private long lastEscapeTime = 0;
    private final int escapeCooldownMs = 10000;
    private final WorldPoint SAFE_TILE = new WorldPoint(3088, 3520, 0);

    public boolean run(AutoHunterConfig config) {
        Microbot.enableAutoRunOn = false;

        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn() || !super.run()) return;

                switch (currentState) {
                    case IDLE:
                        handleIdleState();
                        break;
                    case DROPPING:
                        handleDroppingState(config);
                        break;
                    case CATCHING:
                        handleCatchingState(config);
                        break;
                    case LAYING:
                        handleLayingState(config);
                        break;
                    case ESCAPING:
                        handleEscapeState();
                        break;
                }

            } catch (Exception ex) {
                Microbot.logStackTrace(this.getClass().getSimpleName(), ex);
                currentState = State.IDLE;
            }
        }, 0, 600, TimeUnit.MILLISECONDS);
        return true;
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }

    private boolean detectPKers() {
        long now = System.currentTimeMillis();
        if (now - lastEscapeTime < escapeCooldownMs) return false;

        List<Player> players = Rs2Player.getPlayers();
        for (Player p : players) {
            if (!p.equals(Rs2Player.getLocalPlayer()) &&
                    p.getCombatLevel() > 3 &&
                    Math.abs(p.getCombatLevel() - Rs2Player.getLocalPlayer().getCombatLevel()) <= 15) {
                if (p.getName() != null) { // Detecta cualquier jugador
                    lastEscapeTime = now;
                    return true;
                }
            }
        }
        return false;
    }

    private void handleIdleState() {
        if (detectPKers()) {
            Microbot.log("⚠️ Jugador hostil detectado, escapando...");
            currentState = State.ESCAPING;
            return;
        }

        if (Rs2Inventory.getEmptySlots() <= 1 && Rs2Inventory.contains(ItemID.BLACK_CHINCHOMPA)) {
            releaseChinchompas();
            currentState = State.DROPPING;
            return;
        }

        for (int trapId : shakingTrapIds) {
            if (Rs2GameObject.interact(trapId, "reset", 4)) {
                currentState = State.CATCHING;
                return;
            }
        }

        if (Rs2GameObject.interact(ObjectID.BOX_TRAP_9385, "reset", 4)) {
            currentState = State.CATCHING;
            return;
        }

        if (Rs2GroundItem.interact(ItemID.BOX_TRAP, "lay", 4)) {
            currentState = State.LAYING;
        }
    }

    private void handleDroppingState(AutoHunterConfig config) {
        sleep(config.minSleepAfterLay(), config.maxSleepAfterLay());
        currentState = State.IDLE;
    }

    private void handleCatchingState(AutoHunterConfig config) {
        sleep(config.minSleepAfterCatch(), config.maxSleepAfterCatch());
        currentState = State.IDLE;
    }

    private void handleLayingState(AutoHunterConfig config) {
        sleep(config.minSleepAfterLay(), config.maxSleepAfterLay());
        currentState = State.IDLE;
    }

    private void handleEscapeState() {
        Microbot.log("🏃 Corriendo a zona segura...");
        Rs2Walker.walkTo(SAFE_TILE);
        sleep(3000, 5000);
        currentState = State.IDLE;
    }

    private void releaseChinchompas() {
        int tries = 0;
        while (Rs2Inventory.contains(ItemID.BLACK_CHINCHOMPA) && tries < 10) {
            Rs2Inventory.interact(ItemID.BLACK_CHINCHOMPA, "Release");
            sleep(400, 800);
            tries++;
        }
    }

    // Métodos de break y reemplazo de traps
    public void handleBreaks() {
        int secondsUntilBreak = BreakHandlerScript.breakIn;

        if (secondsUntilBreak > 61 && secondsUntilBreak < 200) {
            boxtiles.clear();
        }

        if (secondsUntilBreak > 0 && secondsUntilBreak <= 60) {
            saveTrapTiles();
            dismantleNearbyTraps();
            oneRun = true;
        }

        if (secondsUntilBreak > 60 && oneRun) {
            replaceTraps();
            oneRun = false;
        }
    }

    private void saveTrapTiles() {
        for (int trapId : trapIds) {
            List<?> gameObjects = Rs2GameObject.getGameObjects(obj -> obj.getId() == trapId);
            if (gameObjects != null) {
                for (Object obj : gameObjects) {
                    WorldPoint location = ((net.runelite.api.GameObject) obj).getWorldLocation();
                    if (Rs2Player.getWorldLocation().distanceTo(location) <= 5 && !boxtiles.contains(location)) {
                        boxtiles.add(location);
                    }
                }
            }
        }
    }

    private void dismantleNearbyTraps() {
        for (WorldPoint tile : boxtiles) {
            Object trap = Rs2GameObject.getGameObject(tile);
            if (trap != null && Rs2Player.getWorldLocation().distanceTo(tile) <= 5) {
                boolean success = Rs2GameObject.interact(tile, "Dismantle") ||
                        Rs2GameObject.interact(tile, "Reset");
                if (success) {
                    sleep(1000, 2000);
                }
            }
        }
    }

    private void replaceTraps() {
        for (WorldPoint tile : boxtiles) {
            if (Rs2GameObject.getGameObject(tile) == null) {
                Rs2Walker.walkTo(tile, 0);
                sleep(1000, 2000);

                int tries = 0;
                while (Rs2GameObject.getGameObject(tile) == null && tries < 3) {
                    if (!Rs2GroundItem.exists("Box trap", 6)) {
                        if (Rs2Inventory.contains("Box trap")) {
                            Rs2Inventory.interact("Box trap", "Lay");
                        }
                    } else {
                        Rs2GroundItem.take("Box trap", 6);
                    }
                    sleep(2000, 4000);
                    tries++;
                }
            }
        }
    }
}
