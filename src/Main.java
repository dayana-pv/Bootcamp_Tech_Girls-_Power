
import services.BankAccountService;
import services.CustomerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\r?\n");
        CustomerService customerService = new CustomerService();
        BankAccountService bankAccountService = new BankAccountService();

        int opt;

        do {
            System.out.println("========================================================");
            System.out.println("BANCO");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Registrar Cuenta Bancaria");
            System.out.println("4. Listar Cuentas Bancarias");
            System.out.println("5. Depositar Dinero");
            System.out.println("6. Retirar Dinero");
            System.out.println("7. Consultar Saldo");
            System.out.println("0. Salir");
            System.out.println("========================================================");
            System.out.print("OPCIÓN: ");
            opt = scanner.nextInt();

            switch(opt) {
                case 1 :
                    System.out.println("\nREGISTRAR CLIENTE");
                    customerService.registerCustomer();
                    System.out.println();
                    break;

                case 2 :
                    System.out.println("\nLISTAR CLIENTES");
                    customerService.printCustomer();
                    System.out.println();
                    break;

                case 3 :
                    System.out.println("\nREGISTRAR CUENTA BANCARIA");
                    bankAccountService.openBankAccount();
                    System.out.println();
                    break;

                case 4 :
                    System.out.println("\nLISTAR CUENTAS BANCARIAS");
                    bankAccountService.printBankAccounts();
                    System.out.println();
                    break;

                case 5 :
                    System.out.println("\n");

                    System.out.println();
                    break;
            }
        } while(opt != 0);
    }
}