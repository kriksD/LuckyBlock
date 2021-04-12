package kriks.luckyblock;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class LuckyBlock extends JavaPlugin {

    propertiesForGive propertiesGive;

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("getluckyblock")).setExecutor(new commGetLuckyBlock(this));
        Bukkit.getPluginManager().registerEvents(new useLuckyBlock(this), this);
        Bukkit.getPluginManager().registerEvents(new playerJoin(this), this);

        propertiesGive = new propertiesForGive(this);
        everyTimeGiveLuckyBlock etGiveLuckyBlock = new everyTimeGiveLuckyBlock(this);

        getLogger().info(getConfig().getString("messages.OnEnable"));
    }

    @Override
    public void onDisable() {
        getLogger().info(getConfig().getString("messages.OnDisabled"));
    }
}
