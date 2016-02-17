
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class Hunter extends Kit implements Listener {

	public Hunter() {
		super(KitType.HUNTER);
		setName("§6Hunter");
		setMaterial(Material.ARROW);
		addItem(9 * 3, new Item(Material.ARROW));
		addItem(1, new Item(Material.BOW, "§6Hunter"));
		addLine("§bUse o Arco e mate o inimigo com 1 Hit");
		addLine("§bQuando matar usando Arco ganhe +1 Flecha");
		addLine("§bQuando matar usando Espada ganhe +2 Flechas");

	}

	@EventHandler
	public void event(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) e.getDamager();
			if (arrow.getShooter() instanceof Player) {
				Player p = (Player) arrow.getShooter();
				if (hasKit(p)) {
					e.setDamage(10000);
				}

			}

		}
	}

	@EventHandler
	public void event(EntityDeathEvent e) {

		if (e.getEntity().getKiller() != null) {
			Player p = e.getEntity().getKiller();
			if (p.equals(e.getEntity())) {
				return;
			}
			if (hasKit(p)) {
				p.getInventory().addItem(new Item(Material.ARROW));
			}
		}
	}

}
