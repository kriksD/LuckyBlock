package kriks.luckyblock;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class playerJoin implements Listener {

    LuckyBlock plugin;

    playerJoin(LuckyBlock plugin){
        this.plugin = plugin;
    }

    @EventHandler
    void join(PlayerJoinEvent event){
        File filePlayers = new File(plugin.getDataFolder() + File.separator + "players.yml");
        FileConfiguration filePlayersFC = YamlConfiguration.loadConfiguration(filePlayers);
        List<String> allPlayers = filePlayersFC.getStringList("players");

        if (!allPlayers.contains(event.getPlayer().getName()) && plugin.propertiesGive.isGiveOnFirstJoin()){
            event.getPlayer().getInventory().addItem(itemStackLuckyBlock.createLuckyBlockItem(plugin.propertiesGive.getCountFJ()));
            allPlayers.add(event.getPlayer().getName());

            filePlayersFC.set("players", allPlayers);
            try {
                filePlayersFC.save(filePlayers);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
