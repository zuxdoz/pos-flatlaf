/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inventarysys;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import javax.swing.UIManager;

/**
 *
 * @author circo
 */
public class InventarySys {

    
    // Función principal de Java
    public static void main(String[] args) {
        Connect();
        
        //Inicia el formulario del login
        java.awt.EventQueue.invokeLater(() -> {
        new loginForm().setVisible(true);
        UImanage.changeTheme("light");

        });
    }

    // Conexión y/o creación de la base de datos 
    public static void Connect() {
        String url = "jdbc:sqlite:inventary.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                // Creación de la base de datos
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("El driver es: " + meta.getDriverName());
                System.out.println("Se ha creado la base de datos");

                // Creación de las tablas de la base de datos
                Statement stmt = conn.createStatement();
                createTableQueries(stmt);
                createUsersQueries(stmt);

                System.out.println("Conexión y configuración establecida correctamente");
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
    }

    // Consultas para crear las tablas de la base de datos
    public static void createTableQueries(Statement stmt) throws SQLException {
        String userTable = "CREATE TABLE IF NOT EXISTS Users("
                + " id INTEGER PRIMARY KEY,"
                + " name TEXT NOT NULL UNIQUE,"
                + " password TEXT NOT NULL"
                + ");";

        String suppliersTable = "CREATE TABLE IF NOT EXISTS Suppliers("
                + "id INTEGER PRIMARY KEY,"
                + "name TEXT NOT NULL UNIQUE,"
                + "contact TEXT NOT NULL,"
                + "phone TEXT,"
                + "email TEXT,"
                + "type TEXT NOT NULL,"
                + "description TEXT NOT NULL"
                + ");";

        String productTable = "CREATE TABLE IF NOT EXISTS Products("
                + "id INTEGER PRIMARY KEY,"
                + "description TEXT NOT NULL,"
                + "quantity INTEGER NOT NULL CHECK(quantity >= 0),"
                + "brand TEXT NOT NULL,"
                + "type TEXT NOT NULL,"
                + "buyPrice REAL NOT NULL CHECK(buyPrice >= 0),"
                + "sellPrice REAL NOT NULL CHECK(sellPrice >= 0)"
                + ");";

        String sellTable = "CREATE TABLE IF NOT EXISTS Sell("
                + "id INTEGER PRIMARY KEY,"
                + "userID INTEGER NOT NULL,"
                + "total FLOAT NOT NULL CHECK(total >= 0),"
                + "date TEXT NOT NULL,"
                + "commentary TEXT,"
                + "FOREIGN KEY(userID) REFERENCES Users(id)"
                + ");";

        String orderProductsTable = "CREATE TABLE IF NOT EXISTS OrderProducts("
                + "id INTEGER PRIMARY KEY,"
                + "productID INTEGER NOT NULL,"
                + "quantity INTEGER NOT NULL CHECK(quantity >= 1),"
                + "sellID INTEGER NOT NULL,"
                + "FOREIGN KEY(productID) REFERENCES Products(id),"
                + "FOREIGN KEY(sellID) REFERENCES Sell(id)"
                + ");";

        stmt.execute(userTable);
        stmt.execute(suppliersTable);
        stmt.execute(productTable);
        stmt.execute(sellTable);
        stmt.execute(orderProductsTable);
    }

    // Inserción de usuario admin por defecto
    public static void createUsersQueries(Statement stmt) throws SQLException {
        String userInsert = "INSERT OR IGNORE INTO Users(id, name, password) VALUES(1, 'admin', 'admin');";
        stmt.execute(userInsert);
    }
}
