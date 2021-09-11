package model.Payment;

import java.io.Serializable;

public class PaymentMethod implements Serializable {
    private int agency;
    private int account;
    private int variation;
    private int type; //1 - deposit, 2 - check in address, 3 - check
    private int checkNumber;

    public PaymentMethod(int agency, int account, int variation, int type) {
        this.agency = agency;
        this.account = account;
        this.variation = variation;
        this.type = type;
    }

    public PaymentMethod(int agency, int account, int variation, int type, int checkNumber) {
        this.agency = agency;
        this.account = account;
        this.variation = variation;
        this.type = type;
        this.checkNumber = checkNumber;
    }

    public String paymentTypeString () {
        if (type == 1) {
            return  "Deposit";
        } else if (type == 2) {
            return  "check in address, check number= " + checkNumber;
        } else if (type == 3) {
            return  "check in hands, check number= " + checkNumber;
        }
        return "";
    }
    @Override
    public String toString() {

        return "PaymentMethod{" +
                "type=" + this.paymentTypeString() +
                ", agency=" + agency +
                ", account=" + account +
                ", variation=" + variation +
                '}';
    }
}
