package mactavish.myFirstPlugin.utils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Treefeller {

    public final HashSet<Material> allowedTreeBlock = new HashSet<Material>() {
        {
            add(Material.ACACIA_LOG);
            add(Material.BIRCH_LOG);
            add(Material.DARK_OAK_LOG);
            add(Material.OAK_LOG);
            add(Material.JUNGLE_LOG);
            add(Material.SPRUCE_LOG);
        }
    };

    public LinkedHashSet<Block> getBlocks(Block start){
        return getBlocks(start, new LinkedHashSet<Block>());
    }

    public LinkedHashSet<Block> getBlocks(Block start, LinkedHashSet<Block> blocks){
        int checkRadius = 1;

        for(int x = -checkRadius; x <= checkRadius; x++){
            for(int y = -checkRadius; y <= checkRadius; y++){
                for(int z = -checkRadius; z <= checkRadius; z++){

                    Block block = start.getRelative(x, y, z);

                    if (!blocks.contains(block) && allowedTreeBlock.contains(block.getType())){
                        blocks.add(block);
                        blocks.addAll(getBlocks(block, blocks));
                    }
                }
            }
        }
        return blocks;
    }
}
