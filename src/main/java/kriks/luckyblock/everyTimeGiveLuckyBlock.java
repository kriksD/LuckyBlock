package kriks.luckyblock;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class everyTimeGiveLuckyBlock {

    LuckyBlock plugin;

    everyTimeGiveLuckyBlock(LuckyBlock plugin){
        this.plugin = plugin;

        if (plugin.propertiesGive.isGiveEveryTime()){
            giveLuckyBlockEveryTimeForPlayers();
        }
    }

    private void giveLuckyBlockEveryTimeForPlayers(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, ()->{
            for (Player player : plugin.getServer().getOnlinePlayers()){
                player.getInventory().addItem(itemStackLuckyBlock.createLuckyBlockItem(plugin.propertiesGive.getCountET()));
            }
        }, 0, plugin.propertiesGive.getTimeET());
    }
}
