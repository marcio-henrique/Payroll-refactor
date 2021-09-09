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

    @Override
    public String toString() {
        String typeString = "";
        if (type == 1) {
            typeString = "Deposit";
        } else if (type == 2) {
            typeString = "check in address, check number= " + checkNumber;
        } else if (type == 3) {
            typeString = "check in hands, check number= " + checkNumber;
        }

        return "PaymentMethod{" +
                "type=" + typeString +
                ", agency=" + agency +
                ", account=" + account +
                ", variation=" + variation +
                '}';
    }
}
