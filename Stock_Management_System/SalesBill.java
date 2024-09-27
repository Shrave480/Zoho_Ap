import java.time.LocalDate;
import java.util.Map;

public class SalesBill {
    private String id;
    private Map<Product, Integer> products; // Product and quantity
    private LocalDate date;
    private String status; // Paid or Delivered

    public SalesBill(String id, Map<Product, Integer> products) {
        this.id = id;
        this.products = products;
        this.status = "Paid";
        this.date = LocalDate.now();
    }

    // Process when SB moves from Paid to Delivered
    public void deliver() {
        if (status.equals("Paid")) {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                if (product.getStock() >= quantity) {
                    product.setStock(product.getStock() - quantity);
                } else {
                    System.out.println("Insufficient stock for product: " + product.getName());
                }
            }
            status = "Delivered";
        }
    }

    // Getters
    public LocalDate getDate(){ 
        return date; 
    }
    public String getStatus(){ 
        return status; 
    }

    public Map<Product, Integer> getProducts(){
        return products;
    }
}