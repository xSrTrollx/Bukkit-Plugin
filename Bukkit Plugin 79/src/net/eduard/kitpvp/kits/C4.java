
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.explosion.Explosions;
import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.manager.click.Click;
import net.eduard.eduard_api.manager.click.util.ClickType;
import net.eduard.eduard_api.time.delay.Delay;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class C4 extends Kit implements Listener {

	public C4() {

		super(KitType.C4);
		Item c4 = new Item(Material.TNT, "§6C4");
		setName("§6C4");
		setMaterial(Material.TNT);
		setCooldown(30);
		addLine("§bJoga uma C4 que explode em 3 segundos");
		addItem(1, c4);
		new Click(c4, ClickType.RIGHT_CLICK) {

			public void effect(PlayerInteractEvent e) {

				Player p = e.getPlayer();
				if (hasKit(p)) {
					e.setCancelled(true);
					if (skillOnCooldown(p)) {
						skillCooldownMessage(p);
					} else {
						Location loc = p.getEyeLocation().add(0, 1, 0);
						org.bukkit.entity.Item bomb =
							p.getWorld().dropItem(loc, e.getItem());
						bomb.setPickupDelay(1000000);
						bomb.setVelocity(loc.getDirection().multiply(0.6));
						new Delay(3) {

							public void effect() {

								new Explosions(5, false, false)
									.create(bomb.getLocation());
								bomb.remove();
							}
						};
						skillStarCooldown(p, getCooldown());
					}
				}
			}

		};
	}

}
