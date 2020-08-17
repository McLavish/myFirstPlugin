package mactavish.firstplugin.events;

import mactavish.firstplugin.utils.Team;
import mactavish.firstplugin.utils.TeamsManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class PlayerInteract implements Listener {

  TeamsManager teamsManager = TeamsManager.getInstance();
  HashMap<Material, EntityType> allowedMobs = new HashMap<>() {
    {
      put(Material.ZOMBIE_SPAWN_EGG, EntityType.ZOMBIE);
      put(Material.SKELETON_SPAWN_EGG, EntityType.SKELETON);
    }
  };

  @EventHandler
  public void onPlayerInteract(PlayerInteractEvent event) {

    ItemStack item = event.getItem();
    if (item == null) {
      return;
    }
    Material itemType = item.getType();
    System.out.println("Player right clicked");

    if (allowedMobs.containsKey(itemType) && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
      System.out.println("Player is attempting to spawn a zombie");
      if (teamsManager.isInTeam(event.getPlayer())) {
        Team playerTeam = teamsManager.getTeam(event.getPlayer());

        //Location should be 1 block above the location otherwise it spawns inside the terrain.
        Location location = event.getClickedBlock().getLocation().add(0, 1, 0);

        Creature mob = (Creature) location.getWorld().spawnEntity(location, allowedMobs.get(itemType));
        mob.setCustomName(playerTeam.getName() + " " + mob.getType());
        mob.setCustomNameVisible(true);
        teamsManager.addMob(mob, playerTeam);

        System.out.println("Added zombie to " + playerTeam.getName());
        event.setCancelled(true);
      }
    }
  }
}
