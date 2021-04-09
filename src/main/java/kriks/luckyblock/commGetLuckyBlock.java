package kriks.luckyblock;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class commGetLuckyBlock implements CommandExecutor {

    LuckyBlock plugin;

    commGetLuckyBlock(LuckyBlock plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
                             @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player){
            int count = 1;
            if (args.length == 1){
                try{
                    if (args[0].matches("[-+]?\\d+")){
                        count = Integer.parseInt(args[0]);
                    } else {
                        sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.CommandNotNumeric")));
                        return false;
                    }

                } catch (Exception e) {
                    plugin.getLogger().severe(plugin.getConfig().getString("messages.ExceptionMessage") + e.getMessage());
                }
            } else if (args.length > 1){
                sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.CommandManyArgs")));
                return false;
            }

            ((Player) sender).getInventory().addItem(itemStackLuckyBlock.createLuckyBlockItem(count));
            sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.CommandIsSuccess")));
            return true;
        }
        sender.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.CommandError")));
        return false;
    }
}
