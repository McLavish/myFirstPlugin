package mactavish.firstplugin.commands;

import mactavish.firstplugin.utils.TeamsManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandItem implements CommandExecutor {

    TeamsManager teamsManager = TeamsManager.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length != 2) {
                player.sendMessage("Error: Command accepts two arguments: /team <command> <team_name>");
                return true;
            }
            switch (args[0]) {
                case "create":
                    if (teamsManager.createTeam(player, args[1]))
                        player.sendMessage("Team successfully created!");
                    else
                        player.sendMessage("Error");
                    break;
                case "join":
                    if (teamsManager.addPlayer(player, args[1]))
                        player.sendMessage("You've joined the team");
                    else
                        player.sendMessage("Error");
                    break;
                case "leave":
                    if (teamsManager.removePlayer(player, args[1]))
                        player.sendMessage("You left the team");
                    else
                        player.sendMessage("Error");
                    break;
            }
        }
        return true;
    }
}
