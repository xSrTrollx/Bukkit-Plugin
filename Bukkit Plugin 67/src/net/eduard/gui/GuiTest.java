
package net.eduard.gui;

import net.eduard.eduard_api.EduardAPI;
import net.eduard.eduard_api.game.item.Item;
import net.eduard.eduard_api.player.gui.Gui;
import net.eduard.eduard_api.player.gui.Slot;
import net.eduard.eduard_api.player.gui.util.GuiType;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class GuiTest extends EduardAPI implements Listener
{

	public GuiTest(){

		super( Main.class );
	}

	public void onEnable() {
		Gui invs = new Gui( GuiType.LINE_2 , "§6Trocar velocidade" );
		Slot slot = new Slot(new Item(Material.LEATHER_BOOTS,"§6Nivel 1")) {
			public void run(Player p) {
				p.setWalkSpeed( 0.2F );
				super.run( p );
			};
		};
		slot.setCloseInventory( true );
		slot.setMessage( "§6Sua velocidade foi alterada para o nivel 1" );
		invs.addSlot( 2 , slot );
		slot = new Slot(new Item(Material.GOLD_BOOTS,"§6Nivel 2")) {
			public void run(Player p) {
				p.setWalkSpeed( 0.4F );
				super.run( p );
			};
		};
		slot.setCloseInventory( true );
		slot.setMessage( "§6Sua velocidade foi alterada para o nivel 2" );
		invs.addSlot( 3 , slot );
		slot = new Slot(new Item(Material.IRON_BOOTS,"§6Nivel 3")) {
			public void run(Player p) {
				p.setWalkSpeed( 0.6F );
				super.run( p );
			};
		};
		slot.setCloseInventory( true );
		slot.setMessage( "§6Sua velocidade foi alterada para o nivel 3" );
		invs.addSlot( 4 , slot );
		slot = new Slot(new Item(Material.CHAINMAIL_BOOTS,"§6Nivel 4")) {
			public void run(Player p) {
				p.setWalkSpeed( 0.8F );
				super.run( p );
			};
		};
		slot.setCloseInventory( true );
		slot.setMessage( "§6Sua velocidade foi alterada para o nivel 4" );
		invs.addSlot( 5 , slot );
		slot = new Slot(new Item(Material.DIAMOND_BOOTS,"§6Nivel 5")) {
			public void run(Player p) {
				p.setWalkSpeed( 1F );
				super.run( p );
			};
		};
		slot.setCloseInventory( true );
		slot.setMessage( "§6Sua velocidade foi alterada para o nivel 5" );
		invs.addSlot( 6 , slot );
		
		addGui( invs );
		setEvent( this );
	}

	@EventHandler
	public void onPlayerJoinEvent( PlayerJoinEvent e ) {

		Player p = e.getPlayer();
		p.getInventory().addItem(
			new Item( Material.DIAMOND , "§4Abrir Gui Custom" ) );
	}

}
