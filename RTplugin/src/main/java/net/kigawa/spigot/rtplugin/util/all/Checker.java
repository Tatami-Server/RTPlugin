package net.kigawa.spigot.rtplugin.util.all;

public class Checker {

    private String str;
    private Type type;

    public Checker(String str, Type type) {
        this.str = str;
        this.type = type;
    }

    public boolean equals(Object o) {
        return ((HasContain) o).contain(this);
    }

    public Type getType(){
        return type;
    }

    public String getStr() {
        return str;
    }

    public enum Type {
        CONTAIN_STRING
    }
}
