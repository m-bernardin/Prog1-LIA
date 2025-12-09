import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Mathieu Bernardin
 */
public class Recipe {

    protected String name;
    protected int servings;
    protected Integer rating;
    protected String introduction;

    public Recipe(String name, int servings) {
        this.name = name;
        this.servings = servings;
        this.rating = 0;
    }

    public void upVote() {
        rating += 1;
    }
    public void downVote() {
        rating -= 1;
    }
    
    public int getRating() {
        return rating;
    }

    public void setRating(int rating){
        this.rating=rating;
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
    
    public String getIntroduction()
    {
        return introduction;
    }

    public int getServings()
    {
        return servings;
    }

    public void setServings(int servings){
        this.servings=servings;
    }

    public String formatRecipeAsString()
    {
        return "this is the generic form of this method, it is overidden with SimpleRecipe and ComplexRecipe";
    }
}
