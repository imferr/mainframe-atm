package bo.edu.ucb.sis213.view_atm;

import javax.swing.*;

import bo.edu.ucb.sis213.bussines_logic.ATMDatabaseAccess;
import bo.edu.ucb.sis213.database_access.ATMBussinesLogic;

public class ATMView {
    private JFrame frame;
    private ATMDatabaseAccess atmDatabaseAccess;

    public ATMView() {
        frame = new JFrame("BANCO 'FORTALEZA'");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        ATMBussinesLogic aLogic = new ATMBussinesLogic();
        atmDatabaseAccess = new ATMDatabaseAccess(aLogic);

        ATMLogin alogin = new ATMLogin(frame, atmDatabaseAccess);

        frame.setVisible(true);
    }
}