
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Switcher extends Kit implements Listener {

	public Switcher() {
		super(KitType.SWITCHER);
		setName("§6Switcher");
		setMaterial(Material.SNOW_BALL);
		addLine("§bQuando acertar a Bolinha de Neve");
		addLine("§bEm algo sera teleportado ate essa entidade");
		addLine("§bE a entidade ate voce");
		addItem(1, new Item(Material.SNOW_BALL, 16, 0, "§6Switcher Power"));

	}

	 @EventHandler
	public void event(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Snowball) {
			Snowball snowball = (Snowball) e.getDamager();
			if (snowball.getShooter() instanceof Player) {
				Player p = (Player) snowball.getShooter();
				Entity d = e.getEntity();
				if (hasKit(p)) {
					Location loc1 =
						p.getLocation().setDirection(d.getLocation().getDirection());
					Location loc2 =
						d.getLocation().setDirection(p.getLocation().getDirection());
					p.teleport(loc2);
					d.teleport(loc1);
				}

			}

		}
	}
}
