
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.manager.click.Click;
import net.eduard.eduard_api.manager.click.util.CheckType;
import net.eduard.eduard_api.manager.click.util.ClickType;

import net.eduard.kitpvp.KitPvP;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map.Entry;

public class Thor extends Kit implements Listener {

	public static HashMap<Player, LightningStrike> thors = new HashMap<>();

	public Thor() {

		super(KitType.THOR);
		setName("§6Thor");
		setMaterial(Material.WOOD_AXE);
		addLine("§bAo usar o Machado cria um Raio onde mirar");
		setHasCooldown(true);
		setCooldown(7);
		Item item = new Item(Material.WOOD_AXE, "§6Thor");
		addItem(1, item);
		Click click = new Click(item, ClickType.BLOCK_CLICK) {

			@SuppressWarnings("deprecation")
			public void effect(PlayerInteractEvent e) {

				Player p = e.getPlayer();
				if (hasKit(p)) {
					if (skillOnCooldown(p)) {
						skillCooldownMessage(p);
					} else {
						Location loc = p.getTargetBlock(null, 100).getLocation();
						LightningStrike thor = loc.getWorld().strikeLightning(loc);
						thors.put(p, thor);
						skillStarCooldown(p, getCooldown());

					}
				}

			}
		};
		click.setCheck(CheckType.BY_TYPE);
	}

	@SuppressWarnings("deprecation")
	// @EventHandler
	public void Video15(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		if (hasKit(p)) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR
				| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getMaterial().name().contains("_AXE")) {
					e.setCancelled(true);
					if (KitPvP.cooldown.has(p)) {
						p.sendMessage("§cVoce precisa espera mais "
							+ KitPvP.cooldown.getTime(p) + " segundos!");
					} else {
						Location loc = p.getTargetBlock(null, 100).getLocation();
						loc.getWorld().strikeLightning(loc);
						p.sendMessage("§aVoce usou o Machado de Thor!");
						KitPvP.cooldown.start(p, 5,
							"§6Voce pode usar o Kit Novamente");
					}
				}
			}
		}
	}

	@EventHandler
	public void event(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof LightningStrike) {
			LightningStrike thisThor = (LightningStrike) e.getDamager();
			for (Entry<Player, LightningStrike> thor : thors.entrySet()) {
				if (thor.getValue().equals(thisThor)) {
					if (e.getEntity() instanceof Player) {
						Player p = (Player) e.getEntity();
						if (thor.getKey().equals(p)) {
							e.setCancelled(true);
						}

					}
				}
			}

		}
	}
}
