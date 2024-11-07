package services;

import domain.Customer;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 + Clase con las operaciones disponibles del cliente
 */
public class CustomerService {
    private ArrayList<Customer> customers = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in).useDelimiter("\r?\n");

    public CustomerService() {
        initLocalCustomers();
    }

    /**
     + Método para registrar un cliente
     * @return      cliente registrado
     */
    public Customer registerCustomer() {
        String dni, name, lastname, email;

        while (true) {
            System.out.print("Ingrese el DNI del cliente: ");
            dni = scanner.nextLine();
            if (dni.isEmpty()) {
                System.out.println("El DNI es obligatorio. Intente nuevamente.");
                continue;
            }
            if (isDniExists(dni)) {
                System.out.println("El DNI ya está registrado.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Ingrese el nombre del cliente: ");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("El nombre es obligatorio. Intente nuevamente.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Ingrese los apellidos del cliente: ");
            lastname = scanner.nextLine();
            if (lastname.isEmpty()) {
                System.out.println("El apellido es obligatorio. Intente nuevamente.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Ingrese el email del cliente:");
            email = scanner.nextLine();
            if (email.isEmpty()) {
                System.out.println("El email es obligatorio. Intente nuevamente.");
                continue;
            }
            if (!isValidEmail(email)) {
                System.out.println("El formato de email es inválido. Intente nuevamente.");
                continue;
            }
            break;
        }

        Customer customer = new Customer(dni, name, lastname, email);
        customers.add(customer);

        System.out.println("Cliente registrado con éxito.");
        return customer;
    }

    /**
     + Método para obtener un cliente por su DNI
     * @param dni    DNI del cliente a obtener
     * @return       cliente encontrado
     */
    public Customer findCustomerByDni(String dni) {
        for (Customer customer : customers) {
            if (customer.getDni().equals(dni)) {
                return customer;
            }
        }

        return null;
    }

    /**
     + Método para imprimir todos los clientes
     */
    public void printCustomer(){
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    /**
     + Método para comprobar si el DNI ya está registrado
     * @param dni   DNI del cliente a validar
     * @return      verdadero si está registrado, falso si no lo está
     */
    private boolean isDniExists(String dni) {
        for (Customer customer : customers) {
            if (customer.getDni().equals(dni)) {
                return true; // El DNI ya existe
            }
        }
        return false; // El DNI no existe
    }

    /**
     + Método para validar el formato de email utilizando una expresión regular
     * @param email  email del cliente a validar
     * @return       verdadero si es válido, falso si no lo es
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     + Método para cargar una lista de clientes inicial
     */
    private void initLocalCustomers() {
        customers.add(new Customer("12345678", "Maria", "Sanchez Ruiz", "maria13@gmail.com"));
        customers.add(new Customer("73612345", "Gustavo", "Quintanilla Osorio", "gosorio@gmail.com"));
        customers.add(new Customer("73842900", "Pablo", "Garcia Garcia", "pgarcia@gmail.com"));
        customers.add(new Customer("80230213", "Amanda", "Marin Perez", "amarin@gmail.com"));
    }
}
