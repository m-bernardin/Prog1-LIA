import java.util.ArrayList;
import java.util.HashSet;
/**
 * A type of recipe which contains multiple smaller subrecipes, generally these are SimpleRecipes.
 * This class specifies many generic methods inherited from Recipe.
 * @version 1.0
 * @author Mathieu Bernardin
 * @see Recipe
 * @see SimpleRecipe
 */
public class ComplexRecipe extends Recipe{
    /**
     * The subrecipes which this ComplexRecipe is made up of.
     */
    private ArrayList<Recipe> subRecipes;
    /**
     * The default constructor for this class. Functionally identical to the superclass' constructor.
     * @param name - the name of this recipe
     * @param servings - how many servings this recipe makes
     * @see Recipe
     */
    public ComplexRecipe(String name,int servings){
        super(name, servings);
        subRecipes=new ArrayList<>();
    }
    /**
     * Adds a subrecipe to the ComplexRecipe
     * @param recipe - the subrecipe to be added
     * @return true if the provided subrecipe was succesfully added, otherwise false
     */
    public boolean addRecipe(Recipe recipe) {
        subRecipes.add(recipe);
        return true;
    }
    /**
     * Calculates the total estimated time based on the estimated time of the subrecipes.
     * @return the total time calculated
     */
    public int calculateTime() {
        int totalTime=0;
        for(Recipe recipe:subRecipes){
            totalTime+=recipe.getTime();
        }
        return totalTime;
    }
    /**
     * Calculates the ingredients used in this ComplexRecipe based on the ingredients used in the subrecipes.
     * @return an ArrayList of the ingredients needed
     */
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
    /**
     * Calculates the equipment used in this ComplexRecipe based on the equipment used in the subrecipes.
     * @return an HashSet of the equipment needed
     */
    public HashSet<String> calculateEquipment() {
        HashSet<String> equipment=new HashSet<>();
        for(Recipe subRecipe:subRecipes){
            for(String item:subRecipe.calculateEquipment()){
                equipment.add(item);
            }
        }
        return equipment;
    }
    /**
     * Scales the serving size this ComplexRecipe by the provided factor
     * @param factor - the factor by which this ComplexRecipe should be scaled
     * @return a scaled version of this ComplexRecipe
     */
    public ComplexRecipe scale(int factor) {
        ComplexRecipe scaledRecipe=new ComplexRecipe(name, servings*factor);
        for(Recipe recipe:subRecipes){
            scaledRecipe.addRecipe(recipe.scale(factor));
        }
        return scaledRecipe;
    }
    public ArrayList<Recipe> getRecipes() {
        return subRecipes;
    }
    /**
     * Checks if an ingredient is already present in an ArrayList
     * @param ingredient - the ingredient to be checked
     * @param ingredients - the ArrayList to be checked against
     * @return true if the ingredient is present, otherwise false
     */
    private int ingredientInList(Ingredient ingredient,ArrayList<Ingredient> ingredients){
        for(int i=0; i<ingredients.size();i++){
            if(ingredient.getName()==ingredients.get(i).getName()){
                return i;
            }
        }
        return -1;
    }
    /**
     * Formats the recipe as a String for display
     * @return The formatted String
     */
    public String formatAsString(){
        String recipeString="";
        recipeString+="======"+name+"======\n";
        recipeString+="  Serves: "+servings+"\n";
        recipeString+="  Rating: "+rating+"\n\n";
        recipeString+="Equipment"+equipment+"\n\n============\n\n";
        recipeString+="Ingredients: \n"+getIngredientsAsString()+"\n";
        System.out.println(subRecipes.size());
        recipeString+="\n"+getSubRecipesAsString()+"\n";
        recipeString+="============";
        return recipeString;
    }
    /**
     * Formats the recipe as a String for display as a subrecipe
     * @return The formatted String
     */
    private  String getSubRecipesAsString(){
        String subRecipesString="";
        for(Recipe subRecipe:subRecipes){
            subRecipesString+=subRecipe.formatAsSubRecipeString()+"\n";
        }
        return subRecipesString;
    }
    /**
     * Formats the ingredients as a String for display as a subrecipe
     * @return The formatted String
     */
    public String getIngredientsAsString()
    {
        String ingredientsString="";
        for(Ingredient ingredient:ingredients){
            ingredientsString=ingredientsString+ingredient.getQuantity()+ingredient.getUnit()+" of "+ingredient.getName()+"\n";
        }
        return ingredientsString;
    }
    /**
     * Indicates wether another Recipe is essentially the same as this one. Two recipes are evaluated to be the same if they share a name, 
     * @param recipe - the recipe to be compared to
     * @return true if they are evaluated to be equal; false otherwise
     */
    public boolean equals(Recipe recipe){
        if(name.equals(recipe.getName())&&servings==recipe.getServings()){
            return true;
        }
        return false;
    }
    /**
     * @return a representation of this recipe as a String.
     */
    public String toString(){
        return name+" for "+servings;
    }
}