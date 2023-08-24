package bo.edu.ucb.sis213.database_access;

import javax.swing.JOptionPane;

import bo.edu.ucb.sis213.bussines_logic.ATMBussinesLogic;

public class ATMDatabaseAccess {
    private ATMBussinesLogic atmbBussinesLogic;

    public ATMDatabaseAccess(ATMBussinesLogic atmBussinesLogic) {
        this.atmbBussinesLogic = atmBussinesLogic;
    }

    //Validación de pin con el atmBussinessLogic
    public boolean ingresarPIN(int pin) {
        return ATMBussinesLogic.validarPIN(pin);
    }

    //obtener saldo con el atmBussinessLogic
    public double obtenerSaldo() {
        return atmbBussinesLogic.consultarSaldo();
    }

    //realizar un depósito con el atmBussinessLogic
    public void realizarDeposito(double cantidad) {
        atmbBussinesLogic.realizarDeposito(cantidad);
    }

    //realizar un retiro conetado con el bussines logic
    public void realizarRetiro(double cantidad){
        ATMBussinesLogic.realizarRetiro(cantidad);
    }

    //realizar un cambio de pin conetado con el bussines logic
    public void cambiarPIN(int nuevoPin) {
        ATMBussinesLogic.cambiarPIN(nuevoPin);
    }

    //realizar un cambio de alias conetado con el bussines logic
    public void cambiarAlias(String nuevoAlias){
        ATMBussinesLogic.cambiarAlias(nuevoAlias);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
