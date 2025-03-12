package net.runelite.client.plugins.microbot.chatmessenger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class ChatMessengerOverlay extends Overlay
{
    private final ChatMessengerPlugin plugin;
    private final ChatMessengerConfig config;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public ChatMessengerOverlay(ChatMessengerPlugin plugin, ChatMessengerConfig config)
    {
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.TOP_LEFT);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        panelComponent.getChildren().clear();
        panelComponent.getChildren().add(TitleComponent.builder()
                .text("Chat Messenger")
                .color(Color.WHITE)
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Mensaje:")
                .right(config.message())
                .build());

        panelComponent.getChildren().add(LineComponent.builder()
                .left("Modo:")
                .right(config.chatMode().name())
                .build());

        return panelComponent.render(graphics);
    }
}
