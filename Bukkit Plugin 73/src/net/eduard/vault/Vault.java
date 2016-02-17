
package net.eduard.vault;

import net.eduard.eduard_api.EduardAPI;
import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.manager.gui.Gui;
import net.eduard.eduard_api.manager.gui.slot.Slot;
import net.eduard.eduard_api.manager.gui.util.GuiType;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Vault extends EduardAPI {

	public void onEnable() {

		Gui loja = new Gui(GuiType.LINE_3, "§8Loja");
		loja.setItem(new Item(Material.BONE));
		loja.addSlot(5, new Slot(new Item(Material.DIAMOND)) {

			public void effect(Player p) {

				if (p.hasPermission("loja.teste")) {
					Main.economy.depositPlayer(p, 200);
					p.sendMessage("§bVoce vendeu o diamante!");
					Main.permission.playerRemove(p, "loja.teste");

				} else {
					Main.economy.withdrawPlayer(p, 200);
					p.sendMessage("§aVoce comprou o diamante");
					Main.permission.playerAdd(p, "loja.teste");
				}
				p.setNoDamageTicks(20 * 10);
			};

		});
		addGui(loja);
	}
}
