
package net.eduard.template.command;

import net.eduard.template.Template;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TemplateCommand extends Template implements CommandExecutor
{

	public boolean
		onCommand( CommandSender sender , Command command , String label , String[] args ) {

		return true;
	}

}
