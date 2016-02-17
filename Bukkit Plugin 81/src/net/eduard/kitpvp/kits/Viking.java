
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Viking extends Kit implements Listener {

	public Viking() {
		super(KitType.VIKING);
		setName("§6Viking");
		setMaterial(Material.DIAMOND_AXE);
		addLine("§bCause mesmo dano que das");
		addLine("§bEspadas usando Machados");
		addItem(0, new Item(Material.STONE_AXE, "§6Viking Power"));

	}

	@EventHandler
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent e) {

		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (hasKit(p)) {
				if (p.getItemInHand().getType().name().contains("_AXE")) {
					e.setDamage(e.getDamage() + 1);
				}
			}
		}
	}

}
