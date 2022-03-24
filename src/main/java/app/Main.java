
package app;

import Config.Conexion;

public class Main {
    
    public static void main(String[] args) {
        
        Conexion conexion = new Conexion();
        conexion.getConnection();
    }
}
