import java.time.LocalDate;
import java.util.*;

public class StockManagementSystem {
    private List<Product> products;
    private List<PurchaseBill> purchaseBills;
    private List<SalesBill> salesBills;

    public StockManagementSystem() {
        this.products = new ArrayList<>();
        this.purchaseBills = new ArrayList<>();
        this.salesBills = new ArrayList<>();
    }

    // Add a product to the system
    public void addProduct(Product product) {
        products.add(product);
    }

    // Add purchase bill
    public void addPurchaseBill(PurchaseBill purchaseBill) {
        purchaseBills.add(purchaseBill);
    }

    // Add sales bill
    public void addSalesBill(SalesBill salesBill) {
        salesBills.add(salesBill);
    }

    // Generate current stock availability of a product
    public int getCurrentStock(Product product) {
        return product.getStock();
    }

    // Generate stock value of a product
    public double getStockValue(Product product) {
        return product.getStockValue();
    }

    // Get the quantity to receive
    public int getQuantityToReceive(Product product) {
        return product.getQuantityToReceive();
    }

    // Get the quantity needed to deliver the paid SB
    public int getQuantityToDeliver(Product product) {
        return product.getQuantityToDeliver();
    }

    // Generate overall stock value of the organization
    public double getOverallStockValue() {
        double totalValue = 0;
        for (Product product : products) {
            totalValue += product.getStockValue();
        }
        return totalValue;
    }

    // Quantity received for a product during a date range
    public int getQuantityReceived(Product product, LocalDate startDate, LocalDate endDate) {
        int totalReceived = 0;
        for (PurchaseBill pb : purchaseBills) {
            if (pb.getDate().isAfter(startDate) && pb.getDate().isBefore(endDate) && pb.getStatus().equals("Received")) {
                totalReceived += pb.getProducts().get(product);
            }
        }
        return totalReceived;
    }

    // Quantity delivered for a product during a date range
    public int getQuantityDelivered(Product product, LocalDate startDate, LocalDate endDate) {
        int totalDelivered = 0;
        for (SalesBill sb : salesBills) {
            if (sb.getDate().isAfter(startDate) && sb.getDate().isBefore(endDate) && sb.getStatus().equals("Delivered")) {
                totalDelivered += sb.getProducts().get(product);
            }
        }
        return totalDelivered;
    }

    public void stockProductsDetails(){
        System.out.println("name    price   stock   quantityToReceive   quantityToDeliver");
        for(Product prod : products){
            System.out.println(prod.getName()+"   "+prod.getPrice()+"   "+prod.getStock()+"   "+prod.getQuantityToReceive()+"   "+prod.getQuantityToDeliver());
        }
    }
}
