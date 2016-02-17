
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Kangaroo extends Kit implements Listener {

	public Kangaroo() {
		super(KitType.KANGAROO);
		setName("§6Kangaroo");
		setMaterial(Material.FIREWORK);
		addLine("§bFaça Pulo duplo usando Boost");
		addItem(1, new Item(Material.FIREWORK, "§6Kangaroo Boost"));

	}
}
