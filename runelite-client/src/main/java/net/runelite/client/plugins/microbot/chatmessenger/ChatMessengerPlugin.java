package net.runelite.client.plugins.microbot.chatmessenger;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
        name = "Chat Messenger",
        description = "Envía mensajes al chat con la opción de alternar entre 'all' y 'channel'.",
        tags = {"chat", "messenger", "spam"}
)
public class ChatMessengerPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private ChatMessengerConfig config;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private ClientToolbar clientToolbar;

    private NavigationButton navButton;
    private boolean sendMessage = false;
    private int tickCounter = 0;

    @Provides
    ChatMessengerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ChatMessengerConfig.class);
    }

    @Override
    protected void startUp() throws Exception
    {
        navButton = NavigationButton.builder()
                .tooltip("Chat Messenger")
                .priority(5)
                .panel(new ChatMessengerPanel(this, config))
                .build();

        clientToolbar.addNavigation(navButton);
        log.info("Chat Messenger iniciado.");
    }

    @Override
    protected void shutDown() throws Exception
    {
        clientToolbar.removeNavigation(navButton);
        log.info("Chat Messenger detenido.");
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        if (sendMessage)
        {
            int intervalTicks = config.spamInterval() * 1000 / 600; // Convierte segundos a ticks (~0.6s por tick)
            if (tickCounter >= intervalTicks)
            {
                String message = config.message();
                if (message != null && !message.isEmpty())
                {
                    switch (config.chatMode())
                    {
                        case ALL:
                            client.runScript(5557, message);
                            break;
                        case CHANNEL:
                            client.runScript(5557, "/" + message);
                            break;
                    }
                }
                tickCounter = 0;
            }
            tickCounter++;
        }
    }

    public void toggleMessaging()
    {
        sendMessage = !sendMessage;
        log.info("Chat Messenger " + (sendMessage ? "Activado" : "Desactivado"));
    }
}
