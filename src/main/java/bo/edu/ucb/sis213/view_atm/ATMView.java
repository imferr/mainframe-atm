package bo.edu.ucb.sis213.view_atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import bo.edu.ucb.sis213.ATMApp;
import bo.edu.ucb.sis213.bussines_logic.ATMBussinesLogic;
import bo.edu.ucb.sis213.database_access.ATMDatabaseAccess;

public class ATMView {
    private JFrame frame;
    private ATMDatabaseAccess atmDatabaseAccess;

    private JPanel loginPanel;
    private JPasswordField pinField;
    private JButton loginButton;

    public ATMView() {
        frame = new JFrame("Banco 'Fortaleza'");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        ATMBussinesLogic aLogic = new ATMBussinesLogic();
        atmDatabaseAccess = new ATMDatabaseAccess(aLogic);

        initLoginScreen();

        frame.setVisible(true);
    }

    private void initLoginScreen(){
        loginPanel = new JPanel(new GridLayout(3, 2));
        pinField = new JPasswordField();
        loginButton = new JButton("Ingresar");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int entradaPin = Integer.parseInt(new String(pinField.getPassword()));
                boolean entrada = atmDatabaseAccess.ingresarPIN(entradaPin);

                if (entrada) {
                    showMainMenu();
                } else {
                    atmDatabaseAccess.mostrarMensaje("PIN incorrecto, intente de nuevo");
                }
            }
        });

        loginPanel.add(new Label("Ingrese su PIN de 4 dígitos:"));
        loginPanel.add(pinField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        frame.add(loginPanel);
    }

    private void showMainMenu() {
        frame.remove(loginPanel); //retiro la pagina del login
        ATMButtons atmButtons = new ATMButtons(frame, atmDatabaseAccess);
        frame.add(atmButtons.obtenerMainMenuPanel());
        frame.revalidate();
        frame.repaint();
    }
}