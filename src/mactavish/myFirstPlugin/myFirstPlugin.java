package mactavish.myFirstPlugin;

import mactavish.myFirstPlugin.commands.CommandItem;
import mactavish.myFirstPlugin.events.BreakBlock;
import mactavish.myFirstPlugin.events.EntityDeath;
import mactavish.myFirstPlugin.events.EntityTarget;
import mactavish.myFirstPlugin.events.PlayerInteract;
import org.bukkit.plugin.java.JavaPlugin;

public class myFirstPlugin extends JavaPlugin {


    @Override
    public void onEnable() {
        this.getCommand("myteams").setExecutor(new CommandItem());

        getServer().getPluginManager().registerEvents(new EntityDeath(), this);
        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginManager().registerEvents(new EntityTarget(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
    }

    @Override
    public void onDisable() {

    }
}
