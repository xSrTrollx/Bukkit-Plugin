package me.eduard.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.ScoreboardManager;

public class Main extends JavaPlugin implements Listener {

	public Main main = this;
	public Server server = Bukkit.getServer();
	public ScoreboardManager score = Bukkit.getScoreboardManager();
	public FileConfiguration cf = getConfig();
	public PluginManager pm = Bukkit.getPluginManager();
	public BukkitScheduler sh = Bukkit.getScheduler();
	public CommandSender send = Bukkit.getConsoleSender();

	public void onLoad() {

	}
	@SuppressWarnings("unused")
	public void onEnable() {
		PluginCommand cmd1 = getCommand( "simples" );
		PluginCommand cmd2 = Bukkit.getPluginCommand( "simples" );
		cmd1.setPermission( "simples.use" );
		cmd1.setPermissionMessage(ChatColor.RED + "Voce nao tem permissao!" );
		cmd1.setUsage( ChatColor.RED +"Use /simples <nick>" );
		cmd1.setExecutor( new SimplesCommand() );
	}
	
	public void onDisable() {
		HandlerList.unregisterAll();
	}

	public List<String> onTabComplete(CommandSender sender, Command command,
			String alias, String[] args) {
		return null;
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("�cApenas para players!");
			return true;
		}
//		Player p = (Player) sender;
		
		
		return true;
	}
}
