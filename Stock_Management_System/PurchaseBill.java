import java.time.LocalDate;
import java.util.Map;

public class PurchaseBill {
    private String id;
    private Map<Product, Integer> products; // Product and quantity
    private LocalDate date;
    private String status; // Paid or Received

    public PurchaseBill(String id, Map<Product, Integer> products) {
        this.id = id;
        this.products = products;
        this.status = "Paid";
        this.date = LocalDate.now();
    }

    // Process when PB moves from Paid to Received
    public void receive() {
        if (status.equals("Paid")) {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                product.setStock(product.getStock() + quantity);
            }
            status = "Received";
        }
    }

    // Getters
    public LocalDate getDate(){ 
        return date; 
    }
    public String getStatus(){ 
        return status; 
    }
    public Map<Product,Integer> getProducts(){
        return products;
    }
}
