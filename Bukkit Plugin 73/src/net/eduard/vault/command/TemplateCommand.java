
package net.eduard.vault.command;

import net.eduard.vault.Vault;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TemplateCommand extends Vault implements CommandExecutor
{

	public boolean
		onCommand( CommandSender sender , Command command , String label , String[] args ) {

		return true;
	}

}
