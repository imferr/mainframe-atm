package bo.edu.ucb.sis213.view_atm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bo.edu.ucb.sis213.database_access.ATMDatabaseAccess;

public class ATMButtons {
    private JPanel mainMenuPanel;
    private JButton botonConsutaSaldo;
    private JButton botonDeposito;
    private JButton botonRetiro;
    private JButton botonCambiarPin;
    private JButton botonCambiarAlias;
    private JButton botonSalir;

    public ATMButtons(JFrame frame, ATMDatabaseAccess atmDatabaseAccess) {
        mainMenuPanel = new JPanel(new GridLayout(6, 1));

        botonConsutaSaldo = new JButton("Consultar Saldo");
        botonDeposito = new JButton("Realizar Depósito");
        botonRetiro = new JButton("Realizar Retiro");
        botonCambiarPin = new JButton("Cambiar PIN");
        botonCambiarAlias = new JButton("Cambiar Alias");
        botonSalir = new JButton("Salir");

        //accion del boton de consulta de saldo
        botonConsutaSaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //double saldo = atmDatabaseAccess.obtenerSaldo();
                atmDatabaseAccess.mostrarMensaje("Su saldo actual es: $" + atmDatabaseAccess.obtenerSaldo());
            }
        });

        //accion del boton de deposito
        botonDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double cantidad = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese el monto a depositar: $"));
                if (cantidad > 0){
                    atmDatabaseAccess.realizarDeposito(cantidad);
                    atmDatabaseAccess.mostrarMensaje("Su depósito se realizó con éxito, su saldo actual es de: $" + atmDatabaseAccess.obtenerSaldo());
                } else {
                    atmDatabaseAccess.mostrarMensaje("Su depósito NO se pudo realizar");
                }
            }
        });

        //accion del boton de retiro
        botonRetiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double cantidad = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese el monto a retirar: $"));
                if(cantidad>0 && cantidad<=atmDatabaseAccess.obtenerSaldo()){
                    atmDatabaseAccess.realizarRetiro(cantidad);
                    atmDatabaseAccess.mostrarMensaje("Su retiro se realizó con éxito, su saldo actual es de: $" + atmDatabaseAccess.obtenerSaldo());
                }else{
                    atmDatabaseAccess.mostrarMensaje("Su retiro NO se pudo realizar");
                }
            }
        });

        //accion del boton cambiar pin
        botonCambiarPin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nuevoPin = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingrese su nuevo PIN:"));
                int confirmarNuevoPin = Integer.parseInt(JOptionPane.showInputDialog(frame, "Confirme su nuevo PIN:"));
                if(nuevoPin == confirmarNuevoPin){
                    atmDatabaseAccess.cambiarPIN(nuevoPin);
                    atmDatabaseAccess.mostrarMensaje("Su PIN se actualizó con éxito");
                }else{
                    atmDatabaseAccess.mostrarMensaje("Los campos de PIN ingresados NO coinciden");
                }
            }
        });

        //accion boton cambiar alias
        botonCambiarAlias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nuevoAlias = JOptionPane.showInputDialog(frame, "Ingrese su nuevo alias:");
                String confirmarNuevoAlias = JOptionPane.showInputDialog(frame, "Confirme su nuevo alias:");
                
                if (nuevoAlias != null && confirmarNuevoAlias != null && nuevoAlias.equals(confirmarNuevoAlias)) {
                    atmDatabaseAccess.cambiarAlias(nuevoAlias);
                    atmDatabaseAccess.mostrarMensaje("Su alias se actualizó con éxito: " + nuevoAlias);
                } else {
                    atmDatabaseAccess.mostrarMensaje("Los alias ingresados no coinciden o están vacíos");
                }
            }
        });

        //accion boton salir
        botonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //agrego los botones al panel
        mainMenuPanel.add(botonConsutaSaldo);
        mainMenuPanel.add(botonDeposito);
        mainMenuPanel.add(botonRetiro);
        mainMenuPanel.add(botonCambiarPin);
        mainMenuPanel.add(botonCambiarAlias);
        mainMenuPanel.add(botonSalir);
    }

    public JPanel obtenerMainMenuPanel() {
        return mainMenuPanel;
    }
}
