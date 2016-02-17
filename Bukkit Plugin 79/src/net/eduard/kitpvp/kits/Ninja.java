
package net.eduard.kitpvp.kits;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Ninja extends Kit implements Listener {

	public Ninja() {
		super(KitType.NINJA);
		setName("§6Ninja");
		setMaterial(Material.NETHER_STAR);
		addLine("§bEntre em PvP e o SHIFT para");
		addLine("§bTeleportar atraz do seu Inimigo");

	}
}
