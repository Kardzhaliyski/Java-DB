package footballBetting.entities.football;

import javax.persistence.*;

@Entity(name = "player_statistics")
public class PlayerStatistics {

    private Game game;
    private Player player;
}
