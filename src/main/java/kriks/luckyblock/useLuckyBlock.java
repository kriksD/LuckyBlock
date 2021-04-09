package kriks.luckyblock;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class useLuckyBlock implements Listener {

    LuckyBlock plugin;

    useLuckyBlock(LuckyBlock plugin){
        this.plugin = plugin;
    }

    @EventHandler
    void placeBlockAlt(BlockPlaceEvent event){
        Player playerE = event.getPlayer();

        if (playerE.getInventory().getItemInMainHand().getItemMeta().lore() == null) return;

        if (event.getBlock().getType() == Material.WET_SPONGE && Objects.requireNonNull(playerE.getInventory().getItemInMainHand().getItemMeta().lore()).toString().contains("Set and destroy for random event.")){
            addBlockToFile(event.getBlock());
        }
    }

    private void addBlockToFile(Block block){
        File fileBlocks = new File(plugin.getDataFolder() + File.separator + "blocks.yml");
        FileConfiguration fileBlocksFC = YamlConfiguration.loadConfiguration(fileBlocks);
        List<String> allBlocks = fileBlocksFC.getStringList("blocks");

        allBlocks.add(block.getX() + " " + block.getY() +  " " + block.getZ() + 1); // чогось по z воно записує на один блок в мінус

        fileBlocksFC.set("blocks", allBlocks);
        try {
            fileBlocksFC.save(fileBlocks);
        } catch (IOException e) {
            plugin.getLogger().severe(plugin.getConfig().getString("messages.ExceptionMessage") + e.getMessage());
        }
    }

    @EventHandler
    void destroyBlock(BlockBreakEvent event){
        Block LBlock = event.getBlock();
        int indexOfBlock = getIndexOfBlock(LBlock);
        if (indexOfBlock != -1){
            event.setDropItems(false);
            startRandomEvent(LBlock);
            removeBlockFromFile(indexOfBlock);
        }
    }

    private int getIndexOfBlock(Block block){
        File fileBlocks = new File(plugin.getDataFolder() + File.separator + "blocks.yml");
        FileConfiguration fileBlocksFC = YamlConfiguration.loadConfiguration(fileBlocks);
        List<String> allBlocks = fileBlocksFC.getStringList("blocks");

        return allBlocks.indexOf(block.getX() + " " + block.getY() +  " " + block.getZ() + 1);
    }

    private void removeBlockFromFile(int indexOfBlock){
        File fileBlocks = new File(plugin.getDataFolder() + File.separator + "blocks.yml");
        FileConfiguration fileBlocksFC = YamlConfiguration.loadConfiguration(fileBlocks);
        List<String> allBlocks = fileBlocksFC.getStringList("blocks");

        allBlocks.remove(indexOfBlock);

        fileBlocksFC.set("blocks", allBlocks);
        try {
            fileBlocksFC.save(fileBlocks);
        } catch (IOException e) {
            plugin.getLogger().severe(plugin.getConfig().getString("messages.ExceptionMessage") + e.getMessage());
        }
    }

    private void startRandomEvent(Block on){
        Random rand = new Random();
        int randomEventNum = rand.nextInt(128);

        Location onLocFix = new Location(on.getWorld(), on.getX() + 0.5, on.getY(), on.getZ() + 0.5);
        Location onLocFixM = new Location(on.getWorld(), on.getX() + 0.5, on.getY() + 50, on.getZ() + 0.5);
        Location onLocFixS = new Location(on.getWorld(), on.getX() + 1, on.getY(), on.getZ() + 1);

        switch (randomEventNum){
            case 0:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.DIAMOND, rand.nextInt(4) + 1));
                break;
            case 1:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.IRON_INGOT, rand.nextInt(8) + 1));
                break;
            case 2:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.GOLD_INGOT, rand.nextInt(6) + 1));
                break;
            case 3:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.EMERALD, rand.nextInt(4) + 1));
                break;
            case 4:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.LAPIS_LAZULI, rand.nextInt(8) + 1));
                break;
            case 5:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.REDSTONE, rand.nextInt(8) + 1));
                break;
            case 6:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.COAL, rand.nextInt(8) + 1));
                break;
            case 7:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.GOLDEN_APPLE));
                break;
            case 8:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.BREAD, rand.nextInt(8) + 1));
                break;
            case 9:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.EXPERIENCE_BOTTLE, rand.nextInt(8) + 1));
                break;
            case 10:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.COOKED_BEEF, rand.nextInt(8) + 1));
                break;
            case 11:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.SAND, rand.nextInt(16) + 1));
                break;
            case 12:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.GRAVEL, rand.nextInt(16) + 1));
                break;
            case 13:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.CACTUS, rand.nextInt(8) + 1));
                break;
            case 14:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.CARROT, rand.nextInt(4) + 1));
                break;
            case 15:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.POTATO, rand.nextInt(4) + 1));
                break;
            case 16:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.WHEAT_SEEDS, rand.nextInt(4) + 1));
                break;
            case 17:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.BEETROOT_SEEDS, rand.nextInt(4) + 1));
                break;
            case 18:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.SUGAR_CANE, rand.nextInt(4) + 1));
                break;
            case 19:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.EGG, rand.nextInt(8) + 1));
                break;
            case 20:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.BAMBOO, rand.nextInt(4) + 1));
                break;
            case 21:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.GRASS_BLOCK, rand.nextInt(4) + 1));
                break;
            case 22:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.BONE, rand.nextInt(8) + 1));
                break;
            case 23:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.STONE_AXE));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.STONE_HOE));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.STONE_PICKAXE));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.STONE_SHOVEL));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.STONE_SWORD));
                break;
            case 24:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.WOODEN_AXE));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.WOODEN_HOE));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.WOODEN_PICKAXE));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.WOODEN_SHOVEL));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.WOODEN_SWORD));
                break;
            case 25:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.LEATHER_BOOTS));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.LEATHER_CHESTPLATE));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.LEATHER_HELMET));
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.LEATHER_LEGGINGS));
                break;
            case 26:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.DIAMOND_SWORD));
                break;
            case 27:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.NETHERITE_SWORD));
                break;
            case 28:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.TRIDENT));
                break;
            case 29:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.GOLDEN_SWORD));
                break;
            case 30:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.IRON_SWORD));
                break;
            case 31:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.BOW));
                break;
            case 32:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.ARROW, rand.nextInt(8) + 1));
                break;
            case 33:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.TORCH, rand.nextInt(8) + 1));
                break;
            case 34:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.ACACIA_SAPLING, rand.nextInt(2) + 1));
                break;
            case 35:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.SPRUCE_SAPLING, rand.nextInt(2) + 1));
                break;
            case 36:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.BIRCH_SAPLING, rand.nextInt(2) + 1));
                break;
            case 37:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.DARK_OAK_SAPLING, 4));
                break;
            case 38:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.JUNGLE_SAPLING, rand.nextInt(2) + 1));
                break;
            case 39:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.OAK_SAPLING, rand.nextInt(2) + 1));
                break;
            case 40:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.OAK_LOG, rand.nextInt(16) + 1));
                break;
            case 41:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.TURTLE_EGG, rand.nextInt(2) + 1));
                break;
            case 42:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.HEART_OF_THE_SEA));
                break;
            case 43:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.NAUTILUS_SHELL, rand.nextInt(2) + 1));
                break;
            case 44:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.CHORUS_FLOWER, rand.nextInt(2) + 1));
                break;
            case 45:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.END_STONE, rand.nextInt(8) + 1));
                break;
            case 46:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.SOUL_SAND, rand.nextInt(8) + 1));
                break;
            case 47:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.NETHER_WART, rand.nextInt(4) + 1));
                break;
            case 48:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.LAVA_BUCKET));
                break;
            case 49:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.WATER_BUCKET));
                break;
            case 50:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.TROPICAL_FISH_BUCKET));
                break;
            case 51:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.COCOA_BEANS, rand.nextInt(4) + 1));
                break;
            case 52:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.ENDER_PEARL, rand.nextInt(2) + 1));
                break;
            case 53:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.CLAY_BALL, rand.nextInt(16) + 1));
                break;
            case 54:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.MILK_BUCKET));
                break;
            case 55:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.OAK_PLANKS, rand.nextInt(16) + 1));
                break;
            case 56:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.WITHER_SKELETON_SKULL, rand.nextInt(3) + 1));
                break;
            case 57:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.QUARTZ, rand.nextInt(8) + 1));
                break;
            case 58:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.FIREWORK_ROCKET, rand.nextInt(8) + 1));
                break;
            case 59:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.ELYTRA));
                break;
            case 60:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.SHULKER_SHELL, rand.nextInt(2) + 1));
                break;
            case 61:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
                break;
            case 62:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.SWEET_BERRIES, rand.nextInt(4) + 1));
                break;
            case 63:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.FISHING_ROD));
                break;
            case 64:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.LEAD, rand.nextInt(2) + 1));
                break;
            case 65:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.NAME_TAG, rand.nextInt(2) + 1));
                break;
            case 66:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.SCUTE, rand.nextInt(5) + 1));
                break;
            case 67:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.NETHERITE_SCRAP, rand.nextInt(2) + 1));
                break;
            case 68:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.FLINT_AND_STEEL));
                break;
            case 69:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.SHIELD));
                break;
            case 70:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.CROSSBOW));
                break;
            case 71:
                on.getWorld().dropItemNaturally(on.getLocation(), new ItemStack(Material.TOTEM_OF_UNDYING));
                break;
            case 72:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.SEA_PICKLE, rand.nextInt(4) + 1));
                break;
            case 73:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.KELP, rand.nextInt(4) + 1));
                break;
            case 74:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.BROWN_MUSHROOM, rand.nextInt(4) + 1));
                break;
            case 75:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.RED_MUSHROOM, rand.nextInt(4) + 1));
                break;
            case 76:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.WARPED_FUNGUS, rand.nextInt(4) + 1));
                break;
            case 77:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.CRIMSON_FUNGUS, rand.nextInt(4) + 1));
                break;
            case 78:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.LILY_PAD, rand.nextInt(4) + 1));
                break;
            case 79:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.STONE, rand.nextInt(32) + 1));
                break;
            case 80:
                on.getWorld().dropItemNaturally(on.getLocation(),
                        new ItemStack(Material.BLACKSTONE, rand.nextInt(32) + 1));
                break;
            case 81:
                on.getWorld().spawnEntity(onLocFix, EntityType.BEE);
                break;
            case 82:
                on.getWorld().spawnEntity(onLocFix, EntityType.CAT);
                break;
            case 83:
                on.getWorld().spawnEntity(onLocFix, EntityType.CHICKEN);
                break;
            case 84:
                on.getWorld().spawnEntity(onLocFix, EntityType.COW);
                break;
            case 85:
                on.getWorld().spawnEntity(onLocFix, EntityType.DONKEY);
                break;
            case 86:
                on.getWorld().spawnEntity(onLocFix, EntityType.FOX);
                break;
            case 87:
                on.getWorld().spawnEntity(onLocFix, EntityType.HORSE);
                break;
            case 88:
                on.getWorld().strikeLightning(on.getLocation());
                break;
            case 89:
                on.getWorld().spawnEntity(onLocFix, EntityType.LLAMA);
                break;
            case 90:
                on.getWorld().spawnEntity(onLocFix, EntityType.MULE);
                break;
            case 91:
                on.getWorld().spawnEntity(onLocFix, EntityType.MUSHROOM_COW);
                break;
            case 92:
                on.getWorld().spawnEntity(onLocFix, EntityType.OCELOT);
                break;
            case 93:
                on.getWorld().spawnEntity(onLocFix, EntityType.PANDA);
                break;
            case 94:
                on.getWorld().spawnEntity(onLocFix, EntityType.PARROT);
                break;
            case 95:
                on.getWorld().spawnEntity(onLocFix, EntityType.PIG);
                break;
            case 96:
                on.getWorld().spawnEntity(onLocFix, EntityType.POLAR_BEAR);
                break;
            case 97:
                on.getWorld().spawnEntity(onLocFix, EntityType.RABBIT);
                break;
            case 98:
                on.getWorld().spawnEntity(onLocFix, EntityType.SHEEP);
                break;
            case 99:
                on.getWorld().spawnEntity(onLocFix, EntityType.SQUID);
                break;
            case 100:
                on.getWorld().spawnEntity(onLocFix, EntityType.TURTLE);
                break;
            case 101:
                on.getWorld().spawnEntity(onLocFix, EntityType.VILLAGER);
                break;
            case 102:
                on.getWorld().spawnEntity(onLocFix, EntityType.WANDERING_TRADER);
                break;
            case 103:
                on.getWorld().spawnEntity(onLocFix, EntityType.WOLF);
                break;
            case 104:
                on.getWorld().spawnEntity(onLocFix, EntityType.ZOMBIE);
                break;
            case 105:
                on.getWorld().spawnEntity(onLocFix, EntityType.SKELETON);
                break;
            case 106:
                on.getWorld().spawnEntity(onLocFix, EntityType.SHULKER);
                break;
            case 107:
                on.getWorld().spawnEntity(onLocFix, EntityType.WITHER_SKELETON);
                break;
            case 108:
                on.getWorld().spawnEntity(onLocFix, EntityType.CREEPER);
                if (rand.nextInt(2) == 0){
                    on.getWorld().strikeLightning(on.getLocation());
                }
                break;
            case 109:
                on.getWorld().spawnEntity(onLocFix, EntityType.ENDERMAN);
                break;
            case 110:
                on.getWorld().spawnEntity(onLocFix, EntityType.HUSK);
                break;
            case 111:
                fillBlocks(onLocFixS, new Location(on.getWorld(),
                        onLocFixS.getX() + 4, onLocFixS.getY() + 4,
                        onLocFixS.getZ() + 4), Material.DIRT);
                break;
            case 112:
                fillBlocks(onLocFixS, new Location(on.getWorld(),
                        onLocFixS.getX() + 4, onLocFixS.getY() + 4,
                        onLocFixS.getZ() + 4), Material.STONE);
                break;
            case 113:
                if (rand.nextInt(10) < 7){
                    caveGen(onLocFixS, new Location(on.getWorld(),
                            onLocFixS.getX() + 2, onLocFixS.getY() + 2, onLocFixS.getZ() + 2));
                } else {
                    caveGen(onLocFixS, new Location(on.getWorld(),
                            onLocFixS.getX() + 4, onLocFixS.getY() + 4, onLocFixS.getZ() + 4));
                }
                break;
            case 114:
                fillBlocks(onLocFixS, new Location(on.getWorld(),
                        onLocFixS.getX() + 4, onLocFixS.getY(),
                        onLocFixS.getZ() + 4), Material.STONE);
                break;
            case 115:
                fillBlocks(onLocFixS, new Location(on.getWorld(),
                        onLocFixS.getX() + 4, onLocFixS.getY(),
                        onLocFixS.getZ() + 4), Material.DIRT);
                break;
            case 116:
                fillBlocks(onLocFixS, new Location(on.getWorld(),
                        onLocFixS.getX(), onLocFixS.getY() + 9,
                        onLocFixS.getZ()), Material.DIRT);
                break;
            case 117:
                fillBlocks(onLocFixS, new Location(on.getWorld(),
                        onLocFixS.getX(), onLocFixS.getY() + 9,
                        onLocFixS.getZ()), Material.STONE);
                break;
            case 118:
                fillBlocks(onLocFixS, new Location(on.getWorld(),
                        onLocFixS.getX() + 1, onLocFixS.getY(),
                        onLocFixS.getZ() + 1), Material.IRON_BLOCK);
                break;
            case 119:
                fillBlocks(onLocFixS, new Location(on.getWorld(),
                        onLocFixS.getX() + 1, onLocFixS.getY(),
                        onLocFixS.getZ() + 1), Material.COAL_BLOCK);
                break;
            case 120:
                fillBlocks(onLocFixS, new Location(on.getWorld(),
                        onLocFixS.getX() + 1, onLocFixS.getY(),
                        onLocFixS.getZ() + 1), Material.GOLD_BLOCK);
                break;
            case 121:
                on.getWorld().spawnFallingBlock(onLocFixM, Material.COAL_BLOCK.createBlockData());
                break;
            case 122:
                on.getWorld().spawnFallingBlock(onLocFixM, Material.IRON_BLOCK.createBlockData());
                break;
            case 123:
                on.getWorld().spawnFallingBlock(onLocFixM, Material.GOLD_BLOCK.createBlockData());
                break;
            case 124:
                on.getWorld().spawnFallingBlock(onLocFixM, Material.DIAMOND_BLOCK.createBlockData());
                break;
            case 125:
                on.getWorld().spawnFallingBlock(onLocFixM, Material.EMERALD_BLOCK.createBlockData());
                break;
            case 126:
                on.getWorld().spawnFallingBlock(onLocFixM, Material.LAPIS_BLOCK.createBlockData());
                break;
            case 127:
                on.getWorld().spawnFallingBlock(onLocFixM, Material.REDSTONE_BLOCK.createBlockData());
                if (rand.nextInt(2) == 0){
                    Location LocTnt = new Location(
                            on.getWorld(), onLocFixM.getX(),
                            onLocFixM.getY() + 50, onLocFixM.getZ());
                    on.getWorld().spawnFallingBlock(LocTnt, Material.TNT.createBlockData());
                }
                break;
        }
    }

    private void fillBlocks(Location point1, Location point2, Material typeBlock){
        if (typeBlock.isBlock()){

            int xa = 1, ya = 1, za = 1;

            if (point1.getBlockX() > point2.getBlockX()) xa = -1;
            if (point1.getBlockY() > point2.getBlockY()) ya = -1;
            if (point1.getBlockZ() > point2.getBlockZ()) za = -1;

            for (int x = point1.getBlockX(); x <= point2.getBlockX(); x += xa){
                for (int y = point1.getBlockY(); y <= point2.getBlockY(); y += ya){
                    for (int z = point1.getBlockZ(); z <= point2.getBlockZ(); z += za){

                        if (point1.getWorld().getBlockAt(x,y,z).getType() == Material.AIR)
                            point1.getWorld().getBlockAt(x,y,z).setType(typeBlock);
                    }
                }
            }
        }
    }

    private void caveGen(Location point1, Location point2){
        int xa = 1, ya = 1, za = 1;
        Random rand = new Random();

        if (point1.getBlockX() > point2.getBlockX()) xa = -1;
        if (point1.getBlockY() > point2.getBlockY()) ya = -1;
        if (point1.getBlockZ() > point2.getBlockZ()) za = -1;

        for (int x = point1.getBlockX(); x <= point2.getBlockX(); x += xa){
            for (int y = point1.getBlockY(); y <= point2.getBlockY(); y += ya){
                for (int z = point1.getBlockZ(); z <= point2.getBlockZ(); z += za){

                    if (point1.getWorld().getBlockAt(x,y,z).getType() == Material.AIR){

                        int randOre = rand.nextInt(128);
                        switch (randOre){
                            case 0:
                            case 1:
                            case 2:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.COAL_ORE);
                                break;
                            case 3:
                            case 4:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.IRON_ORE);
                                break;
                            case 5:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.LAPIS_ORE);
                                break;
                            case 6:
                            case 7:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.REDSTONE_ORE);
                                break;
                            case 8:
                            case 9:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.GOLD_ORE);
                                break;
                            case 10:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.EMERALD_ORE);
                                break;
                            case 11:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.DIAMOND_ORE);
                                break;
                            case 12:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.LAVA);
                                break;
                            case 13:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.WATER);
                                break;
                            default:
                                point1.getWorld().getBlockAt(x,y,z).setType(Material.STONE);
                        }
                    }
                }
            }
        }
    }
}
