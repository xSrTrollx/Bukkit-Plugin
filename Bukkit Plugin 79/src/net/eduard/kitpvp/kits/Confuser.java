
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

public class Confuser extends Kit implements Listener {

	public Confuser() {
		super(KitType.CONFUSER);
		setName("§6Confuser");
		setMaterial(Material.APPLE);
		addLine("§bQuando estiver em PvP tem");
		addLine("§b33% de chance de dar Confusão");

	}

	@EventHandler
	public void event(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Player
			&& e.getEntity() instanceof LivingEntity) {
			Player p = (Player) e.getDamager();
			if (hasKit(p)) {
				if (getChance(0.33)) {
					new Potions(PotionEffectType.CONFUSION, 0, 20 * 5)
						.create((LivingEntity) e.getEntity());
				}
			}
		}
	}
}
