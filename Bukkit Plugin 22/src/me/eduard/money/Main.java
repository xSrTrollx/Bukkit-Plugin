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
		saveMoney();
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
			if (args.length == 1) {
				Player target = Bukkit.getPlayerExact(args[0]);
				if (target == null) {
					p.sendMessage("�cEsse player n�o existe!");
					return true;
				}
				p.sendMessage("�cSaldo do player " + target.getName() + " "
						+ money.get(target));
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")) {
					// /money help
					p.sendMessage("�b---------HELP-------");
					p.sendMessage("�6/money pay <qnt> <target>");
					p.sendMessage("�6/money take <qnt> <target>");
					p.sendMessage("�6/money add <qnt> <target>");
					p.sendMessage("�6/money set <qnt> <target>");
					p.sendMessage("�b---------HELP-------");
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("set")) {
					// /money set 100
					try {
						Integer numero = Integer.valueOf(args[2]);
						money.put(p, numero);
						p.sendMessage("�6Seu saldo: " + numero);
					} catch (Exception ex) {
						p.sendMessage("�cIsto n�o � um Numero!");
					}
				}
				if (args[0].equalsIgnoreCase("take")) {
					// /money take 100
					try {
						Integer numero = Integer.valueOf(args[1]);
						money.put(p,
								money.get(p) - numero < 0 ? 0 : money.get(p)
										- numero);
						p.sendMessage("�6Seu saldo: " + numero
								+ " Numero retirado: "
								+ (money.get(p) - numero));
					} catch (Exception ex) {
						p.sendMessage("�cIsto n�o � um Numero!");
					}
				}
				if (args[0].equalsIgnoreCase("add")) {
					// /money add 100
					try {
						Integer numero = Integer.valueOf(args[1]);
						money.put(p, money.get(p) + numero);
						p.sendMessage("�6Seu saldo: " + numero
								+ " Numero adicionado: " + money.get(p)
								+ numero);
					} catch (Exception ex) {
						p.sendMessage("�cIsto n�o � um Numero!");
					}
				}
			}
			if (args.length == 3) {
				if (args[0].equalsIgnoreCase("pay")) {
					Player target = Bukkit.getPlayerExact(args[2]);
					if (target == null) {
						p.sendMessage("�cEsse player n�o existe!");
						return true;
					}
					// /money pay edu 2015
					try {
						Integer numero = Integer.valueOf(args[1]);
						if (numero > money.get(p)) {
							p.sendMessage("�cDinheiro insuficiente!");
							return true;
						}
						if (p.equals(target)) {
							p.sendMessage("�6N�o pode se pagar!");
							return true;
						}
						money.put(p, money.get(p) - numero);
						money.put(target, money.get(target) + numero);
						target.sendMessage("�6O player " + p.getName()
								+ " te pagou " + numero + " de dinheiro!");
						p.sendMessage("�6Saldo do player " + target.getName()
								+ " " + numero);
						p.sendMessage("�6Voce pagou " + numero
								+ " para o player " + target.getName());
						target.sendMessage("�6Seu saldo: " + money.get(target));
						p.sendMessage("�6Seu saldo: " + money.get(target));
					} catch (Exception ex) {
						p.sendMessage("�cIsto n�o � um Numero!");
					}
				}
				if (args[0].equalsIgnoreCase("set")) {
					Player target = Bukkit.getPlayerExact(args[2]);
					if (target == null) {
						p.sendMessage("�cEsse player n�o existe!");
						return true;
					}
					// /money set 2015 edu
					try {
						Integer numero = Integer.valueOf(args[1]);
						money.put(target, numero);
						p.sendMessage("�6Saldo do player " + target.getName()
								+ " " + numero);
					} catch (Exception ex) {
						p.sendMessage("�cIsto n�o � um Numero!");
					}
				}
				if (args[0].equalsIgnoreCase("take")) {
					Player target = Bukkit.getPlayerExact(args[2]);
					if (target == null) {
						p.sendMessage("�cEsse player n�o existe!");
						return true;
					}
					// /money take 100 edu
					try {
						Integer numero = Integer.valueOf(args[1]);
						money.put(target, money.get(target) - numero < 0 ? 0
								: money.get(target) - numero);
						p.sendMessage("�6Saldo do player " + target.getName()
								+ " " + numero + " Numero retirado: "
								+ (money.get(target) - numero));
					} catch (Exception ex) {
						p.sendMessage("�cIsto n�o � um Numero!");
					}
				}
				if (args[0].equalsIgnoreCase("add")) {
					Player target = Bukkit.getPlayerExact(args[2]);
					if (target == null) {
						p.sendMessage("�cEsse player n�o existe!");
						return true;
					}
					// /money add 100 edu
					try {
						Integer numero = Integer.valueOf(args[1]);
						money.put(target, money.get(target) + numero);
						p.sendMessage("�6Saldo do player " + target.getName()
								+ " " + numero + " Numero adicionado: "
								+ money.get(target) + numero);
					} catch (Exception ex) {
						p.sendMessage("�cIsto n�o � um Numero!");
					}
				}
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
