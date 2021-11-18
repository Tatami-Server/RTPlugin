package net.kigawa.spigot.rtplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import net.kigawa.spigot.rtplugin.timer.GameTimer;
import net.kigawa.spigot.rtplugin.timer.RealTimer;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public final class RTPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        File commandsDir = new File(getDataFolder(), "commands");
        commandsDir.mkdirs();
        Bukkit.getPluginManager().registerEvents(this, this);

        List<CommandData> dataList = new ArrayList<>();
        List<String> tickCommands = new ArrayList<>();
        List<String> secCommands = new ArrayList<>();
        File[] files = commandsDir.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (!fileName.endsWith(".txt")) continue;
                fileName = fileName.substring(0, fileName.length() - 4);
                if (fileName.contains(";")) {
                    String[] time = fileName.split(";");
                    int hour = -1;
                    if (!time[0].equals("_")) {
                        System.out.println(time[0]);
                        hour = Integer.parseInt(time[0]);
                    }
                    int min = -1;
                    if (!time[1].equals("_")) {
                        System.out.println(time[1]);
                        min = Integer.parseInt(time[1]);
                    }
                    try {
                        dataList.add(new CommandData(hour, min, Files.readAllLines(file.getAbsoluteFile().toPath())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                if (fileName.equals("tick")) {
                    try {
                        tickCommands.addAll((Files.readAllLines(file.getAbsoluteFile().toPath())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                if (fileName.equals("sec") | fileName.equals("second")) {
                    try {
                        secCommands.addAll(Files.readAllLines(file.getAbsoluteFile().toPath()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        new CommandExecutor(this, dataList, tickCommands, secCommands);
    }

    @EventHandler
    private void ClickEvent(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();
        if (itemStack == null) return;
        if (!itemStack.getType().equals(Material.CARROT_ON_A_STICK)) return;
        Random random = new Random();
        Player player = event.getPlayer();
        Scoreboard scoreboard = player.getScoreboard();
        String playerName = player.getName();

        Objective objective = scoreboard.getObjective("score1");
        if (objective == null) return;
        objective.getScore(playerName).setScore(random.nextInt(9) + 1);

        objective = scoreboard.getObjective("score2");
        if (objective == null) return;
        objective.getScore(playerName).setScore(random.nextInt(9) + 1);

        objective = scoreboard.getObjective("score3");
        if (objective == null) return;
        objective.getScore(playerName).setScore(random.nextInt(9) + 1);
    }
}

class CommandExecutor extends RealTimer {
    private final List<CommandData> dataList;
    private final List<String> tickCommands;
    private final List<String> secCommands;
    private final Server server;
    private final ConsoleCommandSender sender;

    public CommandExecutor(JavaPlugin plugin, List<CommandData> dataList, List<String> tickCommands, List<String> secCommands) {
        super(plugin);
        this.dataList = dataList;
        this.tickCommands = tickCommands;
        this.secCommands = secCommands;
        server = plugin.getServer();
        sender = server.getConsoleSender();
    }

    @Override
    public void tickTimer() {
        if (tickCommands == null) return;
        for (String command : tickCommands) {
            server.dispatchCommand(sender, command);
        }
    }

    @Override
    public void secTimer() {
        Calendar calendar = Calendar.getInstance();
        if (dataList != null) {
            for (CommandData data : dataList) {
                if (calendar.get(Calendar.HOUR_OF_DAY) != data.getHour() && data.getHour() >= 0) continue;
                if (calendar.get(Calendar.MINUTE) != data.getMin() && data.getMin() >= 0) continue;
                if (calendar.get(Calendar.SECOND) != 0) continue;
                for (String command : data.getCommands()) {
                    server.dispatchCommand(sender, command);
                }
            }
        }
        if (secCommands == null) return;
        for (String command : secCommands) {
            server.dispatchCommand(sender, command);
        }
    }

    @Override
    public void minTimer() {

    }

    @Override
    public void hourTimer() {

    }
}
