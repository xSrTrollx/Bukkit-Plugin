
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.potion.Potions;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class Poseidon extends Kit implements Listener {

	public Poseidon() {
		super(KitType.POSEIDON);
		setMaterial(Material.WATER_BUCKET);
		setName("§6Poseidon");
		addLine("§bQuando andar sobre  a agua ganhe");
		addLine("§b+Força e +Velocidade");

	}


	 @EventHandler
	public void event(PlayerMoveEvent e) {

		Player p = e.getPlayer();
		if (hasKit(p)) {
			if (p.getLocation().getBlock().getType() == Material.STATIONARY_WATER
				|| p.getLocation().getBlock().getType() == Material.WATER) {
				new Potions(PotionEffectType.SPEED, 0, 20 * 5).create(p);
				new Potions(PotionEffectType.INCREASE_DAMAGE, 0, 20 * 5).create(p);
			}
		}
	}

}
