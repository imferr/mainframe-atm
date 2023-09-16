package bo.edu.ucb.sis213.bussines_logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ATMLogicButtons {
    private JFrame frame;
    private ATMBuLog atmBuLog;

    public ATMLogicButtons(JFrame frame, ATMBuLog atmBuLog) {
        this.frame = frame;
        this.atmBuLog = atmBuLog;
    }
    
    //logica de negocio de la consulta de saldo
    public void consultarSaldo() {
        atmBuLog.mostrarMensaje("Su saldo actual es: $" + atmBuLog.obtenerSaldo());
    }

    //logica de negocio de realizar un deposito
    public void realizarDeposito() {
        double cantidad = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese el monto a depositar: $"));
        if (cantidad > 0) {
            atmBuLog.realizarDeposito(cantidad);
            atmBuLog.mostrarMensaje("Su deposito se realizo con exito, su saldo actual es de: $" + atmBuLog.obtenerSaldo());
        } else {
            atmBuLog.mostrarMensaje("Su deposito NO se pudo realizar");
        }
    }

    //logica de negocio de realizar un retiro
     public void realizarRetiro() {
        double cantidad = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese el monto a retirar: $"));
        if(cantidad>0 && cantidad<=atmBuLog.obtenerSaldo()){
            atmBuLog.realizarRetiro(cantidad);
            atmBuLog.mostrarMensaje("Su retiro se realizo con exito, su saldo actual es de: $" + atmBuLog.obtenerSaldo());
        }else{
            atmBuLog.mostrarMensaje("Su retiro NO se pudo realizar");
        }
     }

     //logica de negocio de cambiar el pin
     public void cambiarPIN() {
        int nuevoPin = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingrese su nuevo PIN:"));
        int confirmarNuevoPin = Integer.parseInt(JOptionPane.showInputDialog(frame, "Confirme su nuevo PIN:"));
        if(nuevoPin == confirmarNuevoPin){
            atmBuLog.cambiarPIN(nuevoPin);
            atmBuLog.mostrarMensaje("Su PIN se actualizo con exito");
        }else{
            atmBuLog.mostrarMensaje("Los campos de PIN ingresados NO coinciden");
        }
     }

     //logica de negocio de cambiar el alias
     public void cambiarAlias() {
        String nuevoAlias = JOptionPane.showInputDialog(frame, "Ingrese su nuevo alias:");
        String confirmarNuevoAlias = JOptionPane.showInputDialog(frame, "Confirme su nuevo alias:");
        
        if (nuevoAlias != null && confirmarNuevoAlias != null && nuevoAlias.equals(confirmarNuevoAlias)) {
            atmBuLog.cambiarAlias(nuevoAlias);
            atmBuLog.mostrarMensaje("Su alias se actualizo con exito: " + nuevoAlias);
        } else {
            atmBuLog.mostrarMensaje("Los alias ingresados no coinciden o estan vacios");
        }
     }

     //salir del programa
     public void salir() {
        System.exit(0);
     }
}
