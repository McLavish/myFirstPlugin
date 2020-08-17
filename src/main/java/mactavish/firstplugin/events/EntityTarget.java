package mactavish.firstplugin.events;

import mactavish.firstplugin.utils.Team;
import mactavish.firstplugin.utils.TeamsManager;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityTarget implements Listener {

  TeamsManager teamsManager = TeamsManager.getInstance();

  @EventHandler
  public void onEntityTargetEvent(EntityTargetEvent event) {

    if (event.getTarget() instanceof Player && event.getEntity() instanceof Creature) {
      Player player = (Player) event.getTarget();
      Creature mob = (Creature) event.getEntity();

      Team playerTeam = teamsManager.getTeam(player);
      if (playerTeam != null && playerTeam == teamsManager.getTeam(mob)) {
        event.setCancelled(true);
      }
    }
  }
}
