
package net.eduard.kitpvp.event;

import net.eduard.kitpvp.KitPvP;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillStreak extends KitPvP  {

	@EventHandler
	public void event(EntityDeathEvent e) {

		if (e.getEntity() instanceof Player & e.getEntity().getKiller() != null) {
			Player p = e.getEntity().getKiller();
			kill_streak.put(p, kill_streak.get(p) + 1);
		}
	}
}
