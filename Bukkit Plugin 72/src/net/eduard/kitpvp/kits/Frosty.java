
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

public class Frosty extends Kit implements Listener {

	public Frosty() {
		super(KitType.FROSTY);
		setName("§6Frosty");
		setMaterial(Material.SNOW_BLOCK);
		addLine("§bQuando andar sobre o Gelo ganhe");
		addLine("§b+Velocidade e +Regeneração");

	}

	@EventHandler
	public void onPlayerMoveEvent(PlayerMoveEvent e) {

		Player p = e.getPlayer();
		Material type = e.getTo().getBlock().getRelative(BlockFace.DOWN).getType();
		if (hasKit(p)) {
			if (type == Material.ICE | type == Material.PACKED_ICE
				| type == Material.SNOW_BLOCK) {
				new Potions(PotionEffectType.SPEED, 0, 20 * 5).create(p);
				new Potions(PotionEffectType.REGENERATION, 0, 20 * 5).create(p);
			}
		}
	}

}
