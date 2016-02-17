
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Monk extends Kit implements Listener {

	public Monk() {
		super(KitType.MONK);
		setName("§6Monk");
		setMaterial(Material.BLAZE_ROD);
		addLine("§bAtrapalhe o PvP do Inimigo mechendo os");
		addLine("§bItems no Inventario dele");
		addItem(1, new Item(Material.BLAZE_POWDER, "§6Monk Power"));

	}
}
