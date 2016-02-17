
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.sound.Sounds;
import net.eduard.eduard_api.time.delay.Delay;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class Anchor extends Kit {

	public Anchor() {
		super(KitType.ANCHOR);
		setName("§6Archor");
		setMaterial(Material.ANVIL);
		addLine("§bSer invuneravel a knockback e tambem");
		addLine("§bNao vai poder dar knockback em ninguem");
	}

	// Ant NockBack
	@EventHandler
	public void event(EntityDamageByEntityEvent e) {

		Sounds sound = new Sounds(Sound.ANVIL_LAND);
		Vector stop = new Vector();
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (hasKit(p)) {
				sound.create(p);
				new Delay(1L) {

					public void effect() {

						e.getEntity().setVelocity(stop);
					}

				};
			}
		}
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (hasKit(p)) {
				sound.create(p);
				new Delay(1L) {

					public void effect() {

						e.getEntity().setVelocity(stop);
					}

				};
			}
		}

	}
}
