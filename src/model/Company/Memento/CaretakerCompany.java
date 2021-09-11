package model.Company.Memento;

import model.Company.Company;

import java.util.Stack;

public class CaretakerCompany {
    private Company company;
    private Stack<MementoCompany> history;
    private Stack<MementoCompany> redoHistory;

    public CaretakerCompany(Company company) {
        this.company = company;
        this.history = new Stack<MementoCompany>();
        this.redoHistory = new Stack<MementoCompany>();
    }

    public void doSomething() {
        this.history.push(this.company.saveState());
    }

    public Company undo () {
        if (this.history.isEmpty()) {
            System.out.println("The History Stack is empty");
        } else {
            MementoCompany memento = history.pop();
            this.redoHistory.push(this.company.saveState());
            this.company = memento.restoreState();
        }

        return this.company;
    }

    public Company redo() {
        if (this.redoHistory.isEmpty()) {
            System.out.println("The Redo History Stack is empty");
        } else {
            MementoCompany memento = this.redoHistory.pop();
            this.history.push(this.company.saveState());
            this.company = memento.restoreState();
        }

        return this.company;
    }

}
