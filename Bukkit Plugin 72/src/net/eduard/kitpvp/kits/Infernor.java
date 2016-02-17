
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Infernor extends Kit implements Listener {

	public Infernor() {
		super(KitType.INFERNOR);
		setName("§6Infernor");
		setMaterial(Material.NETHER_FENCE);
		addLine("§bPuxe o inimigo para uma Batalha Infernal");
		addItem(1, new Item(Material.NETHER_FENCE, "§6Infernor Fence"));

	}
}
