import java.util.HashSet;

/**
 * @author Mathieu Bernardin
 */
public class Step {
    private HashSet<Ingredient> ingredients;
    private String description;
    private int time; 

    public Step(HashSet<Ingredient> ingredients, String description, int time) {
        this.ingredients = ingredients;
        this.description = description;
        this.time = time;
    }

    public HashSet<Ingredient> getIngredients() {
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
}
