
package net.eduard.kitpvp.kit;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.packet.item.CraftItemStack;
import net.eduard.eduard_api.time.delay.Delay;
import net.eduard.kitpvp.kits.Anchor;
import net.eduard.kitpvp.kits.Archer;
import net.eduard.kitpvp.kits.Barbarian;
import net.eduard.kitpvp.kits.Berserker;
import net.eduard.kitpvp.kits.Boxer;
import net.eduard.kitpvp.kits.C4;
import net.eduard.kitpvp.kits.Camel;
import net.eduard.kitpvp.kits.Confuser;
import net.eduard.kitpvp.kits.Critical;
import net.eduard.kitpvp.kits.DarkMage;
import net.eduard.kitpvp.kits.EnderMage;
import net.eduard.kitpvp.kits.FireMan;
import net.eduard.kitpvp.kits.Fisherman;
import net.eduard.kitpvp.kits.Flash;
import net.eduard.kitpvp.kits.Frosty;
import net.eduard.kitpvp.kits.Ghoul;
import net.eduard.kitpvp.kits.Gladiator;
import net.eduard.kitpvp.kits.Grappler;
import net.eduard.kitpvp.kits.Hulk;
import net.eduard.kitpvp.kits.Hunter;
import net.eduard.kitpvp.kits.Infernor;
import net.eduard.kitpvp.kits.Kangaroo;
import net.eduard.kitpvp.kits.Monk;
import net.eduard.kitpvp.kits.Ninja;
import net.eduard.kitpvp.kits.Poseidon;
import net.eduard.kitpvp.kits.PvP;
import net.eduard.kitpvp.kits.Scout;
import net.eduard.kitpvp.kits.Snail;
import net.eduard.kitpvp.kits.Specialist;
import net.eduard.kitpvp.kits.Stomper;
import net.eduard.kitpvp.kits.Switcher;
import net.eduard.kitpvp.kits.Thor;
import net.eduard.kitpvp.kits.Turtle;
import net.eduard.kitpvp.kits.Urgal;
import net.eduard.kitpvp.kits.Viking;
import net.eduard.kitpvp.kits.Viper;
import net.eduard.kitpvp.kits.Whiter;
import net.eduard.kitpvp.kits.Wolfman;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public enum KitType {

		ANCHOR(Anchor.class), ARCHER(Archer.class), BARBARIAN(Barbarian.class),
		BERSERKER(Berserker.class), BOXER(Boxer.class), C4(C4.class),
		CAMEL(Camel.class), CONFUSER(Confuser.class), CRITICAL(Critical.class),
		DARKMAGE(DarkMage.class), ENDERMAGE(EnderMage.class), FIREMAN(FireMan.class),
		FISHERMAN(Fisherman.class), FLASH(Flash.class), FROSTY(Frosty.class),
		GHOUL(Ghoul.class), GLADIATOR(Gladiator.class), GRAPPLER(Grappler.class),
		HULK(Hulk.class), HUNTER(Hunter.class), INFERNOR(Infernor.class),
		KANGAROO(Kangaroo.class), MONK(Monk.class), NINJA(Ninja.class),
		POSEIDON(Poseidon.class), PVP(PvP.class), SCOUT(Scout.class),
		SNAIL(Snail.class), SPEACIALIST(Specialist.class), SWITCHER(Switcher.class),
		STOMPER(Stomper.class), THOR(Thor.class), TURTLE(Turtle.class),
		URGAL(Urgal.class), VIKING(Viking.class), VIPER(Viper.class),
		WHITER(Whiter.class), WOLFMAN(Wolfman.class);

	private ArrayList<Player> players = new ArrayList<>();

	private String name = name().toLowerCase();

	private int data = 0;

	private Material material = Material.STONE_SWORD;

	private ArrayList<String> description = new ArrayList<>();

	private HashMap<Integer, Item> items = new HashMap<>();

	private boolean hasSword;

	private boolean enchant;

	private boolean skill = true;

	private boolean hasCooldown;

	private int cooldown;

	private Kit kit;

	private KitType() {
	}

	private KitType(Class<? extends Kit> clazz) {
		new Delay(1L) {

			public void effect() {

				try {
					kit = clazz.newInstance();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		};

	}

	public void addItem(int id, Item item) {

		items.put(id, item);
	}

	public void addLine(String message) {

		description.add(message);
	}

	public int getCooldown() {

		return cooldown;
	}

	public int getData() {

		return data;
	}

	public ArrayList<String> getDescription() {

		return description;
	}

	public ItemStack getIcon() {

		Item item = new Item(getMaterial(), 1, getData(),
			"      §e>>    " + getName() + "     §e<<      ");
		ArrayList<String> lines = new ArrayList<>();
		lines.add("");
		if (isSkill()) {
			lines.add("          §a§lHabilidade         ");
		} else {
			lines.add("    §4Nao Possui Habilidade    ");
			lines.add("");
			lines.add("          §a§lBonus          ");
		}
		lines.add("");
		lines.addAll(getDescription());
		if (isHasCooldown()) {
			lines.add("");
			lines.add("§4Cooldown: §e" + getCooldown() + " §4segundos");
		}
		item.setLore(lines);
		CraftItemStack craft = new CraftItemStack(item);
		if (isEnchant()) {
			try {
				craft.addGlow();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return craft.getItemStack();
	}

	public HashMap<Integer, Item> getItems() {

		return items;
	}

	public Kit getKit() {

		return kit;
	}

	public Material getMaterial() {

		return material;
	}

	public String getName() {

		return name;
	}

	public ArrayList<Player> getPlayers() {

		return players;
	}

	public boolean hasSword() {

		return hasSword;
	}

	public boolean isEnchant() {

		return enchant;
	}

	public boolean isHasCooldown() {

		return hasCooldown;
	}

	public boolean isSkill() {

		return skill;
	}

	public void setCooldown(int cooldown) {

		this.cooldown = cooldown;
	}

	public void setData(int data) {

		this.data = data;
	}

	public void setEnchant(boolean enchant) {

		this.enchant = enchant;
	}

	public void setHasCooldown(boolean hasCooldown) {

		this.hasCooldown = hasCooldown;
	}

	public void setHasSword(boolean hasSword) {

		this.hasSword = hasSword;
	}

	public void setMaterial(Material material) {

		this.material = material;
	}

	public void setName(String name) {

		this.name = name;
	}

	public void setSkill(boolean skill) {

		this.skill = skill;
	}

}
