package net.runelite.client.plugins.microbot.moons;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.moons.enums.State;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
        name = PluginDescriptor.LT + "Moons of Peril",
        description = "Moons of Peril bot for blood moon only",
        tags = {"microbot", "Moons of Peril", "boss"},
        enabledByDefault = false
)
@Slf4j
public class MoonsPlugin extends Plugin {
    @Inject
    private MoonsConfig config;
    @Provides
    MoonsConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(MoonsConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;
    @Inject
    private MoonsOverlay moonsOverlay;
    @Inject
    MoonsScript moonsScript;

    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(moonsOverlay);
        }
        MoonsScript.state = State.CHAMBER;
        moonsScript.run(config);
    }

    protected void shutDown() {
        moonsScript.shutdown();
        overlayManager.remove(moonsOverlay);
    }
}
