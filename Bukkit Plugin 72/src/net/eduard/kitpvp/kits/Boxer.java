
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Boxer extends Kit implements Listener {

	public Boxer() {
		super(KitType.BOXER);
		setName("§6Boxer");
		setMaterial(Material.QUARTZ);
		addLine("§bDe o mesmo dano que uma Espada de Pedra");
		addLine("§bUsando nada na Mao");
		addItem(0, new Item(Material.AIR));

	}

	@EventHandler
	public void event(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Player) {
			Player d = (Player) e.getDamager();
			if (hasKit(d)) {
				if (d.getItemInHand() != null) {
					if (d.getItemInHand().getType() == Material.AIR) {
						e.setDamage(5);
					}
				}
			}
		}
	}

}
