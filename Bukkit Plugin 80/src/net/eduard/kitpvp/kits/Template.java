
package net.eduard.kitpvp.kits;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Template extends Kit implements Listener {

	public static Kit kit;

	public Template() {

		super(KitType.VIKING);

		setName("");
		setMaterial(Material.AIR);
		addLine("");
	}

}
