
package net.eduard.kitpvp.event;

import net.eduard.kitpvp.KitPvP;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Respawn extends KitPvP {

	@EventHandler
	public void event(PlayerRespawnEvent e) {

		Player p = e.getPlayer();
		kits.remove(p);
		cooldown.stop(p);
		join(p);

	}

}
