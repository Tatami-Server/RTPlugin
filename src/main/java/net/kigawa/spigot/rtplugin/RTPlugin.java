package net.kigawa.spigot.rtplugin;

import net.kigawa.spigot.rtplugin.util.plugin.all.PluginBase;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class RTPlugin extends PluginBase {
    @Override
    public void addConfigDefault(FileConfiguration config) {

    }

    @Override
    public void onStart() {
        File commandsDir = new File(getDataFolder(), "commands");
        commandsDir.mkdirs();

        List<CommandData> dataList = new ArrayList<>();
        File[] files = commandsDir.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                fileName = fileName.substring(0, fileName.length() - 4);
                if (fileName.contains(":") && fileName.length() == 5) {
                    int hour = Integer.getInteger(fileName.substring(0, 2));
                    int min = Integer.getInteger(fileName.substring(3, 5));
                    try {
                        dataList.add(new CommandData(hour, min, Files.readAllLines(file.getAbsoluteFile().toPath())));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        new CommandExecutor(this, dataList);
    }


}

class CommandExecutor extends Timer {
    private final List<CommandData> dataList;

    public CommandExecutor(PluginBase plugin, List<CommandData> dataList) {
        super(plugin);
        this.dataList = new ArrayList<>(dataList);
    }

    @Override
    public void tickTimer() {

    }

    @Override
    public void secTimer() {
        Calendar calendar = Calendar.getInstance();
        for (CommandData data : dataList) {
            if (calendar.get(Calendar.HOUR) != data.getHour()) continue;
            if (calendar.get(Calendar.MINUTE) != data.getMin()) continue;
            if (calendar.get(Calendar.SECOND) != 0) continue;
            for (String command : data.getCommands()) {
                getPlugin().getServer().dispatchCommand(getPlugin().getServer().getConsoleSender(), command);
            }
        }
    }

    @Override
    public void minTimer() {

    }

    @Override
    public void hourTimer() {

    }
}
