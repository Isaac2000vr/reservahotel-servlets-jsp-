package reservahotel.modelo;


public class Usuario {
    
    private int id;
    private String clave;
    private String nombre;
    private String rol;
    private String email;

    public Usuario() {
    }

    public Usuario(int id, String clave, String nombre, String rol, String email) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
        this.rol = rol;
        this.email = email;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
}
