package net.runelite.client.plugins.microbot.Pizza;

import com.google.inject.Provides;
import java.awt.AWTException;
import javax.inject.Inject;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.LT.Pizza.PizzaConfig;
import net.runelite.client.plugins.microbot.LT.Pizza.PizzaOverlay;
import net.runelite.client.plugins.microbot.LT.Pizza.PizzaScript;
import net.runelite.client.plugins.microbot.PluginConstants;
import net.runelite.client.plugins.microbot.agility.MicroAgilityPlugin;
import net.runelite.client.ui.overlay.OverlayManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PluginDescriptor(

        name = PluginConstants.LT + "Pizza",
        description = "Pizza Plugin",
        version = MicroAgilityPlugin.version,
        minClientVersion = "2.0.0",
        tags = {"Pizza", "microbot"},
        iconUrl = "",
        cardUrl = "",
        enabledByDefault = PluginConstants.DEFAULT_ENABLED,
        isExternal = PluginConstants.IS_EXTERNAL
)
public class PizzaPlugin extends Plugin {
    private static final Logger log = LoggerFactory.getLogger(PizzaPlugin.class);
    @Inject
    private net.runelite.client.plugins.microbot.LT.Pizza.PizzaConfig config;
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private PizzaOverlay exampleOverlay;
    @Inject
    PizzaScript PizzaScript;
    int ticks = 10;

    public PizzaPlugin() {
    }

    @Provides
    net.runelite.client.plugins.microbot.LT.Pizza.PizzaConfig provideConfig(ConfigManager configManager) {
        return (net.runelite.client.plugins.microbot.LT.Pizza.PizzaConfig)configManager.getConfig(PizzaConfig.class);
    }

    protected void startUp() throws AWTException {
        if (this.overlayManager != null) {
            this.overlayManager.add(this.exampleOverlay);
        }

        this.PizzaScript.run();
    }

    protected void shutDown() {
        this.PizzaScript.shutdown();
        this.overlayManager.remove(this.exampleOverlay);
    }

    @Subscribe
    public void onGameTick(GameTick tick) {
        if (this.ticks > 0) {
            --this.ticks;
        } else {
            this.ticks = 10;
        }

    }
}