package bo.edu.ucb.sis213;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMApp {

    // Frame para las pantallas
    private JFrame frame;
    private App atmConector;

    // Componentes para la pantalla de login
    private JPanel loginPanel;
    private JTextField userField;
    private JPasswordField pinField;
    private JButton loginButton;

    // Componentes para el menú principal
    private JPanel mainMenuPanel;
    private JButton checkBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton changePinButton;
    private JButton changeAliasButton;
    private JButton exitButton;

    public ATMApp() {
        frame = new JFrame("Aplicación de simulación de ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        atmConector = new App(); //Aquí está la instancia de la lógica del cajero

        initLoginScreen();

        frame.setVisible(true);
    }

    private void initLoginScreen() {
        loginPanel = new JPanel(new GridLayout(3, 2));
        pinField = new JPasswordField();
        loginButton = new JButton("Ingresar");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int entradaPin = Integer.parseInt(new String(pinField.getPassword()));
                boolean entrada = atmConector.validarPin(entradaPin);

                if (entrada) {
                    showMainMenu();
                } else {
                    JOptionPane.showMessageDialog(frame, "PIN incorrecto, intente de nuevo");
                }
            }
        });

        loginPanel.add(new JLabel("Ingrese su PIN de 4 dígitos:"));
        loginPanel.add(pinField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        frame.add(loginPanel);
    }

    private void showMainMenu() {
        frame.remove(loginPanel);

        mainMenuPanel = new JPanel(new GridLayout(6, 1));

        checkBalanceButton = new JButton("Consultar saldo");
        depositButton = new JButton("Realizar un depósito");
        withdrawButton = new JButton("Realizar un retiro");
        changePinButton = new JButton("Cambiar PIN");
        changeAliasButton = new JButton("Cambiar Alias");
        exitButton = new JButton("Salir");

        // Agregar acciones a los botones
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para consultar el saldo
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para realizar un depósito
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para realizar un retiro
            }
        });

        changePinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para cambiar el PIN
            }
        });

        changeAliasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes implementar la lógica para cambiar el alias
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainMenuPanel.add(checkBalanceButton);
        mainMenuPanel.add(depositButton);
        mainMenuPanel.add(withdrawButton);
        mainMenuPanel.add(changePinButton);
        mainMenuPanel.add(changeAliasButton);
        mainMenuPanel.add(exitButton);

        frame.add(mainMenuPanel);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMApp();
            }
        });
    }
}
