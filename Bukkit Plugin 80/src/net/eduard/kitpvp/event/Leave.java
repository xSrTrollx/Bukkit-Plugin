
package net.eduard.kitpvp.event;

import net.eduard.kitpvp.KitPvP;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

public class Leave extends KitPvP {

	@EventHandler
	public void event(PlayerQuitEvent e) {

		Player p = e.getPlayer();
		leave(p);
	}

}
