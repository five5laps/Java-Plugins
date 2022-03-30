package Deku.TeamChooser;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Event_Handler implements Listener {

	private main plugin;

	public Event_Handler(main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onjoin(PlayerJoinEvent e) {

		FileConfiguration PlayerTeam = plugin.playerTeams;
		Player p = e.getPlayer();
		p.setGlowing(false);

		// При первом заходе забивает игрока в playerTeams.yml
		if (!PlayerTeam.contains("players." + p.getUniqueId().toString())) {

			String username = p.getName();

			PlayerTeam.contains("players." + p.getUniqueId().toString());
			PlayerTeam.contains("players." + p.getUniqueId().toString() + ".name");
			PlayerTeam.contains("players." + p.getUniqueId().toString() + ".team");

			PlayerTeam.set("players." + p.getUniqueId().toString() + ".name", username);
			PlayerTeam.set("players." + p.getUniqueId().toString() + ".team", "none");

		}
		// Чекер при заходе на сервер, выдаёт тиму
		if (PlayerTeam.get("players." + p.getUniqueId().toString() + ".team").equals("blue")) {
			Event_Handler_Methods.TeamSetBlue(p);
		} else if (PlayerTeam.get("players." + p.getUniqueId().toString() + ".team").equals("red")) {
			Event_Handler_Methods.TeamSetRed(p);
		} else {
			p.sendMessage("Вы ещё не выбрали команду!");
		}

		// Сохраняет .yml
		try {
			PlayerTeam.save(plugin.teams);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		p.sendMessage("Добро пожаловать, §e" + p.getName());
	}
}

/*
 * Нельзя драться между своей командой Кд на /join Выдавать эффекты не только
 * при перезаходе
 */
