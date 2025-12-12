import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Mathieu Bernardin
 */
public class SimpleRecipe extends Recipe {

    private ArrayList<Step> steps = new ArrayList<>();
    
    public SimpleRecipe(String name, int servings) {
        super(name, servings);
    }
    
    public boolean addStep(Step step) {
        steps.add(step);
        return true;
    }

    public int calculateTime() {
        time=0;
        for(int i=0;i<steps.size();++i){
            time+=steps.get(i).getTime();
        }
        return time;
    }

    public ArrayList<Ingredient> calculateIngredients() {
        ArrayList<Ingredient> recipeIngredients=new ArrayList<>();
        for(int i=0;i<steps.size();++i){
            ArrayList<Ingredient> stepIngredients=steps.get(i).getIngredients();
            for(Ingredient ingredient:stepIngredients){
                recipeIngredients.add(ingredient);
            }
        }
        return recipeIngredients;
    }

    public void calculateEquipment() {
        for(int i=0;i<steps.size();++i){
            equipment.add(steps.get(i).getEquipment());
        }
    }

    public SimpleRecipe scale(int factor) {
        return null;
    }
    
    public ArrayList<Step> getSteps() {
        return steps;
    }

    public String getIngredientsAsString()
    {
        String ingredientsString="";
        for(Ingredient ingredient:ingredients){
            ingredientsString=ingredientsString+ingredient.getQuantity()+ingredient.getUnit()+" of "+ingredient.getName()+"\n";
        }
        return ingredientsString;
    }

    public String getStepsAsString()
    {
        String stepsString="";
        for(int i=0;i<steps.size();++i){
            stepsString+=(i+1)+": "+steps.get(i).getDescripton()+"\n";
        }
        return stepsString;
    }

    public String formatRecipeAsString()
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

    public void completeRecipe(){
        calculateIngredients();
        calculateTime();
        recipeCompleteFlag=true;
    }
}
