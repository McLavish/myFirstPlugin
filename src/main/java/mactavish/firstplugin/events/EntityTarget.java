package mactavish.firstplugin.events;

import mactavish.firstplugin.utils.Team;
import mactavish.firstplugin.utils.TeamsManager;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

import java.util.List;

public class EntityTarget implements Listener {

  TeamsManager teamsManager = TeamsManager.getInstance();

  @EventHandler
  public void onEntityTargetEvent(EntityTargetEvent event) {

    if (event.getTarget() instanceof Player && event.getEntity() instanceof Creature) {
      Player player = (Player) event.getTarget();
      Creature mob = (Creature) event.getEntity();

      Team playerTeam = teamsManager.getTeam(player);
      Team mobTeam = teamsManager.getTeam(mob);

      if (playerTeam != null && playerTeam == mobTeam) {

        List<Entity> nearbyEntities = mob.getNearbyEntities(10,5,10);

        //Codice schifo fatto all'una di notte, cambiare pls
        for (Entity entity : nearbyEntities) {

          if (entity instanceof Creature) {
            Creature targetMob = (Creature) entity;
            Team targetTeam = teamsManager.getTeam(targetMob);

            if (targetTeam == null || targetTeam != mobTeam) {
              mob.setTarget(targetMob);
              break;
            }

          } else if (entity instanceof Player) {
            Player targetPlayer = (Player) entity;
            Team targetTeam = teamsManager.getTeam(targetPlayer);

            if (targetTeam == null || targetTeam != mobTeam) {
              mob.setTarget(targetPlayer);
              break;
            }
          }
        }
        event.setCancelled(true);
      }
    }
  }
}
