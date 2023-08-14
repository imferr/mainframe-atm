package bo.edu.ucb.sis213;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    private static int usuarioId;
    private static double saldo;
    private static int pinActual;

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 3306;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int intentos = 3;

        System.out.println("Bienvenido al Cajero Automático.");

        Connection connection = null;
        try {
            connection = getConnection(); // Reemplaza esto con tu conexión real
        } catch (SQLException ex) {
            System.err.println("No se puede conectar a Base de Datos");
            ex.printStackTrace();
            System.exit(1);
        }
        

        while (intentos > 0) {
            System.out.print("Ingrese su PIN de 4 dígitos: ");
            int pinIngresado = scanner.nextInt();
            if (validarPIN(connection, pinIngresado)) {
                pinActual = pinIngresado;
                mostrarMenu();
                break;
            } else {
                intentos--;
                if (intentos > 0) {
                    System.out.println("PIN incorrecto. Le quedan " + intentos + " intentos.");
                } else {
                    System.out.println("PIN incorrecto. Ha excedido el número de intentos.");
                    System.exit(0);
                }
            }
        }
    }

    public static boolean validarPIN(Connection connection, int pin) {
        String query = "SELECT id, saldo FROM usuarios WHERE pin = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pin);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                usuarioId = resultSet.getInt("id");
                saldo = resultSet.getDouble("saldo");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nBienvenido/a, " + obtenerNombreUsuario() + "!");
            System.out.println("Menú Principal:");
            System.out.println("1. Consultar saldo.");
            System.out.println("2. Realizar un depósito.");
            System.out.println("3. Realizar un retiro.");
            System.out.println("4. Cambiar PIN.");
            System.out.println("5. Salir.");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    consultarSaldo();
                    break;
                case 2:
                    realizarDeposito();
                    break;
                case 3:
                    realizarRetiro();
                    break;
                case 4:
                    cambiarPIN();
                    break;
                case 5:
                    System.out.println("Gracias por usar el cajero. ¡Hasta luego!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    public static String obtenerNombreUsuario() {
        String nombreUsuario = "";
        try {
            Connection connection = getConnection();
            String query = "SELECT nombre FROM usuarios WHERE pin = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pinActual);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                nombreUsuario = resultSet.getString("nombre");
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.err.println("Error al obtener el nombre del usuario.");
        }
        return nombreUsuario;
    }


    public static void consultarSaldo() {
        System.out.println("Su saldo actual es: $" + saldo);
    }

    public static void realizarDeposito() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad a depositar: $");
        double cantidad = scanner.nextDouble();

        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
        } else {
            saldo += cantidad;
            System.out.println("Depósito realizado con éxito. Su nuevo saldo es: $" + saldo);

            // Actualizar el saldo en la base de datos
            try {
                Connection connection = getConnection();
                
                // Actualizar el saldo en la tabla de usuarios
                String updateQuery = "UPDATE usuarios SET saldo = ? WHERE id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setDouble(1, saldo);
                updateStatement.setInt(2, usuarioId);
                updateStatement.executeUpdate();

                // Registrar la operación de depósito en la tabla de historico
                String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, 'depósito', ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setInt(1, usuarioId);
                insertStatement.setDouble(2, cantidad);
                insertStatement.executeUpdate();

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.err.println("Error al actualizar el saldo en la base de datos.");
            }
        }
    }



    public static void realizarRetiro() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad a retirar: $");
        double cantidad = scanner.nextDouble();

        if (cantidad <= 0) {
            System.out.println("Cantidad no válida.");
        } else if (cantidad > saldo) {
            System.out.println("Saldo insuficiente.");
        } else {
            saldo -= cantidad;
            System.out.println("Retiro realizado con éxito. Su nuevo saldo es: $" + saldo);

            try {
                Connection connection = getConnection();
                
                // Actualizar el saldo en la tabla de usuarios
                String updateQuery = "UPDATE usuarios SET saldo = ? WHERE id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setDouble(1, saldo);
                updateStatement.setInt(2, usuarioId);
                updateStatement.executeUpdate();

                // Registrar la operación de retiro en la tabla de historico
                String insertQuery = "INSERT INTO historico (usuario_id, tipo_operacion, cantidad) VALUES (?, 'retiro', ?)";
                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                insertStatement.setInt(1, usuarioId);
                insertStatement.setDouble(2, cantidad);
                insertStatement.executeUpdate();

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.err.println("Error al actualizar el saldo en la base de datos.");
            }
        }
    }


    public static void cambiarPIN() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese su PIN actual: ");
        int pinIngresado = scanner.nextInt();

        if (pinIngresado == pinActual) {
            System.out.print("Ingrese su nuevo PIN: ");
            int nuevoPin = scanner.nextInt();
            System.out.print("Confirme su nuevo PIN: ");
            int confirmacionPin = scanner.nextInt();

            if (nuevoPin == confirmacionPin) {
                pinActual = nuevoPin;
                System.out.println("PIN actualizado con éxito.");

                // Actualizar el PIN en la base de datos
                try {
                    Connection connection = getConnection();
                    String updateQuery = "UPDATE usuarios SET pin = ? WHERE id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setInt(1, pinActual);
                    updateStatement.setInt(2, usuarioId);
                    updateStatement.executeUpdate();
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.err.println("Error al actualizar el PIN en la base de datos.");
                }
            } else {
                System.out.println("Los PINs no coinciden.");
            }
        } else {
            System.out.println("PIN incorrecto.");
        }
    }
}
