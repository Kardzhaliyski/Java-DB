package footballBetting.entities.football;

import javax.persistence.*;

@Entity(name = "player_statistics")

@IdClass(PlayerStatisticsId.class)
public class PlayerStatistics {

    @Id
    @ManyToOne
    private Game game;
    @Id
    @ManyToOne
    private Player player;

    @Column(name = "scored_goals")
    private int scoredGoals;
    @Column(name = "player_assists")
    private int playerAssists;
    @Column(name = "played_minutes")
    private int playedMinutes;

    protected PlayerStatistics() {
    }

    public PlayerStatistics(Game game, Player player, int scoredGoals, int playerAssists, int playedMinutes) {
        this.game = game;
        this.player = player;
        this.scoredGoals = scoredGoals;
        this.playerAssists = playerAssists;
        this.playedMinutes = playedMinutes;
    }

    public Game getGame() {
        return game;
    }

    private void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    public int getScoredGoals() {
        return scoredGoals;
    }

    private void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public int getPlayerAssists() {
        return playerAssists;
    }

    private void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    public int getPlayedMinutes() {
        return playedMinutes;
    }

    private void setPlayedMinutes(int playedMinutes) {
        this.playedMinutes = playedMinutes;
    }
}
