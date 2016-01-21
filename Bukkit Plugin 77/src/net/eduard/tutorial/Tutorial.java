package net.eduard.tutorial;

import net.eduard.eduard_api.EduardAPI;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Tutorial extends EduardAPI implements Listener, CommandExecutor
{
	
	public Tutorial(){
		super(Main.class);
	}
	
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this , getPlugin()  );
		setEvent( this );
		PluginCommand cmd = Bukkit.getPluginCommand( "teste" );
		cmd.setPermission( "permissao.use" );
		cmd.setExecutor( this );
		setCommand( "teste" , "permissao.use" , this );
		
		getConfig().addDefault( "teste" , "messagem" );
		getConfig().options().copyDefaults( true );
		saveConfig();
		
		add("teste","messagem");
		saveDefault();
		Player p = null;
		send(p,"eafosdfjasidfoia");
		send("asdfjoasidjfoasijdfias");
		broadcast( "famsdofjasodfijasoidjfasdf" );
		
		for (Player p2:getOnlinePlayers()) {
			send(p2,"mensagem teste");
			p.setGameMode( GameMode.SURVIVAL );
		}
		
		
		
	}
	public void onDisable() {
	
	}

	@Override
	public boolean onCommand( CommandSender arg0 , Command arg1 , String arg2 , String[] arg3 ) {

		// TODO Auto-generated method stub
		return false;
	}


}
