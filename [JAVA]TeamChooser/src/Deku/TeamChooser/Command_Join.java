package Deku.TeamChooser;

import java.io.IOException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Command_Join implements CommandExecutor {

	private main plugin;

	public Command_Join(main plugin) {
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		FileConfiguration teams = plugin.playerTeams;
		Player p = (Player) sender;

		if (!(sender instanceof Player)) {
			sender.sendMessage("Not a player");
			return true;
		}

		if (args.length == 0) {
			p.sendMessage("Choose a team: §cred§r/§9blue");
			return true;
		}

		switch (args[0]) {
		case "blue":
			p.sendMessage("Вы вступили в команду §9синих");
			teams.set("players." + p.getUniqueId().toString() + ".team", args[0].toString());
			Event_Handler_Methods.TeamSetBlue(p);
			break;
		case "red":
			p.sendMessage("Вы вступили в команду §cкрасных");
			teams.set("players." + p.getUniqueId().toString() + ".team", args[0].toString());
			Event_Handler_Methods.TeamSetRed(p);
			break;
		default:
			p.sendMessage("Неверный выбор команды.");
			break;
		}

		try {
			teams.save(plugin.teams);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return true;
	}

}
