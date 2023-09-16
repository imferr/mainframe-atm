package bo.edu.ucb.sis213.bussines_logic;

import javax.swing.JOptionPane;

import bo.edu.ucb.sis213.database_access.ATMDatabaseAccess;

public class ATMBuLog {
    private ATMDatabaseAccess atmDatabaseAccess;

    public ATMBuLog(ATMDatabaseAccess atmDatabaseAccess) {
        this.atmDatabaseAccess = atmDatabaseAccess;
    }

    //validación del alias o usuario
    public boolean validarUser(String usuario, int pin) {
        return ATMDatabaseAccess.validarUser(usuario, pin);
    }
    //Validación de pin con el atmBussinessLogic
    public boolean ingresarPIN(int pin) {
        return ATMDatabaseAccess.validarPIN(pin);
    }

    //obtener saldo con el atmBussinessLogic
    public double obtenerSaldo() {
        return atmDatabaseAccess.consultarSaldo();
    }

    //realizar un depósito con el atmBussinessLogic
    public void realizarDeposito(double cantidad) {
        atmDatabaseAccess.realizarDeposito(cantidad);
    }

    //realizar un retiro conetado con el bussines logic
    public void realizarRetiro(double cantidad){
        ATMDatabaseAccess.realizarRetiro(cantidad);
    }

    //realizar un cambio de pin conetado con el bussines logic
    public void cambiarPIN(int nuevoPin) {
        ATMDatabaseAccess.cambiarPIN(nuevoPin);
    }

    //realizar un cambio de alias conetado con el bussines logic
    public void cambiarAlias(String nuevoAlias){
        ATMDatabaseAccess.cambiarAlias(nuevoAlias);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
