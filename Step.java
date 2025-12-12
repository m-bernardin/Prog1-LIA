import java.util.ArrayList;

/**
 * @author Mathieu Bernardin
 */
public class Step {
    private ArrayList<Ingredient> ingredients;
    private String description;
    private int time; 
    private String equipment;

    public Step(ArrayList<Ingredient> ingredients, String description, int time,String equipment) {
        this.ingredients = ingredients;
        this.description = description;
        this.time = time;
        this.equipment=equipment;
    }

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

    public Step scale(int factor) {
        Step scaledStep=new Step(description, equipment);
        for(int i=0;i<ingredients.size();++i){
            scaledStep.addIngredient(ingredients.get(i).scale(factor));
        }
        return scaledStep;
    }

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

    public boolean equals(Step comparedStep){
        return comparedStep.toString().equals(this.toString());
    }
}
