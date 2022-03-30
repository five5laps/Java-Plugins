package Deku.TeamChooser;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {

	File data = null;
	File teams = null;

	FileConfiguration spawnData = null;
	FileConfiguration playerTeams = null;

	@Override
	public void onDisable() {

	}

	@Override
	public void onEnable() {
		
		for(int i=0;i<3;i++) {
		Bukkit.getLogger().info("\u001B[32m[DEKUTeamChooser] TEAMCHOOSER IS ENABLE!\u001B[0m");
		}

		File folder = new File(getDataFolder().toString());
		if (!folder.exists())
			folder.mkdirs();

		teams = new File(getDataFolder() + File.separator + "playerTeams.yml");
		data = new File(getDataFolder() + File.separator + "data.yml");
		if (!data.exists() || !teams.exists()) {
			try {
				teams.createNewFile();
				data.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		spawnData = YamlConfiguration.loadConfiguration(data);
		playerTeams = YamlConfiguration.loadConfiguration(teams);

		Bukkit.getPluginManager().registerEvents(new Event_Handler(this), this);
		getCommand("spawn").setExecutor(new Command_spawn(this));
		getCommand("join").setExecutor(new Command_Join(this));
		getCommand("setspawn").setExecutor(new Command_setspawn(this));
	}
}
