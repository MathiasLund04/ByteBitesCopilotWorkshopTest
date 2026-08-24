package dk.zealand;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final String[][] DISHES = {
            {"Festivalburger", "59"},
            {"Sprøde fritter", "35"},
            {"Vegansk bowl", "65"}
    };
    
    private static final OrderManager orderManager = new OrderManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("ByteBites – festivalens foodtruck");

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> showDishes();
                case "2" -> createOrder(scanner);
                case "0" -> running = false;
                default -> System.out.println(
                        "Ugyldigt valg. Vælg 0, 1 eller 2."
                );
            }
        }

        System.out.println("Programmet er afsluttet.");
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Vis retter");
        System.out.println("2. Opret bestilling");
        System.out.println("0. Afslut");
        System.out.print("Vælg: ");
    }

    private static void showDishes() {
        System.out.println("Retter:");

        for (int i = 0; i < DISHES.length; i++) {
            System.out.printf("%d. %s (%s kr.)%n", i + 1, DISHES[i][0], DISHES[i][1]);
        }
    }
    
    private static void createOrder(Scanner scanner) {
        if (!orderManager.canAddMoreOrders()) {
            System.out.println("Fejl: Der kan maksimalt gemmes 10 bestillinger.");
            return;
        }
        
        System.out.println();
        System.out.println("--- Opret bestilling ---");
        
        // Spørg efter ret
        showDishes();
        System.out.print("Vælg ret (1-3): ");
        String dishChoice = scanner.nextLine().trim();
        
        int dishIndex;
        try {
            dishIndex = Integer.parseInt(dishChoice) - 1;
            if (dishIndex < 0 || dishIndex >= DISHES.length) {
                System.out.println("Fejl: Du skal vælge 1, 2 eller 3.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Indtast et tal mellem 1 og 3.");
            return;
        }
        
        // Spørg efter antal
        System.out.print("Antal: ");
        String quantityInput = scanner.nextLine().trim();
        
        int quantity;
        try {
            quantity = Integer.parseInt(quantityInput);
            if (quantity <= 0) {
                System.out.println("Fejl: Antal skal være positivt (større end 0).");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Fejl: Antal skal være et helt tal.");
            return;
        }
        
        // Opret bestilling
        if (orderManager.addOrder(dishIndex, quantity)) {
            Order lastOrder = orderManager.getOrders().get(orderManager.getOrders().size() - 1);
            System.out.println();
            System.out.println("Bestilling oprettet!");
            System.out.println("Bestilling #" + lastOrder.getId() + ": " + DISHES[dishIndex][0] + 
                             " (Antal: " + quantity + ") - Status: " + lastOrder.getStatus());
            System.out.println();
        } else {
            System.out.println("Fejl: Kunne ikke oprette bestilling.");
        }
    }
}

