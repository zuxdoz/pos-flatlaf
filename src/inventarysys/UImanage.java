/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventarysys;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author circo
 */
public class UImanage {
    
    public static void changeTheme(String theme){
        
        try{
            switch(theme.toLowerCase()){
                case "dark" -> UIManager.setLookAndFeel(new FlatDarkLaf());
                case "light" -> UIManager.setLookAndFeel(new FlatLightLaf());
                default -> System.out.println("\n ERROR: Tema no existente");
            }
            
            
        for (java.awt.Window window : java.awt.Window.getWindows()) {
            javax.swing.SwingUtilities.updateComponentTreeUI(window);
            window.pack(); // Ajusta el layout al nuevo tema
        }

        }catch(UnsupportedLookAndFeelException e){
            e.printStackTrace();
        }
    }
    
}
