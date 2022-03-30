package Deku.TeamChooser;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.World;;

public class Command_spawn implements CommandExecutor {

	private main plugin;

	public Command_spawn(main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		FileConfiguration spawn = plugin.spawnData;

		if (label.equalsIgnoreCase("spawn")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage("Not a player");
				return true;
			}
			Player p = (Player) sender;

			World world = plugin.getServer().getWorld((String) spawn.get("spawn.World"));

			Location worldspawn = new Location(world, (double) spawn.get("spawn.X"), (double) spawn.get("spawn.Y"),
					(double) spawn.get("spawn.Z"));

			worldspawn.add(0.5, 0, 0.5);
			p.sendMessage("§6Телепорт на спавн...");
			p.teleport(worldspawn);
		}

		return true;
	}

}
