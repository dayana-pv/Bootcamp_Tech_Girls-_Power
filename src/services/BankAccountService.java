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
            dni = scanner.next();
            customer = customerService.findCustomerByDni(dni);
            if (customer == null) {
                System.out.println("El cliente con DNI " + dni + " no existe. Intente nuevamente");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Ingrese el tipo de cuenta (AHORROS o CORRIENTE): ");
            accountType = AccountType.valueOf(scanner.next().toUpperCase());
            if (!accountType.equals(AccountType.AHORROS) && !accountType.equals(AccountType.CORRIENTE)){
                System.out.println("El tipo de cuenta solo puede ser AHORROS o CORRIENTE. Intente nuevamente.");
                continue;
            }
            break;
        }

        System.out.print("Desea agregar saldo a su cuenta (S / N)? ");
        String resp = scanner.next();
        if(resp.equalsIgnoreCase("S")){
            while(true) {
                System.out.print("Ingrese el saldo en la cuenta bancaria: ");
                balance = scanner.nextDouble();
                if (accountType.equals(AccountType.AHORROS) && balance < 0) {
                    System.out.println("El saldo no puede ser negativo. Intente nuevamente.");
                    continue;
                }
                if (accountType.equals(AccountType.CORRIENTE) && balance < -500.00) {
                    System.out.println("El saldo no puede ser menor a 500.00. Intente nuevamente.");
                    continue;
                }
                break;
            }
        } else {
            balance = 0.0;
        }

        String accountNumber = generateAccountNumber();

        BankAccount newAccount = new BankAccount(customer, accountNumber, balance, accountType);
        bankAccounts.add(newAccount);

        System.out.println("Cuenta bancaria creada con éxito. Número de cuenta: " + accountNumber);
        return newAccount;
    }

    /**
     + Método para depositar saldo a una cuenta
     */
    public void depositBalance() {
        System.out.print("Ingrese número de cuenta: ");
        String accountNumber = scanner.next();

        BankAccount bankAccount = findBankAccountByAccountNumber(accountNumber);
        if(bankAccount == null) {
            System.out.println("Número de cuenta inválido. Intente nuevamente.");
            return;
        }

        System.out.print("Ingrese monto a depositar: ");
        Double amount = scanner.nextDouble();
        if(amount <= 0.0) {
            System.out.println("Monto a depositar inválido. Intente nuevamente.");
            return;
        }

        Double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);
        System.out.println("Saldo agregado exitosamente. Nuevo saldo: " + newBalance);
    }

    /**
     * Método para retirar saldo de una cuenta
     */
    public void removeBalance() {
        System.out.print("Ingrese número de cuenta: ");
        String accountNumber = scanner.next();

        BankAccount bankAccount = findBankAccountByAccountNumber(accountNumber);
        if(bankAccount == null) {
            System.out.println("Número de cuenta inválido. Intente nuevamente.");
            return;
        }

        System.out.print("Ingrese saldo a retirar: ");
        Double amount = scanner.nextDouble();
        Double newBalance = bankAccount.getBalance() - amount;

        if (bankAccount.getAccountType().equals(AccountType.AHORROS) && newBalance < 0) {
            System.out.println("No tiene suficiente saldo para realizar el retiro.");
            return;
        }
        if (bankAccount.getAccountType().equals(AccountType.CORRIENTE) && newBalance < -500.00) {
            System.out.println("No puede retirar más allá de su límite de sobregiro.");
            return;
        }

        // Si pasa las verificaciones, actualizar el saldo
        bankAccount.setBalance(newBalance);
        System.out.println("Retiro realizado exitosamente. Nuevo saldo: " + newBalance);
    }

    /**
     * Método para consultar el saldo de una cuenta
     */
    public void checkBalance() {
        System.out.print("Ingrese número de cuenta: ");
        String accountNumber = scanner.next();

        BankAccount bankAccount = findBankAccountByAccountNumber(accountNumber);
        if(bankAccount == null) {
            System.out.println("Número de cuenta inválido. Intente nuevamente.");
            return;
        }

        System.out.println("El saldo de la cuenta " + accountNumber + " es: " + bankAccount.getBalance());
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
     */
    public void printBankAccountByCustomer() {
        System.out.print("Ingrese DNI: ");
        String dni = scanner.next();

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
        bankAccounts.add(new BankAccount(customerService.findCustomerByDni("12345678"), generateAccountNumber(), 450.00, AccountType.AHORROS));
        bankAccounts.add(new BankAccount(customerService.findCustomerByDni("12445878"), generateAccountNumber(), 900.00, AccountType.CORRIENTE));
        bankAccounts.add(new BankAccount(customerService.findCustomerByDni("73612345"), generateAccountNumber(), 400.00, AccountType.AHORROS));
        bankAccounts.add(new BankAccount(customerService.findCustomerByDni("73842900"), generateAccountNumber(), -250.00, AccountType.CORRIENTE));
    }

    /**
     + Método utilitario para generar un número de cuenta aleatoria
     */
    private String generateAccountNumber() {
        return String.format("%08d", (int)(Math.random() * 100000000));
    }
}
