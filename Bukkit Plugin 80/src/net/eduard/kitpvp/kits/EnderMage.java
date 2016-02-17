
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.effect.Effects;
import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.game.sound.Sounds;
import net.eduard.eduard_api.time.delay.Delay;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class EnderMage extends Kit implements Listener {

	public EnderMage() {
		super(KitType.ENDERMAGE);
		setName("§6EnderMag");
		setMaterial(Material.PORTAL);
		addLine("§bPuxa quem estiver acima ou abaixo de voce");
		addItem(1, new Item(Material.PORTAL, "§6EnderMage Portal"));

	}

	@EventHandler
	public void event(BlockPlaceEvent e) {

		Player p = e.getPlayer();
		if (hasKit(p)) {
			Item item = new Item(Material.PORTAL, "§6EnderMage Portal");
			if (new Item(p.getItemInHand()).isSimilar(item)) {
				if (e.getBlockPlaced().getType() == Material.PORTAL) {
					Location portal = e.getBlockPlaced().getLocation().add(0, 1, 0);
					Effects effect = new Effects(Effect.ENDER_SIGNAL, 9);
					Sounds sound = new Sounds(Sound.ENDERMAN_TELEPORT);
					for (Player c : getPlayers(p, 5)) {
						c.teleport(portal);
						c.setNoDamageTicks(20 * 5);
						effect.create(c.getLocation());
						sound.create(c);
						c.sendMessage(
							getTag() + "§3Voce esta invuneravel por 5 segundos");
						if (!p.equals(c)) {
							c.sendMessage(
								getTag() + "§aVoce foi puxado por §e" + p.getName());
						}
					}
					p.sendMessage(getTag()
						+ "§aVoce foi puxou todos players ao redor do portal!");
					p.setNoDamageTicks(20 * 5);
				}
				new Delay(3) {

					public void effect() {

						p.getInventory().addItem(item);
						e.getBlock().setType(Material.AIR);

					}

				};
			}

		}
	}

	public List<Player> getPlayers(Player p, int distance) {

		List<Player> list = new ArrayList<>();
		for (Entity e : p.getNearbyEntities(distance, 1000, distance)) {
			if (e instanceof Player) {
				Player c = (Player) e;
				list.add(c);
			}
		}
		return list;
	}

}
