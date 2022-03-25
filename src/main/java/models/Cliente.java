
package models;

public class Cliente {
    private Integer id;
    private String nombre;
    private String direccion;
    private String password;
    private String dni;
    private String telefono;

    public Cliente() {
    }

    public Cliente(String nombre, String direccion, String password, String dni, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.password = password;
        this.dni = dni;
        this.telefono = telefono;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + 
                ", nombre=" + nombre + 
                ", direccion=" + direccion + 
                ", password=" + password + 
                ", dni=" + dni + 
                ", telefono=" + telefono + 
            '}';
    }
  
}
