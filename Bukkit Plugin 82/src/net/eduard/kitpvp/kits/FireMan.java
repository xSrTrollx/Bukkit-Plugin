
package net.eduard.kitpvp.kits;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class FireMan extends Kit implements Listener {

	public FireMan() {
		super(KitType.FIREMAN);
		setName("§6FireMan");
		setMaterial(Material.FIRE);
		addLine("§bNao levar dano de Fogo e quando");
		addLine("§bQuando bater em alguem faça-o pegar Fogo");

	}
	
	@EventHandler
	public void event(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (hasKit(p)) {
				e.getEntity().setFireTicks(20 * 5);
			}

		}
	}

	@EventHandler
	public void event(EntityDamageEvent e) {

		if (e.getCause() == DamageCause.LAVA | e.getCause() == DamageCause.FIRE
			| e.getCause() == DamageCause.FIRE_TICK) {
			if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (hasKit(p)) {
				e.setCancelled(true);
			}
		}
		}
	}
}
