
package net.eduard.kitpvp.command;

import net.eduard.eduard_api.game.item.Item;

import net.eduard.kitpvp.KitPvP;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class KitCommand extends KitPvP {

	public boolean onCommand(CommandSender sender, Command command, String label,
		String[] args) {

		if (onCommand(sender)) {
			Player p = (Player) sender;
			if (hasKits(p)) {
				send(p, "§cVoce ja possui um kit!");
				return true;
			}
			if (args.length == 0) {
				send(p, "§c/kit <kit>");
			} else {
				for (KitType kit : KitType.values()) {
					if (kit.name().equalsIgnoreCase(args[0])) {
						PlayerInventory inv = p.getInventory();
						Item soup = getSoup();
						inv.addItem(getSword());

						// SOPAS
						for (ItemStack item : inv.getContents()) {
							if (item == null) {
								inv.addItem(soup);
							}
						}
						// ITEMS
						for (Entry<Integer, Item> items : kit.getItems().entrySet()) {
							try {
								inv.setItem(items.getKey(), items.getValue());
							} catch (Exception ex) {
							}
						}
						// SETANDO KIT
						KitPvP.kits.put(p, kit);
						kit.getPlayers().add(p);
						send(p, message("kit").replace("$kit",
							kit.name().toLowerCase()));
						return true;
					}
				}
				send(p, message("no_kit").replace("$kit", args[0]));
			}

		}
		return true;

	}

	public List<String> onTabComplete(CommandSender sender, Command command,
		String alias, String[] args) {

		if (args.length == 1) {
			ArrayList<String> list = new ArrayList<>();
			for (KitType type : KitType.values()) {
				if (type.name().startsWith(args[0])) {
					list.add(type.name());
				}
			}
			return list;
		}
		return null;
	}

}
