package net.runelite.client.plugins.microbot.bee.chaosaltar;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("chaosaltar")
public interface ChaosAltarConfig extends Config {

    @ConfigItem(
            keyName = "pluginDescription",
            name = "How to Use",
            description = "Best practices for using Chaos Altar plugin",
            position = 1
    )
    default String pluginDescription() {
        return "Para mejores resultados:\n"
                + "- Activar Player Monitor en MODO_LITE\n"
                + "- Habilitar AutoLogin\n"
                + "- Mantener Amuletos Ardientes y huesos de dragón en el banco\n"
                + "- SOLO FUNCIONA CON Huesos de Dragón\n"
                + "- Si tienes un personaje alternativo, considera teletransportarte primero al Lava Maze para distraer a los PKers o bots que pueden atacar automáticamente cuando te teletransportas\n"
                + "(Nota: Microbot actualmente no puede cerrar sesión lo suficientemente rápido cuando alguien te espera después del teletransporte)";
    }

    @ConfigItem(
            keyName = "f2pHop",
            name = "Enable F2P Hop",
            description = "Hops to F2P worlds and runs to the altar instead of using Burning Amulet. (WIP - non-functional)"
    )
    default boolean f2pHop() {
        return false;
    }

    @ConfigItem(
            keyName = "Boneyard",
            name = "Enable Boneyard Mode",
            description = "Collects bones from boneyard and uses them on chaos altar(WIP - non-functional)"
    )
    default boolean boneYardMode() {
        return false;
    }

    @ConfigItem(
            keyName = "Fast Bones Offering",
            name = "Offer Bones Fast",
            description = "Uses the bones on the altar quickly (more apm)"
    )
    default boolean giveBonesFast() {
        return false;
    }

}
