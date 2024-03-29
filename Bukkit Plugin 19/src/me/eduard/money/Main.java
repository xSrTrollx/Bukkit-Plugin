package me.eduard.money;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
@SuppressWarnings("deprecation")
public class Main extends JavaPlugin implements Listener {

	public static Main m;

	public static FileConfiguration cf;

	public static BukkitScheduler sh;

	public static PluginManager pm;

	public void onLoad() {
		m = this;
		cf = getConfig();
		sh = Bukkit.getScheduler();
		pm = Bukkit.getPluginManager();
		saveInicial();
	}

	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	public void onDisable() {
		HandlerList.unregisterAll();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("�cApenas para Players!");
			return true;
		}
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("money")) {
			if (args.length == 0) {
				p.sendMessage("�6Seu saldo � de " + money.get(p));
			}
		}

		return true;
	}

	public HashMap<OfflinePlayer, Integer> money = new HashMap<>();

	public void putMoney() {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			if (cf.contains("Money." + p.getName())) {
				money.put(p, cf.getInt("Money." + p.getName()));
			} else {
				money.put(p, getInicial());
			}
		}
	}

	
	public void saveMoney() {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			cf.set("Money." + p.getName(), money.get(p));
		}
		saveConfig();
	}

	public void saveInicial() {
		cf.addDefault("Inicial", 100);
		cf.options().copyDefaults(true);
		saveConfig();
	}

	public int getInicial() {
		return cf.getInt("Inicial");
	}

}
