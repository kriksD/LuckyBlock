package kriks.luckyblock;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class itemStackLuckyBlock {
    private final ItemStack luckyBlock;
    private final LuckyBlock plugin;

    itemStackLuckyBlock(LuckyBlock plugin){
        this.plugin = plugin;
        luckyBlock = createLuckyBlockItem(1);
    }

    void setCount(int count){
        luckyBlock.setAmount(count);
    }

    ItemStack toItemStack(){
        return luckyBlock;
    }

    static public ItemStack createLuckyBlockItem(int count){
        ItemStack item = new ItemStack(Material.WET_SPONGE, count);

        ItemMeta iMeta = item.getItemMeta();
        iMeta.displayName(Component.text(ChatColor.GOLD + "LuckyBlock"));
        ArrayList<Component> listLore = new ArrayList<>();
        listLore.add(Component.text("Set and destroy for random event."));
        iMeta.lore(listLore);

        item.setItemMeta(iMeta);
        return  item;
    }
}
