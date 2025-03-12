package net.runelite.client.plugins.microbot.chatmessenger;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("chatmessenger")
public interface ChatMessengerConfig extends Config
{
    @ConfigItem(
            keyName = "message",
            name = "Mensaje",
            description = "El mensaje que se enviará automáticamente"
    )
    default String message()
    {
        return "¡Hola, mundo!";
    }

    @ConfigItem(
            keyName = "chatMode",
            name = "Modo de Chat",
            description = "Selecciona entre 'All' o 'Channel' para el envío del mensaje"
    )
    default ChatMode chatMode()
    {
        return ChatMode.ALL;
    }

    @ConfigItem(
            keyName = "spamInterval",
            name = "Intervalo de Spam",
            description = "Tiempo en segundos entre cada mensaje"
    )
    default int spamInterval()
    {
        return 10; // Intervalo predeterminado de 10 segundos
    }
}
