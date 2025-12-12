import java.util.ArrayList;
import java.util.HashSet;

public class ComplexRecipe extends Recipe{
    ArrayList<SimpleRecipe> subRecipes;
    
    public ComplexRecipe(String name, int servings){
        super(name, servings);
        subRecipes=new ArrayList<>();
    }
    public boolean addRecipe(SimpleRecipe recipe) {
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
        ArrayList<Ingredient> ingredients=new ArrayList<>();
        for(Recipe subRecipe:subRecipes){
            for(Ingredient ingredient:subRecipe.getIngredients()){
                int i=ingredientInList(ingredient,ingredients);
                if(i!=-1){
                    double newQty=ingredients.get(i).getQuantity()+ingredient.getQuantity();
                    ingredients.get(i).setQuantity(newQty);
                }
                else{
                    ingredients.add(ingredient);
                }
            }
        }
        return ingredients;
    }

    public ComplexRecipe scale(int factor) {
        ComplexRecipe scaledRecipe=new ComplexRecipe(name, servings*factor);
        for(SimpleRecipe recipe:subRecipes){
            scaledRecipe.addRecipe(recipe.scale(factor));
        }
        return scaledRecipe;
    }
    
    public ArrayList<SimpleRecipe> getRecipes() {
        return subRecipes;
    }

    private int ingredientInList(Ingredient ingredient,ArrayList<Ingredient> ingredients){
        for(int i=0; i<ingredients.size();i++){
            if(ingredient.getName()==ingredients.get(i).getName()){
                return i;
            }
        }
        return -1;
    }
}