package net.kigawa.spigot.rtplugin;

import java.util.List;

public class CommandData {
    private final int hour;
    private final int min;
    private final List<String> commands;

    public CommandData(int hour, int min, List<String> commands) {
        this.hour = hour;
        this.min = min;
        this.commands = commands;
    }

    public int getMin() {
        return min;
    }

    public int getHour() {
        return hour;
    }

    public List<String> getCommands() {
        return commands;
    }
}
