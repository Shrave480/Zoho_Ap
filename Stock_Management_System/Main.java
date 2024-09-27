import java.util.*;
public class Main {
    public static void main(String[] args) {
        StockManagementSystem system = new StockManagementSystem();

        // Create products
        Product product1 = new Product("Laptop", 1000.00);
        Product product2 = new Product("Phone", 500.00);

        // Add products to the system
        system.addProduct(product1);
        system.addProduct(product2);

        system.stockProductsDetails();
        // Create a purchase bill
        Map<Product, Integer> purchaseProducts = new HashMap<>();
        purchaseProducts.put(product1, 10);
        purchaseProducts.put(product2, 20);
        PurchaseBill pb1 = new PurchaseBill("PB1", purchaseProducts);
        system.addPurchaseBill(pb1);

        // Receive the purchase bill
        pb1.receive();

        // Create a sales bill
        Map<Product, Integer> salesProducts = new HashMap<>();
        salesProducts.put(product1, 5);
        SalesBill sb1 = new SalesBill("SB1", salesProducts);
        system.addSalesBill(sb1);

        // Deliver the sales bill
        sb1.deliver();

        // Metrics example
        System.out.println("Stock of Laptop: " + system.getCurrentStock(product1));
        System.out.println("Overall Stock Value: " + system.getOverallStockValue());
    }
}