
package net.eduard.kitpvp.kits;

import net.eduard.kitpvp.kit.Kit;
import net.eduard.kitpvp.kit.KitType;

import org.bukkit.Material;
import org.bukkit.event.Listener;

public class Hulk extends Kit implements Listener {

	public Hulk() {
		super(KitType.HULK);
		setName("§6Hulk");
		setMaterial(Material.DIAMOND);
		addLine("§bLevantar seus inimigos e joga-los para longe");

	}
	//
	// if ( ( e.getRightClicked() instanceof Player ) ) {
	// /* 29 */ Player r = ( Player ) e.getRightClicked();
	// /* 30 */ if ( Main.hulk.contains( p.getName() ) )
	// /* 31 */ if ( ( !cooldown.containsKey( p.getName() ) )
	// || ( cooldown.get( p.getName() ).longValue() <=
	// System.currentTimeMillis() ) ) {
	// /* 32 */ if ( Main.areaPvP( p ) ) {
	// /* 33 */ if ( Main.areaPvP( r ) ) {
	// /* 34 */ if ( p.getItemInHand().getType() == Material.SADDLE ) {
	// /* 35 */ e.setCancelled( true );
	// /* 36 */ p.updateInventory();
	// /* 37 */ p.setPassenger( r );
	// /* 38 */ cooldown.put(
	// p.getName() ,
	// Long.valueOf( System.currentTimeMillis()
	// + TimeUnit.SECONDS.toMillis( 15L ) ) );
	// /* 39 */ p.sendMessage( ChatColor.GOLD + "Voce pegou o player: "
	// + ChatColor.WHITE + r.getName() );
	// /* 40 */ r.sendMessage( ChatColor.GOLD + "Voce foi pego pelo Hulk: "
	// + ChatColor.WHITE + p.getName() );
	// /* */ }
	// /* */ }
	// /* 43 */ else
	// p.sendMessage( ChatColor.RED
	// + "Voce pode usar esta habilidade apenas em players que estejam em areas
	// com PVP." );
	// /* */ }
	// /* */ else
	// /* 46 */ p.sendMessage( ChatColor.RED
	// + "Voce pode usar esta habilidade apenas em areas com PVP." );
	// /* */ }
	// /* */ else
	// /* 49 */ p.sendMessage( ChatColor.RED
	// + "Faltam "
	// + TimeUnit.MILLISECONDS.toSeconds( cooldown.get( p.getName()
	// ).longValue()
	// - System.currentTimeMillis() ) + " segundos para poder usar novamente."
	// );
	// /* */ }
	// /* */}

}
