import java.util.ArrayList;
import java.util.HashSet;

public class Recipe {

    String name;
    int servings;
    ArrayList<Ingredient> ingredients;
    HashSet<String> equipment;
    int rating;
    int time;
    HashSet<Tags> tags;
    
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

    public boolean addStep(){
        return false;
    }

    public boolean addRecipe(){
        return false;
    }

    public String getName() {
        return name;
    }
    public HashSet<Tags> getTags() {
        return tags;
    }

    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }

    public int getTime(){
        return time;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
