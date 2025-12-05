public class Ingredient {
    
    String name;
    double quantity; 
    Units unit;

    public Ingredient(String name, double quantity, Units unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }
}
