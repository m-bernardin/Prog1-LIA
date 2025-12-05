import java.util.ArrayList;
import java.util.HashSet;

public class Recipe {

    String name;
    int servings;
    HashSet<Ingredient> ingredients;
    HashSet<String> equipment;
    Integer rating;
    public Recipe(String name, int servings) {
        this.name = name;
        this.servings = servings;
        this.ingredients = new HashSet<>();
        this.equipment = new HashSet<>();
        this.rating = 0;
    }

    public void upVote() {
        rating += 1;
    }
    public void downVote() {
        rating -= 1;
    }
    
    public Integer getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }
    public HashSet<Tags> getTags() {
        return null;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
