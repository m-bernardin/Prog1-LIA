import java.util.ArrayList;
import java.util.HashSet;

public class ComplexRecipe extends Recipe{
    ArrayList<SimpleRecipe> subRecipes;
    
    public ComplexRecipe(String name, int servings){
        super(name, servings);
    }
    public boolean addSubRecipe(SimpleRecipe recipe) {
        subRecipes.add(recipe);
        return true;
    }

    public int calculateTime() {
        int totalTime=0;
        for(SimpleRecipe recipe:subRecipes){
            totalTime+=recipe.getTime();
        }
        return totalTime;
    }

    public HashSet<Ingredient> calculateIngrediants() {
        return null;
    }

    public SimpleRecipe scale(int factor) {
        return null;
    }
    
    public ArrayList<SimpleRecipe> getRecipes() {
        return subRecipes;
    }
}