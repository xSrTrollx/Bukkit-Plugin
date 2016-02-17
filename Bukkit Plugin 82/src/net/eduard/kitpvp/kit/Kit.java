
package net.eduard.kitpvp.kit;

import net.eduard.eduard_api.game.item.Item;

import net.eduard.kitpvp.KitPvP;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class Kit extends KitPvP{

	private KitType type;

	public Kit(KitType type) {
		setType(type);

	}

	public void addItem(int id, Item item) {

		type.addItem(id, item);
	}

	public void addLine(String message) {

		type.addLine(message);
	}

	public int getCooldown() {

		return type.getCooldown();
	}

	public int getData() {

		return type.getData();
	}

	public ArrayList<String> getDescription() {

		return type.getDescription();
	}

	public HashMap<Integer, Item> getItems() {

		return type.getItems();
	}

	public boolean getKit(Player p) {

		return kits.get(p) == getType();
	}

	public Material getMaterial() {

		return type.getMaterial();
	}

	public String getName() {

		return type.getName();
	}

	public ArrayList<Player> getPlayers() {

		return type.getPlayers();
	}

	public String getTag() {

		String name = type.name().toLowerCase();
		return "§8[§b" + name.toUpperCase().substring(0, 1) + name.substring(1)
			+ "§8] ";
	}

	public KitType getType() {

		return type;
	}

	public boolean hasKit(Player p) {

		return getPlayers().contains(p);
	}

	public boolean hasSword() {

		return type.hasSword();
	}

	public boolean isEnchant() {

		return type.isEnchant();
	}

	public boolean isHasCooldown() {

		return type.isHasCooldown();
	}

	public boolean isSkill() {

		return type.isSkill();
	}

	public void setCooldown(int cooldown) {

		type.setCooldown(cooldown);
	}

	public void setData(int data) {

		type.setData(data);
	}

	public void setEnchant(boolean enchant) {

		type.setEnchant(enchant);
	}

	public void setHasCooldown(boolean hasCooldown) {

		type.setHasCooldown(hasCooldown);
	}

	public void setHasSword(boolean hasSword) {

		type.setHasSword(hasSword);
	}

	public void setMaterial(Material material) {

		type.setMaterial(material);
	}

	public void setName(String name) {

		type.setName(name);
	}

	public void setSkill(boolean skill) {

		type.setSkill(skill);
	}

	public void setType(KitType type) {

		this.type = type;
	}

}
