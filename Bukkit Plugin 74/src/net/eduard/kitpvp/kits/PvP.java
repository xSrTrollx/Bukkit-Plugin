
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;

public class PvP extends Kit implements Listener {

	public PvP() {
		super(KitType.PVP);
		setName("§6PvP");
		setMaterial(Material.STONE_SWORD);
		addLine("§bGanhe uma Espada Melhor");
		addItem(1, new Item(getSword()).addEnchant(Enchantment.DAMAGE_ALL, 1));
		setSkill(false);
	}

}
