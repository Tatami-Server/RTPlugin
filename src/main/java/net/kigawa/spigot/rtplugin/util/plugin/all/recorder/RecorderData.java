package net.kigawa.spigot.rtplugin.util.plugin.all.recorder;

import net.kigawa.spigot.rtplugin.util.yaml.YamlData;

public class RecorderData extends YamlData {
    String name;

    public RecorderData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
