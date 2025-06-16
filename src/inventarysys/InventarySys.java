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
                createProductsQueries(stmt);

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
    
    
    //Lista de productos predeterminada prueba
    public static void createProductsQueries(Statement stmt) throws SQLException {
        String insert1 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(1, 'Laptop 15\"', 10, 'Lenovo', 'Electrónica', 500.00, 700.00);";
        String insert2 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(2, 'Mouse Inalámbrico', 50, 'Logitech', 'Accesorio', 10.00, 20.00);";
        String insert3 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(3, 'Teclado Mecánico', 30, 'Redragon', 'Accesorio', 25.00, 45.00);";
        String insert4 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(4, 'Monitor 24\"', 15, 'Samsung', 'Electrónica', 120.00, 180.00);";
        String insert5 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(5, 'Disco SSD 1TB', 20, 'Kingston', 'Almacenamiento', 60.00, 90.00);";
        String insert6 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(6, 'Memoria RAM 8GB', 40, 'Corsair', 'Componente', 30.00, 50.00);";
        String insert7 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(7, 'Silla Gamer', 8, 'Cougar', 'Mobiliario', 90.00, 150.00);";
        String insert8 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(8, 'Impresora Láser', 12, 'HP', 'Electrónica', 85.00, 130.00);";
        String insert9 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(9, 'Router WiFi', 25, 'TP-Link', 'Redes', 20.00, 35.00);";
        String insert10 = "INSERT OR IGNORE INTO Products(id, description, quantity, brand, type, buyPrice, sellPrice) VALUES(10, 'Cámara Web HD', 18, 'Logitech', 'Accesorio', 15.00, 28.00);";

        stmt.execute(insert1);
        stmt.execute(insert2);
        stmt.execute(insert3);
        stmt.execute(insert4);
        stmt.execute(insert5);
        stmt.execute(insert6);
        stmt.execute(insert7);
        stmt.execute(insert8);
        stmt.execute(insert9);
        stmt.execute(insert10);
    }
    
}
