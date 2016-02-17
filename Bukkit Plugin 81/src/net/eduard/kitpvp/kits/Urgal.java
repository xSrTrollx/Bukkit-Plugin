
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class Urgal extends Kit implements Listener {

	@SuppressWarnings("deprecation")
	public Urgal() {
		super(KitType.URGAL);
		setName("§6Urgal");
		setMaterial(Material.POTION);
		addLine("§bGanhe uma poçao de forca");
		addItem(1,
			new Item(new Potion(PotionType.STRENGTH, 1, false).toItemStack(3)));

	}
}
