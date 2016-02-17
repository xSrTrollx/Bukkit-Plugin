
package net.eduard.kitpvp.event;

import net.eduard.kitpvp.KitPvP;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join extends KitPvP{

	@EventHandler
	public void event(PlayerJoinEvent e) {

		Player p = e.getPlayer();
		join(p);
	}

}
