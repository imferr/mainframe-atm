package bo.edu.ucb.sis213.view_atm;

import javax.swing.*;

import bo.edu.ucb.sis213.bussines_logic.ATMBuLog;
import bo.edu.ucb.sis213.database_access.ATMDatabaseAccess;

public class ATMView {
    private JFrame frame;
    private ATMBuLog atmBuLog;

    public ATMView() {
        frame = new JFrame("BANCO 'FORTALEZA'");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        ATMDatabaseAccess aLogic = new ATMDatabaseAccess();
        atmBuLog = new ATMBuLog(aLogic);

        ATMLogin alogin = new ATMLogin(frame, atmBuLog);

        frame.setVisible(true);
    }
}