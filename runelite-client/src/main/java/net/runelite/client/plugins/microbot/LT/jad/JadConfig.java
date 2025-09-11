package net.runelite.client.plugins.microbot.LT.jad;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigInformation;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(JadConfig.configGroup)
@ConfigInformation("Este plugin cambiará la oración para los ataques de Jad y atacará a los sanadores. Soporta hasta 3 Jads")
public interface JadConfig extends Config {
    String configGroup = "micro-jadhelper";
    String shouldAttackHealers = "shouldAttackHealers";

    @ConfigItem(
            keyName = shouldAttackHealers,
            name = "Attack Healers",
            description = "Enable this setting to handle jad healers",
            position = 0
    )
    default boolean shouldAttackHealers() {
        return true;
    }
}
