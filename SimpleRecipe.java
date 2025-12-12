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

    public void calculateTime() {
        time=0;
        for(int i=0;i<steps.size();++i){
            time+=steps.get(i).getTime();
        }
    }

    public void calculateIngredients() {
        ingredients=new ArrayList<>();
        for(int i=0;i<steps.size();++i){
            ArrayList<Ingredient> stepIngredients=steps.get(i).getIngredients();
            for(Ingredient ingredient:stepIngredients){
                ingredients.add(ingredient);
            }
        }
    }

    public void calculateEquipment() {
        for(int i=0;i<steps.size();++i){
            equipment.add(steps.get(i).getEquipment());
        }
    }

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

    public String getIngredientsAsString()
    {
        String ingredientsString="";
        for(Ingredient ingredient:ingredients){
            ingredientsString+=ingredient.getQuantity()+ingredient.getUnit()+" of "+ingredient.getName()+"\n";
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

    public String toString()
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

    public boolean equals(Recipe comparedRecipe){
        return comparedRecipe.toString().equals(this.toString());
    }
}
