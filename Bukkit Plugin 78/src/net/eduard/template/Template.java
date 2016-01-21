package net.eduard.template;

import net.eduard.eduard_api.EduardAPI;
import net.eduard.eduard_api.manager.tag.Tag;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Template extends EduardAPI implements Listener
{
	
	public Template(){
		super(Main.class);
	}
	
	public void onEnable() {
		setEvent( this );
	}
	public void onDisable() {
	
	}
	@EventHandler
	public void a(PlayerJoinEvent e) {
		 Player p = e.getPlayer();
//		 Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
//		 Team team = scoreboard.registerNewTeam( p.getName() );
//		 team.setPrefix( "§a" );
//		 team.addPlayer( p );
//		 p.setScoreboard( scoreboard );
		 Tag.tags.put( p , new Tag("§a","") );
		
	}
	@EventHandler
	public void a(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		e.setFormat("§c"+ p.getDisplayName() +": §r"+e.getMessage() );
	}


}
