public class Product {
    private String name;
    private double price;
    private int stock; // Current stock
    private int quantityToReceive;
    private int quantityToDeliver;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.stock = 0;
        this.quantityToReceive = 0;
        this.quantityToDeliver = 0;
    }

    // Getters and Setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getStock(){ 
        return stock; 
    }
    public void setStock(int stock){ 
        this.stock = stock; 
    }
    public int getQuantityToReceive(){ 
        return quantityToReceive; 
    }
    public void setQuantityToReceive(int quantityToReceive){ 
        this.quantityToReceive = quantityToReceive; 
    }
    public int getQuantityToDeliver(){ 
        return quantityToDeliver; 
    }
    public void setQuantityToDeliver(int quantityToDeliver){ 
        this.quantityToDeliver = quantityToDeliver; 
    }
    public double getPrice(){ 
        return price; 
    }
    public double getStockValue(){ 
        return stock * price; 
    }
}
