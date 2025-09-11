package net.runelite.client.plugins.microbot.LT.barrows;

import net.runelite.api.gameval.ItemID;
import net.runelite.client.config.*;
import net.runelite.client.plugins.microbot.inventorysetups.InventorySetup;
import net.runelite.client.plugins.microbot.util.misc.Rs2Food;

@ConfigGroup("barrows")
@ConfigInformation("1. Ten una configuración de inventario llamada Barrows <br><br> 2. Objetos requeridos: pociones de oración o mezclas de polilla de luna (2), tabletas de teletransporte a Barrows o tabletas de teletransporte a la casa, comida, runas catalíticas (si usas hechizos de viento) y una pala.<br /><br /> 3. Hechizos: Viento: Explosión, Ola y Oleada. O báculos potenciados: admite cualquier tridente, cualquier cetro, cualquier báculo de cristal, el de Tumeken y el Sanguinesti. <br /><br /> Agradecimientos especiales a George por añadir la mazmorra de Barrows al caminante; y a Crannyy por probar el script.<br /><br /> Configuración por Crannyy")
public interface BarrowsConfig extends Config {
    @ConfigItem(
            keyName = "inventorySetup",
            name = "Inventory Setup",
            description = "Inventory Setup to use for Barrows",
            position = 0
    )
    default InventorySetup inventorySetup() { return null; }
    @ConfigItem(
            keyName = "Food",
            name = "Food",
            description = "type of food",
            position = 1
    )
    default Rs2Food food()
    {
        return Rs2Food.POTATO_WITH_CHEESE;
    }
    @ConfigItem(
            keyName = "targetFoodAmount",
            name = "Max Food Amount",
            description = "Max amount of food to withdraw from the bank.",
            position = 2
    )
    @Range(min = 1, max = 28)
    default int targetFoodAmount() {
        return 10;
    }

    @ConfigItem(
            keyName = "minFood",
            name = "Min Food",
            description = "Minimum amount of food to withdraw from the bank.",
            position = 3
    )
    @Range(min = 1, max = 28)
    default int minFood() {
        return 5;
    }

    @ConfigItem(
            keyName = "selectedPrayerRestoreType",
            name = "Prayer Restore Type:",
            description = "Between prayer potions, or moonlight moth mixes.",
            position = 4
    )
    default prayerRestoreType prayerRestoreType() {
        return prayerRestoreType.Prayer_Potion; // Default selection
    }

    enum prayerRestoreType {
        Prayer_Potion(ItemID._4DOSEPRAYERRESTORE, "Prayer potion(4)"),
        MoonlightMothMix(ItemID.HUNTER_MIX_MOONMOTH_2DOSE, "Moonlight moth mix (2)"),
        MoonlightMoth(ItemID.BUTTERFLY_JAR_MOONMOTH, "Moonlight moth");

        private final int id;
        private final String name;

        prayerRestoreType(int id, String name) {
            this.id = id;
            this.name = name;
        }


        public int getPrayerRestoreTypeID() {
            return id;
        }

        public String getPrayerRestoreTypeName() {
            return name;
        }

    }

    @ConfigItem(
            keyName = "targetPrayerPots",
            name = "Max Prayer Restore",
            description = "Max amount of prayer potions, or moonlight moth mixes to withdraw from the bank.",
            position = 5
    )
    @Range(min = 1, max = 20)
    default int targetPrayerPots() {
        return 8;
    }

    @ConfigItem(
            keyName = "minPrayerPots",
            name = "Min Prayer Restore",
            description = "Minimum amount of prayer potions, or moonlight moth mixes to withdraw from the bank.",
            position = 6
    )
    @Range(min = 1, max = 10)
    default int minPrayerPots() {
        return 4;
    }

    @ConfigItem(
            keyName = "targetForgottenBrew",
            name = "Max Forgotten Brews",
            description = "Max amount of forgotten brews to withdraw from the bank.",
            position = 7
    )
    @Range(min = 1, max = 5)
    default int targetForgottenBrew() {
        return 3;
    }

    @ConfigItem(
            keyName = "minForgottenBrew",
            name = "Min Forgotten Brews",
            description = "Minimum amount of forgotten brews to withdraw from the bank.",
            position = 8
    )
    @Range(min = 0, max = 5)
    default int minForgottenBrew() {
        return 1;
    }

    @ConfigItem(
            keyName = "selectedToBarrowsTPMethod",
            name = "Barrows TP Method",
            description = "Between using a barrows teleport tablet, or your POH portal.",
            position = 9
    )
    default selectedToBarrowsTPMethod selectedToBarrowsTPMethod() {
        return selectedToBarrowsTPMethod.Tablet; // Default selection
    }

    enum selectedToBarrowsTPMethod {
        Tablet(ItemID.TELETAB_BARROWS, "Barrows teleport"),
        POH(ItemID.POH_TABLET_TELEPORTTOHOUSE, "Teleport to house");

        private final int id;
        private final String name;

        selectedToBarrowsTPMethod(int id, String name) {
            this.id = id;
            this.name = name;
        }


        public int getToBarrowsTPMethodItemID() {
            return id;
        }

        public String getToBarrowsTPMethodItemName() {
            return name;
        }

    }

    @ConfigItem(
            keyName = "targetBarrowsTeleports",
            name = "Max Barrows Teleports",
            description = "Max amount of Barrows teleports to withdraw from the bank.",
            position = 10
    )
    @Range(min = 1, max = 10)
    default int targetBarrowsTeleports() {
        return 8;
    }

    @ConfigItem(
            keyName = "minBarrowsTeleports",
            name = "Min Barrows Teleports",
            description = "Minimum amount of Barrows teleports to withdraw from the bank.",
            position = 11
    )
    @Range(min = 1, max = 10)
    default int minBarrowsTeleports() {
        return 1;
    }

    @ConfigItem(
            keyName = "minRuneAmount",
            name = "Min Runes",
            description = "Minimum amount of runes before banking",
            position = 12
    )
    @Range(min = 50, max = 1000)
    default int minRuneAmount() {
        return 180;
    }

    @ConfigItem(
            keyName = "shouldGainRP",
            name = "Aim for 86+% rewards potential",
            description = "Should we gain additional RP other than the barrows brothers?",
            position = 13
    )
    default boolean shouldGainRP() {
        return false;
    }

    @ConfigItem(
            keyName = "shouldPrayAgainstWeakerBrothers",
            name = "Pray against Torag, Verac, and Guthans?",
            description = "Should we Pray against Torag, Verac, and Guthans?",
            position = 14
    )
    default boolean shouldPrayAgainstWeakerBrothers() {
        return true;
    }

}
