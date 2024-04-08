package entity;

public class Cliente {

    private Integer id;

    private String nombre;

    private String apellido;

    private String email;

    public Cliente() {}

    public Cliente(Integer id, String nombre, String apellido, String email) {}

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Cliente\n" +
                "id: " + id +
                ", nombre: " + nombre +
                ", apellido: " + apellido  +
                ", email: " + email +
                "\n--------------------------------\n";
    }
}
