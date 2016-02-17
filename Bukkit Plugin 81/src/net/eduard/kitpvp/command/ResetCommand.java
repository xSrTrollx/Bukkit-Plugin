
package net.eduard.kitpvp.command;

import net.eduard.kitpvp.KitPvP;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetCommand extends KitPvP implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label,
		String[] args) {

		if (onCommand(sender)) {
			Player p = (Player) sender;
			leave(p);
			join(p);
			send(p, message("reset-kit"));

		}
		return true;

	}

}
