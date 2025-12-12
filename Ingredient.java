public class Ingredient {
    
    String name;
    double quantity; 
    Units unit;

    public Ingredient(String name, double quantity, Units unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public double getQuantity() {
        return quantity;
    }

    public Units getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(double qty){
        quantity=qty;
    }

    public Ingredient scale(int factor) {
        Ingredient scaledIngredient=new Ingredient(name, quantity*factor, unit);
        return scaledIngredient;
    }
}
