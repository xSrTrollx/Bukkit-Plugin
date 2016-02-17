
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.potion.Potions;
import net.eduard.eduard_api.game.sound.Sounds;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffectType;

public class Berserker extends Kit  {

	public Berserker() {
		super(KitType.BERSERKER);
		setName("§6Berserker");
		setMaterial(Material.OBSIDIAN);
		addLine("§bQuando eliminar alguem ganhe efeitos");
		addLine("§b+Força 1 por 20 segundos");
		addLine("§b+Velocidade 1 por 20 segundos");

	}

	@EventHandler
	public void event(EntityDeathEvent e) {

		if (e.getEntity().getKiller() != null) {
			Player d = e.getEntity().getKiller();
			if (hasKit(d)) {
				new Potions(PotionEffectType.INCREASE_DAMAGE, 0, 20 * 20).create(d);
				new Potions(PotionEffectType.SPEED, 0, 20 * 20).create(d);
				new Sounds(Sound.AMBIENCE_THUNDER).create(d);
				d.sendMessage(getTag() + "§3Habilidade ativada!");
			}
		}
	}
}
