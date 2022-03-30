package Deku.TeamChooser;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Event_Handler_Methods implements Listener {

static public void BluePotions(Player p) {

		int PotionLevel = 0;

		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}

		p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, PotionLevel));
		p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, PotionLevel));
	}

static public void RedPotions(Player p) {

		int PotionLevel = 0;

		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}

		p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, PotionLevel));
		p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, PotionLevel));
	}

static public void TeamSetBlue(Player p) {

		String username = p.getName();

		// p.setGlowing(true);
		p.setPlayerListName("§9" + username);
		p.sendMessage("Ты в ряду §9синих!");

		BluePotions(p);
	}

static public void TeamSetRed(Player p) {

		String username = p.getName();

		// p.setGlowing(true);
		p.setPlayerListName("§c" + username);
		p.sendMessage("Ты в ряду §cкрасных!");

		RedPotions(p);
	}

}
