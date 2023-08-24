package bo.edu.ucb.sis213.bussines_logic;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ATMLogicButtons {
    private JFrame frame;
    private ATMDatabaseAccess atmDatabaseAccess;

    public ATMLogicButtons(JFrame frame, ATMDatabaseAccess atmDatabaseAccess) {
        this.frame = frame;
        this.atmDatabaseAccess = atmDatabaseAccess;
    }
    
    //logica de negocio de la consulta de saldo
    public void consultarSaldo() {
        atmDatabaseAccess.mostrarMensaje("Su saldo actual es: $" + atmDatabaseAccess.obtenerSaldo());
    }

    //logica de negocio de realizar un deposito
    public void realizarDeposito() {
        double cantidad = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese el monto a depositar: $"));
        if (cantidad > 0) {
            atmDatabaseAccess.realizarDeposito(cantidad);
            atmDatabaseAccess.mostrarMensaje("Su deposito se realizo con exito, su saldo actual es de: $" + atmDatabaseAccess.obtenerSaldo());
        } else {
            atmDatabaseAccess.mostrarMensaje("Su deposito NO se pudo realizar");
        }
    }

    //logica de negocio de realizar un retiro
     public void realizarRetiro() {
        double cantidad = Double.parseDouble(JOptionPane.showInputDialog(frame, "Ingrese el monto a retirar: $"));
        if(cantidad>0 && cantidad<=atmDatabaseAccess.obtenerSaldo()){
            atmDatabaseAccess.realizarRetiro(cantidad);
            atmDatabaseAccess.mostrarMensaje("Su retiro se realizo con exito, su saldo actual es de: $" + atmDatabaseAccess.obtenerSaldo());
        }else{
            atmDatabaseAccess.mostrarMensaje("Su retiro NO se pudo realizar");
        }
     }

     //logica de negocio de cambiar el pin
     public void cambiarPIN() {
        int nuevoPin = Integer.parseInt(JOptionPane.showInputDialog(frame, "Ingrese su nuevo PIN:"));
        int confirmarNuevoPin = Integer.parseInt(JOptionPane.showInputDialog(frame, "Confirme su nuevo PIN:"));
        if(nuevoPin == confirmarNuevoPin){
            atmDatabaseAccess.cambiarPIN(nuevoPin);
            atmDatabaseAccess.mostrarMensaje("Su PIN se actualizo con exito");
        }else{
            atmDatabaseAccess.mostrarMensaje("Los campos de PIN ingresados NO coinciden");
        }
     }

     //logica de negocio de cambiar el alias
     public void cambiarAlias() {
        String nuevoAlias = JOptionPane.showInputDialog(frame, "Ingrese su nuevo alias:");
        String confirmarNuevoAlias = JOptionPane.showInputDialog(frame, "Confirme su nuevo alias:");
        
        if (nuevoAlias != null && confirmarNuevoAlias != null && nuevoAlias.equals(confirmarNuevoAlias)) {
            atmDatabaseAccess.cambiarAlias(nuevoAlias);
            atmDatabaseAccess.mostrarMensaje("Su alias se actualizo con exito: " + nuevoAlias);
        } else {
            atmDatabaseAccess.mostrarMensaje("Los alias ingresados no coinciden o estan vacios");
        }
     }

     //salir del programa
     public void salir() {
        System.exit(0);
     }
}
