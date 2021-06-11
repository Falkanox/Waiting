package fr.falkanox.bingo;

import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import fr.falkanox.bingo.inventorys.TeamInventory;
import fr.falkanox.bingo.registers.RegisterCommands;
import fr.falkanox.bingo.registers.RegisterEvents;
import fr.falkanox.bingo.scoreboard.WaitingScoreboard;
import fr.falkanox.bingo.states.GState;
import fr.falkanox.bingo.teams.BlueTeam;
import fr.falkanox.bingo.teams.RedTeam;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Bingo extends JavaPlugin {

    private RegisterEvents rv = new RegisterEvents(this);
    private RegisterCommands rm = new RegisterCommands(this);

    private GState state;

    public JPerPlayerMethodBasedScoreboard scoreboard;
    public JScoreboardTeam team;
    public JScoreboardTeam team1;
    public BlueTeam blueTeam;
    public RedTeam redTeam;

    public String prefix = "§7[§eBingo§7] §e";
    public String error = "§7[§eBingo§7] §c";

    public TeamInventory teamInventory = new TeamInventory();

    public WaitingScoreboard waitingScoreboard = new WaitingScoreboard(this);

    public void onEnable(){

        getServer().getWorld("world").setTime(1000);
        getServer().getWorld("world").setStorm(false);

        rv.registerEvents();
        rm.registerCommands();

        setState(GState.WAITING);

        scoreboard = new JPerPlayerMethodBasedScoreboard();

        team = scoreboard.createTeam("Bleue", "§bBleue ", ChatColor.AQUA);
        blueTeam = new BlueTeam(team, scoreboard, this);
        

        team1 = scoreboard.createTeam("Rouge", "§cRouge ", ChatColor.RED);
        redTeam = new RedTeam(team1, scoreboard, this);

    }

    public void onDisable(){

    }

    public void setState(GState state) {
        this.state = state;
    }

    public boolean isState(GState state){
        return this.state == state;
    }

}
