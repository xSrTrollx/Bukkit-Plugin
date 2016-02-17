
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class Fisherman extends Kit implements Listener {

	public Fisherman() {

		super(KitType.FISHERMAN);
		setName("§6FisherMan");
		setMaterial(Material.FISHING_ROD);
		addLine("§bUse sua vara de pescar para puxar jogadores");
		addItem(1, new Item(Material.FISHING_ROD, "§6FisherMan Rod"));

	}

	 @EventHandler
	public void event(PlayerFishEvent e) {

		Player p = e.getPlayer();
		if (hasKit(p)) {
			if (e.getCaught() != null) {
				e.getCaught().teleport(p.getLocation()
					.setDirection(e.getCaught().getLocation().getDirection()));
				p.sendMessage(getTag() + "§aVoce puxou: §6"
					+ (e.getCaught() instanceof Player
						? ((Player) e.getCaught()).getName()
						: e.getCaught().getType().name().toLowerCase()));
				if (e.getCaught() instanceof Player) {
					Player c = (Player) e.getCaught();
					c.sendMessage(
						getTag() + "§aVoce foi puxado por: §6" + p.getName());
				}
			}
		}
	}

}
