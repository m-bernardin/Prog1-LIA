import java.util.HashSet;
public class Step {
    HashSet<Ingredient> ingredients;
    String description;
    int time; 

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
    
}
