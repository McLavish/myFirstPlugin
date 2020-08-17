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

  private final HashMap<Material, Material> oreToIngot = new HashMap<>() {
    {
      put(Material.IRON_ORE, Material.IRON_INGOT);
      put(Material.GOLD_ORE, Material.GOLD_INGOT);
    }
  };

  Treefeller trefeller = new Treefeller();

  @EventHandler
  public void onPlayerBreakBlock(BlockBreakEvent event) {
    Block block = event.getBlock();
    Material blockType = block.getType();

    // Todo: check for creative mode
    // Code to drop ingots from ore

    if (oreToIngot.containsKey(blockType)) {
      ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
      List<ItemStack> drops = (List<ItemStack>) block.getDrops(tool);

      if (drops.isEmpty()) {
        return;
      }

      event.setCancelled(true);
      event.getBlock().setType(Material.AIR);

      ItemStack newDrop = new ItemStack(oreToIngot.get(blockType));
      block.getWorld().dropItemNaturally(block.getLocation(), newDrop);

      //Code for trefeller
    } else if (trefeller.allowedTreeBlock.contains(blockType)) {
      LinkedHashSet<Block> tree = trefeller.getBlocks(block);
      tree.forEach(Block::breakNaturally);
    }
  }
}
