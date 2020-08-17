package mactavish.myFirstPlugin.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Team {
    private ArrayList<Player> members;
    private String name;

    public Team (Player founder, String name){
        this.members = new ArrayList<>();
        this.name = name;
        members.add(founder);
    }

    public void playerJoin (Player sender){
        members.add(sender);
    }

    public void playerLeave (Player sender){
        members.remove(sender);
    }

    public int count(){
        return members.size();
    }

    public String getName() {
        return name;
    }
}
