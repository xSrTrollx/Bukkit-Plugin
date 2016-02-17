
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.effect.Effects;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Critical extends Kit implements Listener {

	public Critical() {
		super(KitType.CRITICAL);
		setName("§6Critical");
		setMaterial(Material.GOLDEN_APPLE);
		addLine("§bQuando estiver em PvP tem");
		addLine("§b30% de chance de dar Critico");
		addLine("§bCausando +4 de Dano");

	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void event(EntityDamageByEntityEvent e) {

		if (e.getDamager() instanceof Player) {
			Player d = (Player) e.getDamager();

			if (hasKit(d)) {
				if (getChance(0.30)) {
					e.setDamage(e.getDamage() + 4);
					if (e.getEntity() instanceof Player) {
						Player p = (Player) e.getEntity();
						p.sendMessage(
							getTag() + "§aVoce sofreu dano critico do §6" + d.getName());
						d.sendMessage(
							getTag() + "§aVoce fez dano critico do §6" + p.getName());
					} else {
						d.sendMessage(getTag() + "§aVoce fez dano critico do §6"
							+ e.getEntityType().name().toLowerCase());
					}

					new Effects(Effect.STEP_SOUND, Material.REDSTONE_BLOCK.getId())
						.create(e.getEntity().getLocation().add(0, 1, 0));
				}
			}

		}
	}
}
