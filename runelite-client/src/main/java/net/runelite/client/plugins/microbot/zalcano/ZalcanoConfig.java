package net.runelite.client.plugins.microbot.zalcano;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("zalcano")
public interface ZalcanoConfig extends Config
{
    @ConfigItem(
            keyName = "showOverlay",
            name = "Mostrar Panel de Zalcano",
            description = "Muestra el panel con información de daño y loot",
            position = 1
    )
    default boolean showOverlay() { return true; }

    @ConfigItem(
            keyName = "alertRocks",
            name = "Alertar caída de rocas",
            description = "Mostrar notificación cuando caen rocas sobre el jugador",
            position = 2
    )
    default boolean alertRocks() { return true; }

    @ConfigItem(
            keyName = "lootThreshold",
            name = "Umbral de Loot",
            description = "Cantidad mínima de daño para obtener loot y chance de Smolcano",
            position = 3
    )
    default int lootThreshold() { return 100; }

    @ConfigItem(
            keyName = "autoMoveFromRocks",
            name = "Mover automáticamente de rocas",
            description = "Mover al jugador automáticamente si está debajo de una roca",
            position = 4
    )
    default boolean autoMoveFromRocks() { return true; }

    @ConfigItem(
            keyName = "enableLogging",
            name = "Habilitar Logs",
            description = "Muestra logs en consola sobre Zalcano y las rocas",
            position = 5
    )
    default boolean enableLogging() { return true; }
}
