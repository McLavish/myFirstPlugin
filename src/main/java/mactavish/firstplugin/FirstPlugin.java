package mactavish.firstplugin;

import mactavish.firstplugin.commands.CommandItem;
import mactavish.firstplugin.events.BreakBlock;
import mactavish.firstplugin.events.EntityDeath;
import mactavish.firstplugin.events.EntityTarget;
import mactavish.firstplugin.events.PlayerInteract;
import org.bukkit.plugin.java.JavaPlugin;

public class FirstPlugin extends JavaPlugin {


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
