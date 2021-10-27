package net.kigawa.spigot.rtplugin.util.plugin.all.command;

public class EqualsCommand {
    String command;
    public EqualsCommand(String command){
        this.command=command;
    }
    @Override
    public  boolean equals(Object o){
        return  ((Command)o).getName().equals(command);
    }
}
