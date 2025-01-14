package bo.edu.ucb.sis213.view_atm;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bo.edu.ucb.sis213.bussines_logic.ATMBuLog;

public class ATMLogin {
    
    private JPanel loginPanel;
    private JTextField userField;
    private JPasswordField pinField;
    private JButton loginButton;

    public ATMLogin(JFrame frame, ATMBuLog atmBuLog) {
        iniciarLogin(frame, atmBuLog);
    }

    private void iniciarLogin(JFrame frame, ATMBuLog atmBuLog) {
        loginPanel = new JPanel(new GridLayout(4, 2));
        userField = new JTextField();
        pinField = new JPasswordField();
        loginButton = new JButton("Ingresar");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText();
                int entradaPin = Integer.parseInt(new String(pinField.getPassword()));
                boolean entrada = atmBuLog.validarUser(usuario, entradaPin);

                if (entrada) {
                    showMainMenu(frame, atmBuLog);
                } else {
                    atmBuLog.mostrarMensaje("Usuario o PIN incorrecto, intente de nuevo");
                }
            }
        });

        loginPanel.add(new JLabel("Usuario (Alias):"));
        loginPanel.add(userField);
        loginPanel.add(new Label("Ingrese su PIN de 4 digitos:"));
        loginPanel.add(pinField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        frame.getContentPane().removeAll();
        frame.add(loginPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void showMainMenu(JFrame frame, ATMBuLog atmBuLog) {
        frame.remove(loginPanel); //retiro la pagina del login
        ATMButtons atmButtons = new ATMButtons(frame, atmBuLog);
        frame.add(atmButtons.obtenerMainMenuPanel());
        frame.revalidate();
        frame.repaint();
    }
}
