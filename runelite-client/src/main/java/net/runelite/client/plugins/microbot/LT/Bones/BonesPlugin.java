package net.runelite.client.plugins.microbot.LT.Bones;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;

@PluginDescriptor(
        name = PluginDescriptor.LT + "Bones Burier",
        description = "Entierra Big Bones automáticamente cerca de un banco",
        tags = {"bones", "bury", "prayer"},
        enabledByDefault = false
)
public class BonesPlugin extends Plugin {

    @Inject
    private BonesScript script;

    @Override
    protected void startUp() {
        script.run();
    }

    @Override
    protected void shutDown() {
        script.shutdown();
    }
}
