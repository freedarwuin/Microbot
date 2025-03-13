package net.runelite.client.plugins.microbot.chatmessenger;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("chatmessenger")
public interface ChatMessengerConfig extends Config
{
    @ConfigItem(
            keyName = "spamInterval",
            name = "Intervalo de Spam (segundos)",
            description = "Cada cuántos segundos se enviará el mensaje"
    )
    default int spamInterval()
    {
        return 10; // Valor por defecto: 10 segundos
    }

    @ConfigItem(
            keyName = "message",
            name = "Mensaje",
            description = "El mensaje que se enviará automáticamente"
    )
    default String message()
    {
        return "533 ferox rg now! - rune crossbow + chins o barrage con ancient staff";
    }

    @ConfigItem(
            keyName = "chatMode",
            name = "Modo de Chat",
            description = "Enviar a ALL o CHANNEL"
    )
    default ChatMode chatMode()
    {
        return ChatMode.ALL;
    }
}
