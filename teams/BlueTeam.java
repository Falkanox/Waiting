package fr.falkanox.bingo.teams;

import dev.jcsoftware.jscoreboards.JGlobalScoreboard;
import dev.jcsoftware.jscoreboards.JPerPlayerMethodBasedScoreboard;
import dev.jcsoftware.jscoreboards.JScoreboardTeam;
import fr.falkanox.bingo.Bingo;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class BlueTeam {

    private JScoreboardTeam team;
    private JPerPlayerMethodBasedScoreboard scoreboard;
    private Bingo main;


    public BlueTeam(JScoreboardTeam team, JPerPlayerMethodBasedScoreboard scoreboard, Bingo main){
        this.team = team;
        this.scoreboard = scoreboard;
        this.main = main;
    }

    public void addToBlueTeam(UUID uuid, Player p) {

        if (uuid != null) {
            //Bukkit.getOnlinePlayers().forEach(this::addToScoreboard);

            if (!team.isOnTeam(uuid)) {

                if (team.getEntities().size() < 4) {

                    team.addPlayer(p);
                    addToScoreboard(p);
                    countDown();
                }

            } else addToScoreboard(p);
        }
    }

    public void addToScoreboard(Player p) {

        scoreboard.addPlayer(p);
        scoreboard.setTitle(p, "§6§lBINGO");
        scoreboard.setLines(p,
                "§c",
                "§fJoueurs:§7 " + Bukkit.getOnlinePlayers().size(),
                "§e",
                "§fVotre équipe: §bBleue",
                "§b",
                "§fStatut: §7En attente...",
                "§f",
                "§fTimer: §700:00",
                "§a");

        scoreboard.updateScoreboard();

    }

    public void countDown() {
        new BukkitRunnable() {
            @Override
            public void run() {

                    for (UUID player : team.getEntities()) {
                        countDownScoreBoard(Bukkit.getPlayer(player));
                    }
                }
        }.runTaskTimerAsynchronously(main,0,20);
    }

    private void countDownScoreBoard(Player p) {

        if(Bukkit.getOnlinePlayers().contains(p)){

            scoreboard.setLines(p,
                    "§c",
                    "§fJoueurs:§7 " + Bukkit.getOnlinePlayers().size(),
                    "§e",
                    "§fVotre équipe: §bBleue",
                    "§b",
                    "§fStatut: §7En attente...",
                    "§f",
                    "§fTimer: §700:00",
                    "§a");

        }

    }
}
