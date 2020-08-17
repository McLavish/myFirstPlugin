package mactavish.firstplugin.events;

import mactavish.firstplugin.utils.Treefeller;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class BreakBlock implements Listener {

    private final HashMap<Material,Material> oreToIngot = new HashMap<Material,Material>() {
        {
            put(Material.IRON_ORE,Material.IRON_INGOT);
            put(Material.GOLD_ORE,Material.GOLD_INGOT);
        }
    };

    Treefeller trefeller = new Treefeller();

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event){
        Block block = event.getBlock();
        Material blockType = block.getType();

        //bug: devo controllare la creative
        if (oreToIngot.containsKey(blockType))
        {
            List<ItemStack> drops = (List<ItemStack>) block.getDrops(event.getPlayer().getInventory().getItemInMainHand());

            if (drops.isEmpty())
                return;

            ItemStack drop = drops.get(0);

            event.setCancelled(true);
            event.getBlock().setType(Material.AIR);
            block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(oreToIngot.get(blockType)));
        }

        else if (trefeller.allowedTreeBlock.contains(blockType)){
            LinkedHashSet<Block> tree = trefeller.getBlocks(block);
            tree.forEach(Block::breakNaturally);
        }
    }
}
