
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.potion.Potions;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class DarkMage extends Kit implements Listener {

	public DarkMage() {
		super(KitType.DARKMAGE);
		setName("§6DarkMage");
		setMaterial(Material.COAL);
		addLine("§bQuando estiver em PvP tem");
		addLine("§b33% de chance de dar Cegueira");

	}

	@EventHandler
	public void event(EntityDamageByEntityEvent e) {

		if (e.getEntity() instanceof LivingEntity
			&& e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			LivingEntity entity = (LivingEntity) e.getEntity();
			if (hasKit(p)) {
				if (getChance(0.33)) {
					new Potions(PotionEffectType.BLINDNESS, 0, 5 * 20).create(entity);
				}
			}
		}
	}

}
