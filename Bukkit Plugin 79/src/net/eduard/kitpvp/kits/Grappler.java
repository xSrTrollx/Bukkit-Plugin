
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Grappler extends Kit implements Listener {

	public Grappler() {
		super(KitType.GRAPPLER);
		setName("§6Grappler");
		setMaterial(Material.LEASH);
		addLine("§bUse a corda para ser puxado onde acertar");
		addItem(1, new Item(Material.LEASH, "§6Grappler Hook"));

	}
}
