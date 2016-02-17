
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.game.potion.Potions;
import net.eduard.kitpvp.KitPvP;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

public class Ghoul extends Kit implements Listener {

	public Ghoul() {

		super(KitType.GHOUL);
		setName("§6Ghoul");
		setMaterial(Material.REDSTONE);
		addLine("§bGanhe muitos Bonus");
		addLine("§b+Velocidade +Regeneracão");
		addLine("§b+Dano +Pulo +Vida Extra");
		setHasCooldown(true);
		setCooldown(60);
		addItem(1, new Item(Material.REDSTONE_BLOCK, "§6Ghoul Boots"));

	}

	@EventHandler
	public void event(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		if (hasKit(p)) {
			if (e.getItem() == null) {
				return;
			}
			if (e.getAction().name().contains("RIGHT")) {
				if (new Item(e.getItem())
					.equals(new Item(Material.REDSTONE_BLOCK, "§6Ghoul Boots"))) {
					if (KitPvP.cooldown.has(p)) {
						p.sendMessage(
							"§8[§bGhoul§8] §aHabilidade em Cooldown espere mais "
								+ KitPvP.cooldown.getTime(p) + " segundos!");
					} else {
						p.sendMessage("§8[§bGhoul§8] §aHabilidade ativada!");
						new Potions(PotionEffectType.JUMP, 0, 20 * 30).create(p);
						new Potions(PotionEffectType.SPEED, 0, 20 * 30).create(p);
						new Potions(PotionEffectType.CONFUSION, 3, 20 * 30).create(p);
						new Potions(PotionEffectType.HEALTH_BOOST, 0, 20 * 30).create(p);
						new Potions(PotionEffectType.INCREASE_DAMAGE, 2, 20 * 30)
							.create(p);
						KitPvP.cooldown.start(p, 60,
							"§8[§bGhoul§8] §aAgora pode ativar modo Ghoul novamente!");
					}
					e.setCancelled(true);
				}
			}
		}
	}

}
