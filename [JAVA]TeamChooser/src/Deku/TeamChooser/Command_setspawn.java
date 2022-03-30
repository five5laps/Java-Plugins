package Deku.TeamChooser;

import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Command_setspawn implements CommandExecutor {

	private main plugin;

	public Command_setspawn(main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!sender.hasPermission("deku.setspawn")) {
			sender.sendMessage("§4You dont have permission");
			return true;
		}

		FileConfiguration spawn = plugin.spawnData;

		double SpawnX, SpawnY, SpawnZ;
		float SpawnPitch, SpawnYaw;

		if (label.equalsIgnoreCase("setspawn")) {

			if (!(sender instanceof Player)) {
				sender.sendMessage("Not a player");
				return true;
			}

			Player p = (Player) sender;
			Location loc = p.getLocation();

			String world = p.getWorld().getName();

			SpawnX = loc.getBlockX();
			SpawnY = loc.getBlockY();
			SpawnZ = loc.getBlockZ();
			SpawnPitch = loc.getPitch();
			SpawnYaw = loc.getYaw();

			p.sendMessage("§6Установка спавна...");

			spawn.createSection("spawn");
			spawn.createSection("spawn.X");
			spawn.createSection("spawn.Y");
			spawn.createSection("spawn.Z");
			spawn.createSection("spawn.Pitch");
			spawn.createSection("spawn.Yaw");
			spawn.createSection("spawn.World");

			spawn.set("spawn.X", SpawnX);
			spawn.set("spawn.Y", SpawnY);
			spawn.set("spawn.Z", SpawnZ);
			spawn.set("spawn.Pitch", (float) SpawnPitch);
			spawn.set("spawn.Yaw", (float) SpawnYaw);
			spawn.set("spawn.World", world);

			try {
				spawn.save(plugin.data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
