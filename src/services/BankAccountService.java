package services;

import domain.AccountType;
import domain.BankAccount;
import domain.Customer;

import java.util.ArrayList;
import java.util.Scanner;

/**
 + Clase con las operaciones de cuenta bancaria
 */
public class BankAccountService {
    private ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    private CustomerService customerService = new CustomerService();
    private Scanner scanner = new Scanner(System.in).useDelimiter("\r?\n");

    public BankAccountService() {
        initLocalBankAccounts();
    }

    /**
     + Método para registrar una nueva cuenta bancaria
     * @return      cuenta bancaria aperturada
     */
    public BankAccount openBankAccount() {
        String dni;
        AccountType accountType;
        Customer customer;
        Double balance = 0.0;

        while (true) {
            System.out.print("Ingrese el DNI del cliente: ");
            dni = scanner.nextLine();
            customer = customerService.findCustomerByDni(dni);
            if (customer == null) {
                System.out.println("El cliente con DNI " + dni + " no existe. Intente nuevamente");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Ingrese el tipo de cuenta (AHORROS o CORRIENTE): ");
            accountType = AccountType.valueOf(scanner.nextLine().toUpperCase());
            if (!accountType.equals(AccountType.AHORROS) && !accountType.equals(AccountType.CORRIENTE)){
                System.out.println("El tipo de cuenta solo puede ser AHORROS o CORRIENTE. Intente nuevamente.");
                continue;
            }
            break;
        }

        System.out.println("Desea agregar saldo a su cuenta (S / N)? ");
        String resp = scanner.nextLine();
        while(resp.toUpperCase().equals("S")){
            System.out.println("Ingrese el saldo en la cuenta bancaria:");
            balance = scanner.nextDouble();
            if(accountType.equals(AccountType.AHORROS) && balance < 0){
                System.out.println("El saldo no puede ser negativo. Intente nuevamente.");
                continue;
            }
            if(accountType.equals(AccountType.CORRIENTE) && balance < -500.00){
                System.out.println("El saldo no puede ser menor a 500.00. Intente nuevamente.");
                continue;
            }
            break;
        }
        balance = 0.0;

        String accountNumber = "B000" + String.format("%08d", (int)(Math.random() * 100000000));

        BankAccount newAccount = new BankAccount(customer, accountNumber, balance, accountType);
        bankAccounts.add(newAccount);

        System.out.println("Cuenta bancaria creada con éxito. Número de cuenta: " + accountNumber);
        return newAccount;
    }

    

    /**
     + Método para buscar una cuenta bancaria por número de cuenta
     * @param accountNumber     número de cuenta
     * @return                  cuenta bancaria encontrada
     */
    public BankAccount findBankAccountByAccountNumber(String accountNumber) {
        for (BankAccount account : bankAccounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    /**
     + Método para listar las cuentas bancarias por cliente
     * @param dni       DNI del cliente
     */
    public void printBankAccountByCustomer(String dni) {
        for (BankAccount account : bankAccounts) {
            if (account.getCustomer().getDni().equals(dni)) {
                System.out.println(account);
            }
        }
    }

    /**
     + Método para imprimir todas las cuentas bancarias
     */
    public void printBankAccounts() {
        for (BankAccount account : bankAccounts) {
            System.out.println(account);
        }
    }

    /**
     + Método para cargar una lista de cuentas bancarias inicial
     */
    private void initLocalBankAccounts() {
        bankAccounts.add(new BankAccount(customerService.findCustomerByDni("12345678"), "", 450.00, AccountType.AHORROS));
    }
}
