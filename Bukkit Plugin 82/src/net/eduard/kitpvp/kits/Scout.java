
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class Scout extends Kit implements Listener {

	public Scout() {
		super(KitType.SCOUT);
		setName("§6Scout");
		setMaterial(Material.POTION);
		addLine("§bGanhe +2 Poções de Velocidade");
		setSkill(false);
		addItem(1, new Item(new Potion(PotionType.SPEED, 2).toItemStack(2))
			.setName("§6Scout Bonus"));

	}

}