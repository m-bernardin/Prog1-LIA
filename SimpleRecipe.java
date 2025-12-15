import java.util.ArrayList;
import java.util.HashSet;;
/**
 * A type of recipe which contains only Steps.
 * This class specifies many generic methods inherited from Recipe.
 * @version 1.0
 * @author Mathieu Bernardin
 * @see Recipe
 * @see Step
 */
public class SimpleRecipe extends Recipe {
    /**
     * The list of steps required to follow this recipe, in order of execution. 
     * @see Step
     */
    private ArrayList<Step> steps = new ArrayList<>();
    /**
     * The default constructor for this class. Functionally identical to the superclass' constructor.
     * @param name - the name of this recipe
     * @param servings - how many servings this recipe makes
     * @see Recipe
     */
    public SimpleRecipe(String name, int servings) {
        super(name, servings);
    }
    /**
     * Adds the specified Step to the end of the instructions for this recipe
     * @param step - the step to be added to this recipe
     * @return true if the provided step was succesfully added; false otherwise
     * @see Step
     */
    public boolean addStep(Step step) {
        Boolean invalid=false;
        for(Ingredient ingredient:step.getIngredients()){
            if(ingredient.getQuantity()<=0){
                invalid=true;
                break;
            }
        }
        if(!invalid){
            steps.add(step);
            return true;
        }
        return false;
    }
    /**
     * Calculates the total estimated time for this recipe, based on the estimated time of each of the current Steps, and assigns it to the time field
     * @return the total time calculated
     */
    public int calculateTime() {
        time=0;
        for(int i=0;i<steps.size();++i){
            time+=steps.get(i).getTime();
        }
        return time;
    }
    /**
     * Calculates all the ingredients used in this recipe.
     * @return the ArrayList of ingredients calculated 
     * @see Ingredient
     */
    public ArrayList<Ingredient> calculateIngredients() {
        ingredients=new ArrayList<>();
        for(int i=0;i<steps.size();++i){
            ArrayList<Ingredient> stepIngredients=steps.get(i).getIngredients();
            for(Ingredient ingredient:stepIngredients){
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }
    /**
     * Calculates all the equipment used in this recipe.
     * @return the HashSet of equipment calculated
     */
    public HashSet<String> calculateEquipment() {
        for(int i=0;i<steps.size();++i){
            equipment.add(steps.get(i).getEquipment());
        }
        return equipment;
    }
    /**
     * Scales this recipe by multiplying the quantity of required Ingredients for each of this recipe's Steps by the provided factor.
     * @param factor - the factor by which to scale this recipe
     * @return this recipe scaled by the provided factor
     * @see Step
     * @see Ingredient
     */
    public SimpleRecipe scale(int factor) {
        SimpleRecipe scaledRecipe = new SimpleRecipe(name, servings*factor);
        for(int i=0;i<steps.size();++i){
            scaledRecipe.addStep(steps.get(i).scale(factor));
        }
        return scaledRecipe;
    }
    public ArrayList<Step> getSteps() {
        return steps;
    }
    /**
     * Formulates all known Ingredients as a single String.
     * @return the formulated String
     * @see Ingredient
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
     * Formulates all known Steps as a single String.
     * @return the formulated String
     * @see Step
     */
    public String getStepsAsString()
    {
        String stepsString="";
        for(int i=0;i<steps.size();++i){
            stepsString+=(i+1)+": "+steps.get(i).getDescripton()+"\n";
        }
        return stepsString;
    }
    /**
     * Formulates this recipe as a single String.
     * @return the formulated String
     */
    public String formatAsString()
    {
        String recipeString="";
        recipeString+="======"+name+"======\n";
        recipeString+="  Serves: "+servings+"\n";
        recipeString+="  Rating: "+rating+"\n\n";
        recipeString+=introduction+"\n\n============\n\n";
        recipeString+="Ingredients: \n"+getIngredientsAsString()+"\n";
        recipeString+="Steps: \n"+getStepsAsString()+"\n";
        recipeString+="============";
        return recipeString;
    }
    /**
     * Formats this SimpleRecipe as a String which fits within the fromatting of a ComplexRecipe.
     * @return The formatted String.
     * @see ComplexRecipe
     */
    public String formatAsSubRecipeString()
    {
        String recipeString="";
        recipeString+="-----"+name+"-----\n";
        recipeString+=introduction+"\n\n-------------\n\n";
        recipeString+="Ingredients: \n"+getIngredientsAsString()+"\n";
        recipeString+="Steps: \n"+getStepsAsString()+"\n";
        recipeString+="------------";
        return recipeString;
    }
    /**
     * Sets this recipe to be complete by calling all calculate methods of this recipe. 
     * For use by the RecipeBookManager
     * @see RecipeBookManager
     */
    public void completeRecipe(){
        calculateIngredients();
        calculateTime();
        calculateEquipment();
        recipeCompleteFlag=true;
    }
}
