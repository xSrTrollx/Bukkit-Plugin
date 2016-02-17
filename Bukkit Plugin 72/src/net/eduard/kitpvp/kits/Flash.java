
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.effect.Effects;
import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.game.potion.Potions;
import net.eduard.eduard_api.game.sound.Sounds;
import net.eduard.eduard_api.manager.click.Click;
import net.eduard.eduard_api.manager.click.util.ClickType;
import net.eduard.eduard_api.time.delay.Delay;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class Flash extends Kit implements Listener {

	public Flash() {

		super(KitType.FLASH);
		setName("§6Flash");
		setMaterial(Material.REDSTONE_TORCH_ON);
		Item sp = new Item(Material.REDSTONE_TORCH_ON);
		addLine("Teleporte onde mirar!");
		setCooldown(15);
		addItem(1, sp);
		setHasCooldown(true);
		new Click(sp, ClickType.RIGHT_CLICK) {

			@SuppressWarnings("deprecation")
			public void effect(PlayerInteractEvent e) {

				Player p = e.getPlayer();
				if (skillOnCooldown(p)) {
					skillCooldownMessage(p);
				} else {
					Block block =
						p.getTargetBlock(null, 100).getRelative(BlockFace.UP);
					p.teleport(block.getLocation()
						.setDirection(p.getLocation().getDirection()));
					new Potions(PotionEffectType.BLINDNESS, 0, 20 * 5).create(p);
					new Sounds(Sound.ENDERMAN_TELEPORT).create(p);
					new Effects(Effect.ENDER_SIGNAL, 0).create(p);
					new Delay(getCooldown()) {

						public void effect() {

							for (ItemStack item : p.getInventory().getContents()) {
								if (item != null) {
									if (item
										.getType() == Material.REDSTONE_TORCH_OFF) {
										item.setType(Material.REDSTONE_TORCH_ON);
									}
								}
							}

						}

					};
					skillStarCooldown(p, getCooldown());
				}
			}

		};
	}

}
