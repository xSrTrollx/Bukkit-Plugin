
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.potion.Potions;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class Camel extends Kit implements Listener {

	public List<Material> types = Arrays.asList(Material.SAND, Material.SANDSTONE);

	public Camel() {
		super(KitType.CAMEL);
		setName("§6Camel");
		setMaterial(Material.SAND);
		addLine("§bQuando andar sobre o deserto ganhe");
		addLine("§b+Velocidade e +Regeneração");

	}

	@EventHandler
	public void event(PlayerMoveEvent e) {

		Player p = e.getPlayer();
		if (hasKit(p)) {
			Material block =
				p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
			if (types.contains(block)) {
				new Potions(PotionEffectType.SPEED, 0, 20 * 5).create(p);
				new Potions(PotionEffectType.REGENERATION, 0, 20 * 5).create(p);
			}
		}
	}

}
