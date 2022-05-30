package billpayment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class CreditCardDetails extends BillingDetails{
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "exparation_date")
    private LocalDate expirationDate;

    protected CreditCardDetails() {
    }

    public CreditCardDetails(User owner, String cardType, int expirationYear, int expirationMonth) {
        super(owner);
        setCardType(cardType);
        setExpirationDate(expirationYear, expirationMonth);
    }

    public int getExpirationMonth() {
        return expirationDate.getMonthValue();
    }

    public int getExpirationYear() {
        return expirationDate.getYear();
    }

    public String getCardType() {
        return cardType;
    }

    private void setCardType(String cardType) {
        this.cardType = cardType;
    }

    private LocalDate getExpirationDate() {
        return expirationDate;
    }

    private void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    private void setExpirationDate(int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        int lastDayOfMonth = date.lengthOfMonth();
        date = date.withDayOfMonth(lastDayOfMonth);
        this.expirationDate = date;
    }
}
