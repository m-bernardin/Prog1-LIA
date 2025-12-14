import java.util.ArrayList;

/**
 * A representation of a single step within a SimpleRecipe. 
 * @version 1.0
 * @author Mathieu Bernardin
 * @see SimpleRecipe
 */
public class Step {
    /**
     * An ArrayList containing all Ingredients used in this step.
     */
    private ArrayList<Ingredient> ingredients;
    /**
     * The description of what to do in this step
     */
    private String description;
    /**
     * The approximate total time required to complete this step
     */
    private int time; 
    /**
     * The piece of equipment required to complete this step
     */
    private String equipment;
    /**
     * The default constructor for this class
     * @param ingredients - the ingredients used in this step
     * @param description - the description of what to do in this step
     * @param time - the approximate total time required to complete this step
     * @param equipment - the piece of equipment required to complete this step
     */
    public Step(ArrayList<Ingredient> ingredients, String description, int time,String equipment) {
        this.ingredients = ingredients;
        this.description = description;
        this.time = time;
        this.equipment=equipment;
    }
    /**
     * A special constructor used to create a "blank" Step
     * @param description - the description of what to do in this step
     * @param equipment - the piece of equipment required to complete this step
     */
    public Step(String description,String equipment){
        this.description=description;
        this.equipment=equipment;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getDescripton() {
        return description;
    }

    public int getTime() {
        return time;
    }
    
    public void setDescription(String description){
        this.description=description;
    }

    public void setTime(int time){
        this.time=time;
    }

    public void addIngredient(Ingredient ingredient){
        ingredients.add(ingredient);
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
    /**
     * Scales this Step by the provided factor
     * @param factor - the factor by which this Step should be scaled
     * @return a scaled version of this Step
     */
    public Step scale(int factor) {
        Step scaledStep=new Step(description, equipment);
        for(int i=0;i<ingredients.size();++i){
            scaledStep.addIngredient(ingredients.get(i).scale(factor));
        }
        return scaledStep;
    }
    /**
     * @return a representation of this Step's details as a String
     */
    public String toString()
    {
        String stepString="Step details:\n";
        stepString+="Description: "+description+"\n";
        stepString+="Time: "+time+"\n";
        stepString+="Equipment: "+equipment+"\n";
        stepString+="Ingredients: \n";
        for(int i=0;i<ingredients.size();++i){
            stepString+=ingredients.get(i).toString()+"\n";
        }
        return stepString;
    }
    /**
     * Indicates wether another Step is essentially the same as this one. Steps are determined to be equal if all their details are identical.
     * @param comparedStep - the step with which this one is to be compared
     * @return true if they are evaluated to be equal; false otherwise
     */
    public boolean equals(Step comparedStep){
        return comparedStep.toString().equals(this.toString());
    }
}
