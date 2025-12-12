import java.util.HashSet;
import java.util.ArrayList;

/**
 * @author Mathieu Bernardin
 */
public class Step {
    private ArrayList<Ingredient> ingredients;
    private String description;
    private int time; 
    private String equipment;

    public Step(ArrayList<Ingredient> ingredients, String description, int time,String equipment) {
        this.ingredients = ingredients;
        this.description = description;
        this.time = time;
        this.equipment=equipment;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getDescripton() {
        return description;
    }

    public int getTime() {
        return time;
    }
    
    public void setDescription(String description){
        this.description=description;
    }

    public void setTime(int time){
        this.time=time;
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
