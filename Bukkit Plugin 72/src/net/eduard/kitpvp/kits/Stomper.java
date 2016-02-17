
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import java.util.List;

public class Stomper extends Kit implements Listener {

	public Stomper() {
		super(KitType.STOMPER);
		setName("§6Stomper");
		setMaterial(Material.ANVIL);
		addLine("§bPule sobre seus Inimigos");
		addLine("§bE os esmague-os");
		addItem(1, new Item(Material.SPONGE, "§6Stomper Boost"));

	}

	@EventHandler
	private void onEntityDamageEvent(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (hasKit(p)) {
				if (e.getCause() == DamageCause.FALL) {
					List<Entity> targets = p.getNearbyEntities(3, 3, 3);
					for (Entity target : targets) {
						if (target instanceof Player) {
							Player pTarget = (Player) target;
							if (!pTarget.isSneaking()) {
								pTarget.damage(e.getDamage());
								p.getWorld().playSound(p.getLocation(), Sound.ANVIL_LAND,
									2, 1);
							} else {
								pTarget.damage(2);
							}
						}
					}
					e.setDamage(e.getDamage() > 4 ? 4 : e.getDamage());
				}
			}
		}

	}

}
