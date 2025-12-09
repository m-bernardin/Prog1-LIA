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

    public ArrayList<Ingredient> calculateIngredients() {
        ArrayList<Ingredient> ingredients=subRecipes.get(0).getIngredients();
        return ingredients;
    }

    public ComplexRecipe scale(int factor) {
        ComplexRecipe scaledRecipe=new ComplexRecipe(name, servings*factor);
        for(SimpleRecipe recipe:subRecipes){
            scaledRecipe.addSubRecipe(recipe.scale(factor));
        }
        return scaledRecipe;
    }
    
    public ArrayList<SimpleRecipe> getRecipes() {
        return subRecipes;
    }
}