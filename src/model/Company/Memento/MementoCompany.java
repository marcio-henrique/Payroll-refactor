package model.Company.Memento;

import model.Company.Company;
import util.ConvertUtil;

public class MementoCompany {
    private String state;

    public MementoCompany(Company company) {
        this.state = ConvertUtil.company2string(company);
    }

    public Company restoreState() {
        return ConvertUtil.string2company(this.state);
    }
}
