package dk.zealand;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static final int MAX_ORDERS = 10;
    private final List<Order> orders;
    
    public OrderManager() {
        this.orders = new ArrayList<>();
    }
    
    public boolean addOrder(int dishIndex, int quantity) {
        // Validering: dishIndex skal være 0-2
        if (dishIndex < 0 || dishIndex > 2) {
            return false;
        }
        
        // Validering: quantity skal være positivt
        if (quantity <= 0) {
            return false;
        }
        
        // Validering: maksimalt 10 bestillinger
        if (orders.size() >= MAX_ORDERS) {
            return false;
        }
        
        // Opret og gem bestilling
        Order order = new Order(dishIndex, quantity);
        orders.add(order);
        return true;
    }
    
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
    
    public int getOrderCount() {
        return orders.size();
    }
    
    public boolean canAddMoreOrders() {
        return orders.size() < MAX_ORDERS;
    }
}
