package billpayment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class BankAccountDetails extends BillingDetails{
    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "swift_code")
    private String sWIFTCode;

    protected BankAccountDetails() {
    }

    public BankAccountDetails(User owner, String bankName, String sWIFTCode) {
        super(owner);
        setBankName(bankName);
        setsWIFTCode(sWIFTCode);
    }

    public String getBankName() {
        return bankName;
    }

    private void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getsWIFTCode() {
        return sWIFTCode;
    }

    private void setsWIFTCode(String sWIFTCode) {
        this.sWIFTCode = sWIFTCode;
    }
}
