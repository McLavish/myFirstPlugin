package mactavish.myFirstPlugin.events;

import mactavish.myFirstPlugin.utils.Team;
import mactavish.myFirstPlugin.utils.TeamsManager;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;

public class EntityTarget implements Listener {

    TeamsManager teamsManager = TeamsManager.getInstance();

    @EventHandler
    public void onEntityTargetEvent(EntityTargetEvent event){

        if (event.getTarget() instanceof Player && event.getEntity() instanceof Creature){
            System.out.println("Creature is targetting player");
            Player player = (Player) event.getTarget();
            Creature mob = (Creature) event.getEntity();

            Team playerTeam = teamsManager.getTeam(player);
            if (playerTeam != null && playerTeam == teamsManager.getTeam(mob)){
                System.out.println("Player is on the creature's team (" + playerTeam.getName() + "). Cancelling target");
                event.setCancelled(true);
            }
        }
    }
}
