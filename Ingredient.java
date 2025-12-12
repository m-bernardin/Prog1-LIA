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

    public boolean equals(Ingredient ingredient){
        System.out.println("name: "+name.equals(ingredient.getName()));
        System.out.println("unit: "+unit.equals(ingredient.getUnit()));
        System.out.println("qt: "+(quantity==ingredient.getQuantity()));
        if(name.equals(ingredient.getName())&&unit.equals(ingredient.getUnit())&&quantity==ingredient.getQuantity()){
            return true;
        }
        return false;
    }
    public String toString(){
        return ""+quantity+unit+" of "+name;
    }
    public Ingredient scale(int factor) {
        Ingredient scaledIngredient=new Ingredient(name, quantity*factor, unit);
        return scaledIngredient;
    }
}
