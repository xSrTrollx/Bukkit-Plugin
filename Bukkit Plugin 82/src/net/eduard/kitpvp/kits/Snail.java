
package net.eduard.kitpvp.kits;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Snail extends Kit implements Listener {

	public Snail() {
		super(KitType.SNAIL);
		setName("§6Snail");
		setMaterial(Material.WEB);
		addLine("§bQuando estiver em PvP tem");
		addLine("§b33% de chance de dar Lentidão");

	}

}
