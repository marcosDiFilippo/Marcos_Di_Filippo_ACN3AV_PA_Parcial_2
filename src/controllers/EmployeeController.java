package controllers;

import javax.swing.JFrame;

import views.TransactionsView;

public class EmployeeController {

    private TransactionsView transactionsView;

    public EmployeeController(JFrame parentView) {
        transactionsView = new TransactionsView(parentView);
    }

    public void viewAllTransactions() {
        transactionsView.setVisible(true);
    }
}
