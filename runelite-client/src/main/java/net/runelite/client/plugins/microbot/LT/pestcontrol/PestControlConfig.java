package net.runelite.client.plugins.microbot.LT.pestcontrol;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("pestcontrol")
public interface PestControlConfig extends Config {
    @ConfigItem(
            keyName = "guide",
            name = "How to use",
            description = "How to use this plugin",
            position = 1
    )
    default String GUIDE() {
        return "Empieza cerca de un barco de tu nivel de combate";
    }

    @ConfigItem(
            keyName = "NPC Priority 1",
            name = "Prioridad de NPC 1",
            description = "What npc to attack as first option",
            position = 2
    )
    default PestControlNpc Priority1() {
        return PestControlNpc.PORTAL;
    }
    @ConfigItem(
            keyName = "NPC Priority 2",
            name = "Prioridad de NPC 2",
            description = "What npc to attack as second option",
            position = 3
    )
    default PestControlNpc Priority2() {
        return PestControlNpc.SPINNER;
    }
    @ConfigItem(
            keyName = "NPC Priority 3",
            name = "Prioridad de NPC 3",
            description = "What npc to attack as third option",
            position = 4
    )
    default PestControlNpc Priority3() {
        return PestControlNpc.BRAWLER;
    }

    @ConfigItem(
            keyName = "Alch in boat",
            name = "Alch mientras esperas",
            description = "Alch while waiting",
            position = 5
    )
    default boolean alchInBoat() {
        return false;
    }

    @ConfigItem(
            keyName = "itemToAlch",
            name = "Item para alch",
            description = "Item to alch",
            position = 6
    )
    default String alchItem() {
        return "";
    }

    @ConfigItem(
            keyName = "QuickPrayer",
            name = "Enable QuickPrayer",
            description = "Enables quick prayer",
            position = 7
    )
    default boolean quickPrayer() {
        return false;
    }

    @ConfigItem(
            keyName = "Special Attack",
            name = "Use Special Attack on %",
            description = "What percentage to use Special Attack",
            position = 8
    )
    default int specialAttackPercentage() {
        return 100;
    }
}
