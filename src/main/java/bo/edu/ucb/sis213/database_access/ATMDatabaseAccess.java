package bo.edu.ucb.sis213.database_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ATMDatabaseAccess {

    private static int usuarioId;
    private static double saldo;
    private static int pinActual;

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3307;
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private static final String DATABASE = "atm";

    public static Connection getConnection() throws SQLException {
        String jdbcUrl = String.format("jdbc:mysql://%s:%d/%s", HOST, PORT, DATABASE);
        try {
            // Asegúrate de tener el driver de MySQL agregado en tu proyecto
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found.", e);
        }

        return DriverManager.getConnection(jdbcUrl, USER, PASSWORD);
    }

    public static boolean validarUser(String usuario, int pin) {
        String query = "SELECT id, saldo FROM usuarios WHERE alias = ? AND pin = ?";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, usuario);
            preparedStatement.setInt(2, pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioId = resultSet.getInt("id");
                saldo = resultSet.getDouble("saldo");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validarPIN(int pin) {
        String query = "SELECT id, saldo FROM usuarios WHERE pin = ?";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioId = resultSet.getInt("id");
                saldo = resultSet.getDouble("saldo");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Consultar el saldo con el BD
    public double consultarSaldo() {
        String query = "SELECT saldo FROM usuarios WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, usuarioId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("saldo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //Actualizar el saldo de la BD
    private static void actualizarSaldo(){
        String updateQuery = "UPDATE usuarios SET saldo = ? WHERE id = ?";
        try (Connection connection = getConnection();
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery)){
            updateStatement.setDouble(1, saldo);
            updateStatement.setInt(2, usuarioId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }

    //Realizar un registro histórico en la tabla historico de la BD
    private static void registroHistorico(String tipoOperacion, double cantidad){
        String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, usuarioId);
            insertStatement.setString(2, tipoOperacion);
            insertStatement.setDouble(3, cantidad);
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Realizar un depósito
    public void realizarDeposito(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            actualizarSaldo();
            registroHistorico("depósito", cantidad);
        }
    }

    //Realizar un retiro
    public static void realizarRetiro(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            //System.out.println("Cantidad no válida.");
            saldo -= cantidad;
            actualizarSaldo();
            registroHistorico("retiro", cantidad);
        } 
    }

    //Cambiar el pin de la BD
    public static void cambiarPIN(int nuevoPin) {
        pinActual = nuevoPin;
        String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?";
        try (Connection connection = getConnection();
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, pinActual);
            updateStatement.setInt(2, usuarioId);
            updateStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Cambiar el alias de la BD
    public static void cambiarAlias(String nuevoAlias) {    
        String updateQuery = "UPDATE usuarios SET alias = ? WHERE id = ?";
        try (Connection connection = getConnection();
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setString(1, nuevoAlias);
            updateStatement.setInt(2, usuarioId);
            updateStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
