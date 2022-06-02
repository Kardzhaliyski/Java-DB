package footballBetting.entities.football;

import javax.persistence.IdClass;
import java.io.Serializable;

public class PlayerStatisticsId implements Serializable {
    Player player;
    Game game;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
