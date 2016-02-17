
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

public class Whiter extends Kit implements Listener {

	public static Kit kit;

	public Whiter() {
		super(KitType.WHITER);
		setName("§6Wither");
		setMaterial(Material.SKULL_ITEM);
		setData(1);
		addLine("§bQuando estiver em PvP tem");
		addLine("§b33% de chance de dar Wither");

	}

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

		if (e.getEntity() instanceof LivingEntity
			&& e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			LivingEntity entity = (LivingEntity) e.getEntity();
			if (hasKit(p)) {
				if (getChance(0.33)) {
					new Potions(PotionEffectType.WITHER, 0, 5 * 20).create(entity);
				}
			}

		}
	}

}
