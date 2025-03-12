package net.runelite.client.plugins.microbot.chatmessenger;

import net.runelite.client.ui.PluginPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class ChatMessengerPanel extends PluginPanel
{
    public ChatMessengerPanel(ChatMessengerPlugin plugin, ChatMessengerConfig config)
    {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Chat Messenger Configuración");
        add(titleLabel, BorderLayout.NORTH);

        JButton toggleButton = new JButton("Activar/Desactivar Mensajes");
        toggleButton.addActionListener(e -> plugin.toggleMessaging());
        add(toggleButton, BorderLayout.CENTER);
    }
}
