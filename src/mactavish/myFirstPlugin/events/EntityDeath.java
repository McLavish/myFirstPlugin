package mactavish.myFirstPlugin.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;

public class EntityDeath implements Listener {

    private final HashMap<Material,Material> rawToCooked = new HashMap<Material,Material>() {
        {
            put(Material.MUTTON,Material.COOKED_MUTTON);
            put(Material.BEEF,Material.COOKED_BEEF);
            put(Material.CHICKEN,Material.COOKED_CHICKEN);
            put(Material.PORKCHOP,Material.COOKED_PORKCHOP);
            put(Material.RABBIT,Material.COOKED_RABBIT);
        }
    };

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        List<ItemStack> drops = event.getDrops();
        drops.replaceAll(itemStack -> {
            Material dropType = itemStack.getType();

            return rawToCooked.containsKey(dropType)
                    ? new ItemStack(rawToCooked.get(dropType), itemStack.getAmount())
                    : itemStack;
        });
        System.out.println("The mob dropped: " + drops);
    }
}
