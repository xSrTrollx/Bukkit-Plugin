
package net.eduard.score;

import net.eduard.eduard_api.EduardAPI;
import net.eduard.eduard_api.manager.scoreboard.Scoreboard;
import net.eduard.eduard_api.time.timer.Timer;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map.Entry;

public class Score extends EduardAPI implements Listener
{

	public Score(){

		super( Main.class );
	}
	public HashMap< Player , Scoreboard > scores = new HashMap<>();

	public void setup() {

		setEvent( this );
		new Timer( 1 ) {

			public void effect() {

				for ( Entry< Player , Scoreboard > scoreboard : scores.entrySet() ) {
					Player p = scoreboard.getKey();
					Scoreboard score = scoreboard.getValue();
					score.set(6 , "§3Kills: §e" + p.getStatistic( Statistic.PLAYER_KILLS ) ) ;
					score.set(4 , "§3Deaths: §e" + p.getStatistic( Statistic.DEATHS ) ) ;
				}

			}
		};

	}

	@EventHandler
	public void onPlayerJoinEvent( PlayerJoinEvent e ) {

		Player p = e.getPlayer();
		Scoreboard score = new Scoreboard( "§6§lSky§f§lLegend" );
		score.setEmpty( 10 );
		score.set(8 , "§aNick: §6"+p.getName() ) ;
		score.set(6 , "§3Kills: §e" + p.getStatistic( Statistic.PLAYER_KILLS ) ) ;
		score.set(4 , "§3Deaths: §e" + p.getStatistic( Statistic.DEATHS ) ) ;
		p.setScoreboard( score.getScoreboard() );
		scores.put( p , score );
	}
}
