
package net.eduard.kitpvp.kits;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class Barbarian extends Kit implements Listener {

	public Barbarian() {

		super(KitType.BARBARIAN);
		setName("§6Barbarian");
		setMaterial(Material.WOOD_SWORD);
		addLine("§bQuando matar alguem ganhe");
		addLine("§b+1 nivel para sua Espada");
		addLine("");
		addLine("§4Limite de Nivel: §e8 §4Niveis");
	}

	@EventHandler
	public void event(EntityDeathEvent e) {

		if (e.getEntity().getKiller() != null) {
			Player d = e.getEntity().getKiller();
			if (hasKit(d)) {
				if (d.getItemInHand() != null) {
					Material type = d.getItemInHand().getType();
					switch (type) {
					case WOOD_SWORD:
						d.getItemInHand().setType(Material.STONE_SWORD);
						send(d, getTag() + "§3Nivel 1!");
						break;
					case STONE_SWORD:
						d.getItemInHand().setType(Material.IRON_SWORD);
						send(d, getTag() + "§3Nivel 2!");
						break;
					case IRON_SWORD:
						d.getItemInHand().setType(Material.DIAMOND_SWORD);
						send(d, getTag() + "§3Nivel 3!");
						break;
					case DIAMOND_SWORD:
						ItemMeta meta = d.getItemInHand().getItemMeta();
						if (meta.hasEnchant(Enchantment.DAMAGE_ALL)) {
							int level = meta.getEnchantLevel(Enchantment.DAMAGE_ALL);
							switch (level) {
							case 1:
								meta.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
								send(d, getTag() + "§3Nivel 5!");
								break;
							case 2:
								meta.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
								send(d, getTag() + "§3Nivel 6!");
								break;
							case 3:
								meta.addEnchant(Enchantment.DAMAGE_ALL, 4, true);
								send(d, getTag() + "§3Nivel 7!");
								break;
							case 4:
								meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
								send(d, getTag() + "§3Nivel 8!");
								break;
							case 5:
								send(d, getTag() + "§3Nivel MAX!");
								break;
							}

						} else {
							meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
							send(d, getTag() + "§3Nivel 4!");
						}
						d.getItemInHand().setItemMeta(meta);

						break;
					default:
						break;
					}
				}
			}
		}
	}

}
