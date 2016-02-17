
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;

public class Archer extends Kit implements Listener {

	public Archer() {
		super(KitType.ARCHER);
		setName("§6Archer");
		setEnchant(true);
		setSkill(false);
		setMaterial(Material.BOW);
		addLine("§bGanha um Bow com Infinity 1");
		addItem(1, new Item(Material.BOW).addEnchant(Enchantment.ARROW_DAMAGE, 1));
		addItem(9, new Item(Material.ARROW, 64));

	}

}
