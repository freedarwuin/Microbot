package net.runelite.client.plugins.microbot.LT.mess;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigInformation;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.plugins.microbot.LT.mess.TheMessScript.Dish;

@ConfigGroup("the_mess")
@ConfigInformation("<b>-- ACERCA DEL PLUGIN THE MESS --</b><br /><br />" +
        "<b>Wiki:</b> https://oldschool.runescape.wiki/w/Mess<br /><br />" +
        "• Es básicamente Overcooked en OSRS<br />" +
        "• Perfecto para ironman que quieran subir cocina ya que no se necesitan ítems para obtener buena xp/h.<br />" +
        "• No hay recompensas más allá de la XP.<br /><br />" +
        "<b>-- REQUISITOS --</b><br />" +
        "• Servery Meat Pie (20 Cocina)<br />" +
        "• Servery Stew (25 Cocina)<br />" +
        "• Servery Pineapple Pizza (65 Cocina)<br /><br />" +
        "<b>FLUJO DEL PLUGIN:</b><br /><br />" +
        "• Si no estás en la zona, viajará al Mess Hall.<br />" +
        "• Al llegar, si el inventario no está vacío, lo guardará en el banco cercano.<br />" +
        "• Si todo está en orden, repetirá el ciclo de preparar platos y entregarlos.<br />" +
        "• Siempre que la apreciación del plato seleccionado esté por debajo del umbral configurado, cambiará de mundo hasta que mejore.<br />" +
        "(ten en cuenta que un umbral mayor a 33 puede causar un bucle de hops.)")
public interface TheMessConfig extends Config {
    @ConfigSection(
            name = "Configuración",
            description = "Ajustes del script en sí.",
            position = 0
    )
    String settingsSection = "settings";

    @ConfigItem(
            keyName = "dish",
            name = "Plato",
            description = "Selecciona el plato a cocinar, asegúrate de\n" +
                    "tener el nivel de cocina requerido mostrado arriba.",
            position = 0,
            section = settingsSection
    )
    default Dish dish() {
        return Dish.STEW;
    }

    @ConfigItem(
            keyName = "appreciation_threshold",
            name = "Apreciación mínima",
            description = "Porcentaje de apreciación requerido para seguir sirviendo.\n" +
                    "Si la apreciación está por debajo de este valor, el bot cambiará de mundo.",
            position = 1,
            section = settingsSection
    )
    default int appreciation_threshold() {
        return 29;
    }

    @ConfigItem(
            keyName = "debug_mode",
            name = "Modo Debug",
            description = "Activa el modo debug para el script.\n" +
                    "Útil para pruebas y desarrollo.",
            position = 2,
            section = settingsSection
    )
    default boolean debug_mode() {
        return false;
    }
}