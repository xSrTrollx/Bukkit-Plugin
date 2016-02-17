
package net.eduard.kitpvp.kits;

import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.time.TimeEffect;
import net.eduard.eduard_api.time.delay.Delay;
import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class Gladiator extends Kit implements Listener {

	public static class GladiatorInfo {

		private ArrayList<Location> locations;

		private Player firstPlayer;

		private Player lastPlayer;

		private Location firstLocation;

		private Location lastLocation;

		public Location getFirstLocation() {

			return firstLocation;
		}

		public Player getFirstPlayer() {

			return firstPlayer;
		}

		public Location getLastLocation() {

			return lastLocation;
		}

		public Player getLastPlayer() {

			return lastPlayer;
		}

		public ArrayList<Location> getLocations() {

			return locations;
		}

		public void setFirstLocation(Location firstLocation) {

			this.firstLocation = firstLocation;
		}

		public void setFirstPlayer(Player firstPlayer) {

			this.firstPlayer = firstPlayer;
		}

		public void setLastLocation(Location lastLocation) {

			this.lastLocation = lastLocation;
		}

		public void setLastPlayer(Player lastPlayer) {

			this.lastPlayer = lastPlayer;
		}

		public void setLocations(ArrayList<Location> locations) {

			this.locations = locations;
		}
	}

	public static HashMap<Player, GladiatorInfo> gladiators = new HashMap<>();

	public Gladiator() {
		super(KitType.GLADIATOR);
		setName("§6Gladiator");
		setMaterial(Material.IRON_FENCE);
		addLine("§bPuxe o inimigo para uma Batalha desafiadora");
		addItem(1, new Item(Material.IRON_FENCE, "§6Gladiator Fence"));

	}

	@EventHandler
	public void onBlockBreakEvent(BlockBreakEvent e) {

		Player p = e.getPlayer();
		if (e.getBlock().getType() == Material.GLASS
			& p.getGameMode() != GameMode.CREATIVE & gladiators.containsKey(p)) {
			e.getBlock().setType(Material.BEDROCK);
			new Delay(20 + 10) {

				public void effect() {

					e.getBlock().setType(Material.GLASS);
				}

			};
			return;
		}
	}

	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent e) {

		Player p = e.getEntity();
		if (gladiators.containsKey(p)) {
			GladiatorInfo glad = gladiators.get(p);
			if (glad.getFirstPlayer().equals(p)) {
				Player c = glad.getLastPlayer();
				c.teleport(glad.getLastLocation());
				p.teleport(glad.getFirstLocation());
				c.sendMessage(
					"§8[§bGladiator§8] §aSeu inimigo morreu: §e" + p.getName());
				c.sendMessage("§8[§bGladiator§8] §aVoce venceu a batalha!");
				p.sendMessage(
					"§8[§bGladiator§8] §aSeu inimigo te matou: §e" + c.getName());
				p.sendMessage("§8[§bGladiator§8] §aVoce perdeu a batalha!");
				gladiators.remove(p);
				gladiators.remove(c);
			} else {
				Player c = glad.getFirstPlayer();
				p.teleport(glad.getLastLocation());
				c.teleport(glad.getFirstLocation());
				c.sendMessage(
					"§8[§bGladiator§8] §aSeu inimigo morreu: §e" + p.getName());
				c.sendMessage("§8[§bGladiator§8] §aVoce venceu a batalha!");
				p.sendMessage(
					"§8[§bGladiator§8] §aSeu inimigo te matou: §e" + c.getName());
				p.sendMessage("§8[§bGladiator§8] §aVoce perdeu a batalha!");
				gladiators.remove(p);
				gladiators.remove(c);

			}

			for (Location loc : glad.getLocations()) {
				loc.getBlock().setType(Material.AIR);
			}
		}
	}

	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent e) {

		Player p = e.getPlayer();
		if (p.getItemInHand() == null) {
			return;
		}
		if (hasKit(p)) {
			if (e.getRightClicked() instanceof Player) {
				Item gladiator = new Item(Material.IRON_FENCE, "§6Gladiator Fence");
				Player c = (Player) e.getRightClicked();

				if (new Item(p.getItemInHand()).isSimilar(gladiator)) {
					e.setCancelled(true);
					if (gladiators.containsKey(c)) {
						p.sendMessage(
							"§8[§bGladiator§8] §aEsse jogador ja esta participando de uma batalha!");
						return;
					}
					if (gladiators.containsKey(p)) {
						p.sendMessage(
							"§8[§bGladiator§8] §aVoce nao pode entrar em duas batalhas!");
						return;
					}
					Location loc1 = p.getLocation();
					Location loc2 = c.getLocation();
					loc1.add(0, 20, 0);
					ArrayList<Location> alllocs = getBlocksBox(loc1.clone(), 10, 10);
					ArrayList<Location> removedlocs =
						getBlocksBox(loc1.clone().add(0, 1, 0), 8, 8);
					alllocs.removeAll(removedlocs);
					for (Location location : alllocs) {
						location.getBlock().setType(Material.GLASS);
					}
					p.teleport(loc1.clone().add(5, 1, 5));
					c.teleport(loc1.clone().add(-5, 1, -5));
					p.sendMessage(
						"§8[§bGladiator§8] §aA batalha começara em 5 segundos!");
					c.sendMessage(
						"§8[§bGladiator§8] §aA batalha começara em 5 segundos!");
					p.setHealth(p.getMaxHealth());
					c.setHealth(c.getMaxHealth());
					c.setNoDamageTicks(20 * 5);
					p.setNoDamageTicks(20 * 5);
					GladiatorInfo glad = new GladiatorInfo();
					glad.setFirstLocation(loc1);
					glad.setLastLocation(loc2);
					glad.setFirstPlayer(p);
					glad.setLastPlayer(c);
					glad.setLocations(alllocs);
					gladiators.put(p, glad);
					gladiators.put(c, glad);
					new TimeEffect(6, 20) {

						public void effect() {

							if (getTime() == 1) {
								p.sendMessage("§8[§bGladiator§8] §aA batalha começou!");
								c.sendMessage("§8[§bGladiator§8] §aA batalha começou!");
								p.sendMessage(
									"§8[§bGladiator§8] §aA batalha vai acabar daqui 3min!");
							} else {
								p.sendMessage(
									"§8[§bGladiator§8] §aA batalha começara em "
										+ getTime() + " segundos!");
								c.sendMessage(
									"§8[§bGladiator§8] §aA batalha começara em "
										+ getTime() + " segundos!");
							}

						}
					};
				}
			}
		}
	}

	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent e) {

		Player p = e.getPlayer();
		if (e.getItem() == null) {
			return;
		}
		if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.GLASS
				& p.getGameMode() != GameMode.CREATIVE & gladiators.containsKey(p)) {
			e.getClickedBlock().setType(Material.BEDROCK);
			new Delay(20 + 10) {

				public void effect() {

					e.getClickedBlock().setType(Material.GLASS);
				}

			};
			return;
		}
		}

		if (hasKit(p)) {
			Item gladiator = new Item(Material.IRON_FENCE, "§6Gladiator Fence");
			if (new Item(e.getItem()).isSimilar(gladiator)) {
				e.setCancelled(true);
				if (!gladiators.containsKey(p)) {
					p.sendMessage(
						"§8[§bGladiator§8] §aVoce precisa clicar em um jogador!");
				}
			}
		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuitEvent(PlayerQuitEvent e) {

		Player p = e.getPlayer();
		if (gladiators.containsKey(p)) {
			GladiatorInfo glad = gladiators.get(p);
			if (glad.getFirstPlayer().equals(p)) {
				Player c = glad.getLastPlayer();
				c.teleport(glad.getLastLocation());
				p.teleport(glad.getFirstLocation());
				c.sendMessage(
					"§8[§bGladiator§8] §aSeu inimigo saiu: §e" + p.getName());
				c.sendMessage("§8[§bGladiator§8] §aVoce venceu a batalha!");
				gladiators.remove(p);
				gladiators.remove(c);
			} else {
				Player c = glad.getFirstPlayer();
				p.teleport(glad.getLastLocation());
				c.teleport(glad.getFirstLocation());
				c.sendMessage(
					"§8[§bGladiator§8] §aSeu inimigo saiu: §e" + p.getName());
				c.sendMessage("§8[§bGladiator§8] §aVoce venceu a batalha!");
				gladiators.remove(p);
				gladiators.remove(c);

			}

			for (Location loc : glad.getLocations()) {
				loc.getBlock().setType(Material.AIR);
			}
		}
	}

}
