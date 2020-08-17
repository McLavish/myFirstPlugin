package mactavish.myFirstPlugin.utils;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;

import java.util.HashMap;

//Singleton
//https://bukkit.org/threads/team-systems.411790/
public class TeamsManager {
    private static TeamsManager instance = new TeamsManager();

    private TeamsManager() {}

    private HashMap<String, Team> teams = new HashMap<>();
    private HashMap<Player, Team> players = new HashMap<>();
    private HashMap<Creature, Team> mobs = new HashMap<>();

    public static TeamsManager getInstance(){
        return instance;
    }

    public boolean createTeam(Player founder, String teamName){
        if (isInTeam(founder) || teams.containsKey(teamName))
            return false;

        Team team = new Team(founder, teamName);
        teams.put(teamName, team);
        players.put(founder, team);

        return true;
    }

    public boolean addPlayer(Player player, String teamName){
        if (teams.containsKey(teamName) && !isInTeam(player)){
            Team team = teams.get(teamName);
            team.playerJoin(player);
            players.put(player, team);
            return true;
        }

        return false;
    }

    public boolean removePlayer(Player player, String teamName){
        if (teams.containsKey(teamName) && isInTeam(player)){
            Team team = players.get(player);
            players.remove(player);
            team.playerLeave(player);

            if (team.count() <= 0){
                teams.remove(team.getName());
            }

            return true;
        }

        return false;
    }

    public boolean addMob(Creature mob, Team team){
        mobs.put(mob, team);
        return true;
    }

    public boolean isInTeam(Player player){
        return players.containsKey(player);
    }

    public boolean isInTeam(Creature zombie){
        return mobs.containsKey(zombie);
    }

    public Team getTeam(Player player){
        if (isInTeam(player)){
            return players.get(player);
        }
        return null;
    }

    public Team getTeam(Creature mob){
        if (isInTeam(mob)){
            return mobs.get(mob);
        }
        return null;
    }
}
