
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class Specialist extends Kit implements Listener {

	public Specialist() {
		super(KitType.SPEACIALIST);
		setName("§6Specialist");
		setMaterial(Material.BOOK);
		addLine("§bGanhe Livro de encantar portatil");
		addItem(1, new Item(Material.BOOK, "§6Specialist Book"));

	}

	@EventHandler
	public void onEntityDeathEvent(EntityDeathEvent e) {

		if (e.getEntity().getKiller() instanceof Player) {
			Player p = e.getEntity().getKiller();
			if (hasKit(p)) {
				p.getInventory()
					.addItem(new Item(Material.EXP_BOTTLE, "§6Specialist XP"));
			}
		}
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		if (hasKit(p)) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK
				|| e.getAction() == Action.RIGHT_CLICK_AIR) {
				if (e.getMaterial() == Material.BOOK) {
					p.openEnchanting(p.getLocation(), true);
				}
			}
		}
	}

}
