package bo.edu.ucb.sis213.view_atm;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bo.edu.ucb.sis213.bussines_logic.ATMDatabaseAccess;
import bo.edu.ucb.sis213.bussines_logic.ATMLogicButtons;

public class ATMButtons {
    private JPanel mainMenuPanel;
    private JButton botonConsutaSaldo;
    private JButton botonDeposito;
    private JButton botonRetiro;
    private JButton botonCambiarPin;
    private JButton botonCambiarAlias;
    private JButton botonSalir;
    private ATMLogicButtons atmLogicButtons;

    public ATMButtons(JFrame frame, ATMDatabaseAccess atmDatabaseAccess) {
        mainMenuPanel = new JPanel(new GridLayout(6, 1));
        mainMenuPanel.setBackground(new Color(240, 240, 240));
        atmLogicButtons = new ATMLogicButtons(frame, atmDatabaseAccess); //llamo a la clase atmLogicButtons como controlador

        //opciones del menú
        botonConsutaSaldo = new JButton("Consultar Saldo");
        botonDeposito = new JButton("Realizar Deposito");
        botonRetiro = new JButton("Realizar Retiro");
        botonCambiarPin = new JButton("Cambiar PIN");
        botonCambiarAlias = new JButton("Cambiar Alias");
        botonSalir = new JButton("Salir");

        //accion del boton de consulta de saldo
        botonConsutaSaldo.addActionListener(e -> atmLogicButtons.consultarSaldo()); //esto hace que las funciones que hay en la logica de los botones sean llamadas con el action

        //accion del boton de deposito
        botonDeposito.addActionListener(e -> atmLogicButtons.realizarDeposito());

        //accion del boton de retiro
        botonRetiro.addActionListener(e -> atmLogicButtons.realizarRetiro());

        //accion del boton cambiar pin
        botonCambiarPin.addActionListener(e -> atmLogicButtons.cambiarPIN());

        //accion boton cambiar alias
        botonCambiarAlias.addActionListener(e -> atmLogicButtons.cambiarAlias());

        //accion boton salir
        botonSalir.addActionListener(e -> atmLogicButtons.salir());

        //agrego los botones al panel
        mainMenuPanel.add(botonConsutaSaldo);
        mainMenuPanel.add(botonDeposito);
        mainMenuPanel.add(botonRetiro);
        mainMenuPanel.add(botonCambiarPin);
        mainMenuPanel.add(botonCambiarAlias);
        mainMenuPanel.add(botonSalir);
    }

    public JPanel obtenerMainMenuPanel() {
        return mainMenuPanel;   //devuelvo el menu
    }
}
