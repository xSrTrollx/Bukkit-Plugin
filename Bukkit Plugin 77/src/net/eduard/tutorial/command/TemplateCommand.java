
package net.eduard.tutorial.command;

import net.eduard.tutorial.Tutorial;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TemplateCommand extends Tutorial implements CommandExecutor
{

	public boolean
		onCommand( CommandSender sender , Command command , String label , String[] args ) {

		return true;
	}

}
