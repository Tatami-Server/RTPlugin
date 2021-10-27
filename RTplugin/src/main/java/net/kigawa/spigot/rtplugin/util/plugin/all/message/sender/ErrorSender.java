package net.kigawa.spigot.rtplugin.util.plugin.all.message.sender;


import net.kigawa.spigot.rtplugin.util.all.Util;
import net.kigawa.spigot.rtplugin.util.plugin.all.message.Messenger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ErrorSender extends Sender {
    public ErrorSender(String title, Player player) {
        super(player);
        player.sendMessage(ChatColor.RED + title);
    }

    public ErrorSender(String title, List<Player> senders) {
        super(Util.changeListType(senders, CommandSender.class));
        Messenger.sendMessage(senders, title);
    }

    public static String getString(String message) {
        return ChatColor.RED + "Error: " + message;
    }
}
