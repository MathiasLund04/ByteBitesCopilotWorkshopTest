package dk.zealand;

public class Order {
    private static int nextId = 1;
    
    private final int id;
    private final int dishIndex;
    private final int quantity;
    private final String status;
    
    public Order(int dishIndex, int quantity) {
        this.id = nextId++;
        this.dishIndex = dishIndex;
        this.quantity = quantity;
        this.status = "MODTAGET";
    }
    
    public int getId() {
        return id;
    }
    
    public int getDishIndex() {
        return dishIndex;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public String getStatus() {
        return status;
    }
    
    @Override
    public String toString() {
        return String.format("Bestilling #%d: %s (Antal: %d) - Status: %s",
                id, "Ret " + (dishIndex + 1), quantity, status);
    }
}
