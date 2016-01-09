package net.eduard.template;

import net.eduard.eduard_api.EduardAPI;
import net.eduard.eduard_api.config.Config;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;


public class Warp extends EduardAPI implements Listener, CommandExecutor
{
	
	
	public Warp(){
		super(Main.class);
	}
	@SuppressWarnings("unused")
	public void onEnable() {
		
		setCommand( "nomedocomando" , "permissao.use" , this );
		setEvent( this );
		
		add("messagem","&6Mensagem teste!");
		saveDefault();
		Config teste = createConfig( "teste.yml" );
		String mensagem = message( "messagem" );
		send(getConsole(),mensagem);
		
	}
	@Override
	public boolean onCommand( CommandSender arg0 , Command arg1 , String arg2 , String[] arg3 ) {

		// TODO Auto-generated method stub
		return false;
	}

}
