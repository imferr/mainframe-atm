package bo.edu.ucb.sis213;

import javax.swing.*;

//import bo.edu.ucb.sis213.bussines_logic.ATMBussinesLogic;
import bo.edu.ucb.sis213.view_atm.ATMView;

//import java.awt.*;
//import java.awt.event.*;

public class ATMApp {/* 

    // Frame para las pantallas
    private JFrame frame;
    private App atm;

    // Componentes para la pantalla de login
    private JPanel loginPanel;
    private JPasswordField pinField;
    private JButton loginButton;

    // Componentes para el menú principal
    private JPanel mainMenuPanel;
    private JButton botonConsutaSaldo;
    private JButton botonDeposito;
    private JButton botonRetiro;
    private JButton botonCambiarPin;
    private JButton botonCambiarAlias;
    private JButton botonSalir;

    public ATMApp() {
        frame = new JFrame("Aplicación de simulación de ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        atm = new App(); //Aquí está la instancia de la lógica del cajero

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
                boolean entrada = atm.validarPIN(entradaPin);

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

        botonConsutaSaldo = new JButton("Consultar saldo");
        botonDeposito = new JButton("Realizar un depósito");
        botonRetiro = new JButton("Realizar un retiro");
        botonCambiarPin = new JButton("Cambiar PIN");
        botonCambiarAlias = new JButton("Cambiar Alias");
        botonSalir = new JButton("Salir");

        // Agregar acciones a los botones
        botonConsutaSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //double saldo = atm.consultarSaldo(); //consulto el saldo de la bdd
                JOptionPane.showMessageDialog(frame, "Su saldo actual es: $" + atm.consultarSaldo());
            }
        });

        botonDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double cantidad = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese el monto a depositar: $"));
                if (cantidad > 0){
                    atm.realizarDeposito(cantidad);
                    JOptionPane.showMessageDialog(frame, "Su deposito se realizó con éxito, su saldo actual es de: $"+ atm.consultarSaldo());
                }else{
                    JOptionPane.showMessageDialog(frame, "Su deposito NO se pudo realizar");
                }
            }
        });

        botonRetiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double cantidad = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese el monto a retirar: $"));
                if(cantidad>0 && cantidad<=atm.consultarSaldo()){
                    atm.realizarRetiro(cantidad);
                    JOptionPane.showMessageDialog(frame, "Su retiro se realizó con éxito, su saldo actual es de: $"+ atm.consultarSaldo());
                }else{
                    JOptionPane.showMessageDialog(frame, "Su retiro NO se pudo realizar");
                }
            }
        });

        botonCambiarPin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nuevoPin = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingrese su nuevo PIN:"));
                int confirmarNuevoPin = Integer.parseInt(JOptionPane.showInputDialog(frame, "Confirme su nuevo PIN:"));
                if(nuevoPin == confirmarNuevoPin){
                    atm.cambiarPIN(nuevoPin);
                    JOptionPane.showMessageDialog(frame, "Su PIN se actualizó con éxito");
                }else{
                    JOptionPane.showMessageDialog(frame, "Los campos de PIN ingresados NO coinciden");
                }
            }
        });

        botonCambiarAlias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoAlias = JOptionPane.showInputDialog(frame, "Ingrese su nuevo alias:");
                String confirmarNuevoAlias = JOptionPane.showInputDialog(frame, "Confirme su nuevo alias:");
                atm.cambiarAlias(nuevoAlias);
                JOptionPane.showMessageDialog(frame, "Su alias se actualizó con éxito: "+nuevoAlias);
            }
        });

        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mainMenuPanel.add(botonConsutaSaldo);
        mainMenuPanel.add(botonDeposito);
        mainMenuPanel.add(botonRetiro);
        mainMenuPanel.add(botonCambiarPin);
        mainMenuPanel.add(botonCambiarAlias);
        mainMenuPanel.add(botonSalir);

        frame.add(mainMenuPanel);
        frame.revalidate();
        frame.repaint();
    }*/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ATMView();
            }
        });
    }
}
