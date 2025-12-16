/**
 * A representation of a single ingredient within a Step.
 * Contains a measure of quantity with a specified unit.
 * @version 1.0
 * @author Madeleine Siwick
 * @see Step
 */
public class Ingredient {
    /**
     * The name of this ingredient
     */
    private String name;
    /**
     * The quantity of this ingredient to be used
     */
    private double quantity; 
    /**
     * The unit this ingredient's quantity is measured in
     */
    private Units unit;
    /**
     * The default constructorb for this class
     * @param name - this ingredient's name
     * @param quantity - the quantity of this ingredient to be used
     * @param unit - the unit this ingredient's quantity is measured in
     */
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
    /**
     * Indicates wether another Ingredient is essentially the same as this one. Two Ingredients are evaluated to be the same if they share a name, 
     * @param ingredient - the ingredient to be compared to
     * @return true if they are evaluated to be equal; false otherwise
     */
    public boolean equals(Ingredient ingredient){
        if(name.equals(ingredient.getName())&&unit.equals(ingredient.getUnit())&&quantity==ingredient.getQuantity()){
            return true;
        }
        return false;
    }
    /**
     * @return a representation of this ingredient as a String.
     */
    public String toString(){
        return ""+quantity+unit+" of "+name;
    }
    /**
     * Scales this Ingredient by the provided factor
     * @param factor - the factor by which this Ingredient should be scaled
     * @return a scaled version of this Ingredient
     */
    public Ingredient scale(int factor) {
        Ingredient scaledIngredient=new Ingredient(name, quantity*factor, unit);
        return scaledIngredient;
    }
}
