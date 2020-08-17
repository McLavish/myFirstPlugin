package mactavish.firstplugin.utils;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ExampleTask extends BukkitRunnable {
  private final JavaPlugin plugin;

  public ExampleTask(JavaPlugin plugin) {
    this.plugin = plugin;
  }

  public void run() {

  }
}
