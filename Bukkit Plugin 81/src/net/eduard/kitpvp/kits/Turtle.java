
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class Turtle extends Kit implements Listener {

	// public Turtle(){
	//
	// super( "Turtle" , Material.ANVIL );
	// setDescription( "§eAo cair de uma altura se voce" , "§eestiver segurando
	// Shift voce nao" ,
	// "§etomara nenhum dano" );
	// }
	public Turtle() {
		super(KitType.TURTLE);
		new Item(Material.DIAMOND_CHESTPLATE, "§6Turtle", "§aHABILIDADE",
			"§bAo pressionar SHIFT tome dano reduzido");

	}

	@EventHandler
	public void event(EntityDamageEvent e) {

		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (hasKit(p)) {
				if (p.isSneaking()) {
					if (e.getCause() == DamageCause.FALL) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

}
