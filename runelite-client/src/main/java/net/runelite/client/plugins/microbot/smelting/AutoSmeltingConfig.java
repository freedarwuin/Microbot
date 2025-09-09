package net.runelite.client.plugins.microbot.smelting;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.plugins.microbot.smelting.enums.Bars;

@ConfigGroup("Smithing")
public interface AutoSmeltingConfig extends Config {
    @ConfigItem(
            keyName = "guide",
            name = "How to use",
            description = "How to use this plugin",
            position = 0,
            section = generalSection
    )
    default String GUIDE() {
        return "Selecciona el tipo de lingote que quieres fundir y permanece cerca de cualquier horno.\n" +
                "Necesitas suficiente mineral en tu banco para llenar tu inventario, por ejemplo: 14 de estaño y 14 de cobre o 9 de hierro y 18 de carbón.\n";
    }

    @ConfigSection(
            name = "General",
            description = "General",
            position = 0
    )
    String generalSection = "general";

    @ConfigItem(
            keyName = "Bar",
            name = "Bar",
            description = "Choose the bar",
            position = 0,
            section = generalSection
    )
    default Bars SELECTED_BAR_TYPE()
    {
        return Bars.BRONZE;
    }
}