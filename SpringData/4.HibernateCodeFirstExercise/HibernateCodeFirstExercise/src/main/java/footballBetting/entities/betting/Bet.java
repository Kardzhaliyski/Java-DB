package footballBetting.entities.betting;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "bets")
public class Bet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "bet_money")
    private BigDecimal betMoney;
    @Column
    private LocalDate dateTime;
    @ManyToOne
    private User user;

    protected Bet() {
    }

    public Bet(BigDecimal betMoney, LocalDate dateTime, User user) {
        setBetMoney(betMoney);
        setDateTime(dateTime);
        setUser(user);
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBetMoney() {
        return betMoney;
    }

    private void setBetMoney(BigDecimal betMoney) {
        this.betMoney = betMoney;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    private void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    private void setUser(User user) {
        this.user = user;
    }
}
