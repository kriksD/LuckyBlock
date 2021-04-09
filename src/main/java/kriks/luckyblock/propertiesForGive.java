package kriks.luckyblock;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class propertiesForGive {
    private boolean giveEveryTime;
    private long timeET;
    private int countET;
    private boolean giveOnFirstJoin;
    private int countFJ;

    private final LuckyBlock plugin;

    propertiesForGive(LuckyBlock plugin){
        this.plugin = plugin;
        getConfigData();
    }

    private void getConfigData(){
        createDefaultConfigWhenNonExist();
        FileConfiguration config = plugin.getConfig();
        giveEveryTime = config.getBoolean("propertiesForGive.giveEveryTime");
        timeET = config.getInt("propertiesForGive.timeET");
        countET = config.getInt("propertiesForGive.countET");
        giveOnFirstJoin = config.getBoolean("propertiesForGive.giveOnFirstJoin");
        countFJ = config.getInt("propertiesForGive.countFJ");
    }

    private void createDefaultConfigWhenNonExist(){
        File configFile = new File(plugin.getDataFolder() + File.separator + "config.yml");
        if (!configFile.exists()){
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveDefaultConfig();
            plugin.saveConfig();
        }
    }

    public boolean isGiveEveryTime() {
        return giveEveryTime;
    }

    public long getTimeET() {
        return timeET;
    }

    public int getCountET() {
        return countET;
    }

    public boolean isGiveOnFirstJoin() {
        return giveOnFirstJoin;
    }

    public int getCountFJ() {
        return countFJ;
    }
}
