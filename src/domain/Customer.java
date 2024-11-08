package domain;

public class Customer {
    private String dni;
    private String name;
    private String lastname;
    private String email;

    public Customer(String dni, String name, String lastname, String email) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "DNI=" + dni +
                ", Nombre=" + name +
                ", Apellido=" + lastname +
                ", Email=" + email;
    }
}
