
package net.eduard.kitpvp;

import net.eduard.eduard_api.EduardAPI;
import net.eduard.eduard_api.game.Game;
import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.manager.gui.Gui;
import net.eduard.eduard_api.manager.gui.slot.Slot;
import net.eduard.eduard_api.manager.gui.util.GuiType;
import net.eduard.eduard_api.time.cooldown.player.Cooldown;
import net.eduard.eduard_api.time.delay.Delay;
import net.eduard.kitpvp.command.KitCommand;
import net.eduard.kitpvp.command.ResetCommand;
import net.eduard.kitpvp.event.Join;
import net.eduard.kitpvp.event.KillStreak;
import net.eduard.kitpvp.event.Leave;
import net.eduard.kitpvp.event.Respawn;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class KitPvP extends EduardAPI {

	public static Cooldown cooldown = new Cooldown();

	public static HashMap<Player, Integer> kill_streak = new HashMap<>();

	public static HashMap<Player, KitType> kits = new HashMap<>();

	public static HashMap<Player, Gui> kits_selector = new HashMap<>();

	public static HashMap<Player, Gui> kits_shop = new HashMap<>();

	public KitPvP() {

		super(Main.class);
	}

	public Item getEmptySoup() {

		return getItem("empty_soup");
	}

	public int getKitPrice(KitType kit) {

		return getInt("kits." + kit.name() + ".price");
	}

	public ArrayList<Integer> getNumbers() {

		ArrayList<Integer> list = new ArrayList<>();
		// for (int i = 0; i < 9; i++) {
		// list.add(i);
		// }
		for (int i = 0; i < 6; i++) {
			list.add(i * 9);
		}
		for (int i = 1; i < 7; i++) {
			list.add(i * 9 - 1);
		}
		return list;
	}

	public Item getSelector() {

		return getItem("item_selector");
	}

	public Item getShop() {

		return getItem("item_shop");
	}

	public Item getSoup() {

		return getItem("soup");
	}

	public Item getSword() {

		return getItem("sword");
	}

	public boolean hasKits(Player p) {

		return kits.containsKey(p);
	}

	public void join(Player p) {

		Game g = new Game(p);
		g.clearInventory();
		g.refreshAll();
		p.getInventory().addItem(getShop(), getSelector());
		kill_streak.put(p, 0);
		kit(p);
	}

	public void kit(Player p) {

		Gui gui_selector = new Gui(GuiType.LINE_6, message("gui_selector"));
		Gui gui_shop = new Gui(GuiType.LINE_6, message("gui_shop"));
		Item vidro = new Item(Material.STAINED_GLASS_PANE, "§6KitPvP");
		gui_shop.setPlayer(p);
		gui_shop.setItem(getShop());
		gui_selector.setPlayer(p);
		gui_selector.setItem(getSelector());
		int selector_id = 0;
		int shop_id = 0;
		for (Integer id : getNumbers()) {
			gui_shop.addSlot(id, new Slot(vidro) {

				public void effect(Player p) {

				};
			});
			gui_selector.addSlot(id, new Slot(vidro) {

				public void effect(Player p) {

				};
			});
		}

		for (KitType kitType : KitType.values()) {
			ItemStack item = kitType.getIcon();
			if (item == null) {
				continue;
			}
			if (item.getType() == Material.AIR) {
				continue;
			}

			if (hasVault()) {
				if (!p
					.hasPermission("kitpvp.kits." + kitType.name().toLowerCase())) {
					Slot slot = new Slot(item) {

						public void effect(Player p) {

							super.effect(p);
							int price = getKitPrice(kitType);
							if (getVault().getEconomy().has(p, price)) {
								getVault().getPermission().playerAdd(p,
									"kitpvp.kits." + kitType.name().toLowerCase());
								send(p, message("buy_kit").replace("$kit",
									kitType.getName()));
								getVault().getEconomy().withdrawPlayer(p, price);
								kit(p);
							} else {
								send(p,
									message("no_buy_kit")
										.replace("$kit", kitType.getName()).replace(
											"$price", "" + getKitPrice(kitType)));
							}
						}
					};
					slot.setCloseInventory(true);
					while (getNumbers().contains(shop_id)) {
						shop_id++;
					}
					gui_shop.addSlot(shop_id, slot);
					shop_id++;

					continue;
				}
			}
			Slot slot = new Slot(item) {

				public void effect(Player p) {

					super.effect(p);
					p.chat("/kit " + kitType.name());

				}

			};
			slot.setClearInventory(true);
			slot.setClearArmour(true);
			slot.setRefresh(true);
			slot.setCloseInventory(true);
			while (getNumbers().contains(selector_id)) {
				selector_id++;
			}
			gui_selector.addSlot(selector_id, slot);
			selector_id++;

		}

		if (kits_selector.containsKey(p)) {
			removeGui(kits_selector.get(p));
		}
		if (kits_shop.containsKey(p)) {
			removeGui(kits_shop.get(p));
		}
		addGui(gui_shop);
		addGui(gui_selector);
		kits_selector.put(p, gui_selector);
		kits_shop.put(p, gui_shop);
	}

	public void leave(Player p) {

		kits.remove(p);
		for (KitType kit : KitType.values()) {
			kit.getPlayers().remove(p);
		}
		cooldown.stop(p);
		removeGui(kits_selector.get(p));
		removeGui(kits_shop.get(p));
		kits_selector.remove(p);
		kits_shop.remove(p);
		kill_streak.remove(p);
	}

	public void onEnable() {

		addItem("sword", new Item(Material.WOOD_SWORD, "§6Espada KitPvP"));
		addItem("empty_soup", new Item(Material.BOWL, "§6Sopa"));
		addItem("soup", new Item(Material.MUSHROOM_SOUP, "§6Sopa"));
		addItem("item_shop", new Item(Material.EMERALD, "§a§lLoja"));
		addItem("item_selector", new Item(Material.CHEST, "§6§lKits"));
		add("no_kit", "&cNao existe esse kit &f$kit");
		add("kit", "&6Voce pegou esse kit &a$kit");
		add("reset_kit", "&6Voce resetou agora pode escolher um kit novamente!");
		add("buy_kit", "&62oce comprou o kit &a$kit &2pagando &a$price &2moedas!");
		add("no_buy_kit",
			"&cVoce precisa de &f$price &cmoedas para comprar o kit &a$kit");
		add("gui_selector", "&7Seus Kits");
		add("gui_shop", "&7Kits Disponiveis");
		saveDefault();
		for (KitType kit : KitType.values()) {
			add("kits." + kit.name() + ".price", 1000);
		}
		saveDefault();
		setCommand("resetkit", "kitpvp.reset", new ResetCommand());
		setCommand("kit", "kitpvp.kit", new KitCommand());
		new Join();
		new Leave();
		new KillStreak();
		new Respawn();
		resetPlayers();

	}

	public void resetPlayers() {

		new Delay(2L) {

			public void effect() {

				for (Player p : getOnlinePlayers()) {
					leave(p);
					join(p);
				}
			}

		};
	}

	public void skillCooldownMessage(Player p) {

		send(p, "§cVoce precisa esperar mais $cooldown segundos!"
			.replace("$cooldown", "" + cooldown.getTime(p)));
	}

	public boolean skillOnCooldown(Player p) {

		return cooldown.has(p);
	}

	public void skillStarCooldown(Player p, int seconds) {

		cooldown.start(p, seconds, "§aSua habilidade esta recarregada!");
	}

}
