
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.potion.Potions;
import net.eduard.eduard_api.game.sound.Sounds;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

public class Wolfman extends Kit implements Listener {

	public Wolfman() {
		super(KitType.WOLFMAN);
		setName("§6WolfMan");
		setHasCooldown(true);
		setCooldown(30);
		setMaterial(Material.MONSTER_EGG);
		addLine("§bGanhe melhores Bonus");
		addLine("§bGanhe lobos ajudantes");
		addLine("§b+Velocidade +Pulo");

	}

	@EventHandler
	public void event(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		if (hasKit(p)) {
			if (e.getAction().name().contains("RIGHT")) {
				if (e.getMaterial() == Material.MONSTER_EGG) {
				e.setCancelled(true);
				if (cooldown.has(p)) {

				} else {
					Location loc = p.getLocation();
					Wolf wolf1 =
						p.getWorld().spawn(loc.clone().add(0, 0, 2), Wolf.class);
					Wolf wolf2 =
						p.getWorld().spawn(loc.clone().add(2, 0, 2), Wolf.class);
					Wolf wolf3 =
						p.getWorld().spawn(loc.clone().add(2, 0, 0), Wolf.class);
					wolf1.setAdult();
					wolf2.setAdult();
					wolf3.setAdult();
					wolf1.setAngry(true);
					wolf2.setAngry(true);
					wolf3.setAngry(true);
					for (Entity entity : p.getNearbyEntities(5, 5, 5)) {
						if (entity instanceof Player) {
							Player p2 = (Player) entity;
							wolf1.setTarget(p2);
							wolf2.setTarget(p2);
							wolf3.setTarget(p2);

						}
					}
					new Potions(PotionEffectType.SPEED, 0, 20 * 25).create(p);
					new Potions(PotionEffectType.JUMP, 0, 20 * 25).create(p);
					new Sounds(Sound.WOLF_HOWL).create(p);
					cooldown.start(p, getCooldown(), "§aVoce saiu do cooldown!");
				}
			}
			}
		}
	}
}
