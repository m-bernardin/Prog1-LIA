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
    protected ArrayList<Ingredient> ingredients;
    protected HashSet<String> equipment;
    protected int time;
    protected HashSet<Tags> tags;
    protected boolean recipeCompleteFlag=false;
    
    public Recipe(String name, int servings) {
        this.name = name;
        this.servings = servings;
        this.rating = 0;
        tags=new HashSet<>();
        ingredients=new ArrayList<>();
        equipment=new HashSet<>(); 
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

    public boolean addStep(Step step){
        return false;
    }

    public boolean addRecipe(Recipe recipe){
        return false;
    }

    public Recipe scale(int factor){
        return null;
    }
    public void addTag(Tags tag){
        tags.add(tag);
    }

    public String getName() {
        return name;
    }
    public HashSet<Tags> getTags() {
        return tags;
    }

    public ArrayList<Ingredient> getIngredients(){
        return ingredients;
    }

    public HashSet<String> getEquipment(){
        return equipment;
    }

    public int getTime(){
        return time;
    }

    public void setTime(int time){
        this.time=time;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getIntroduction()
    {
        return introduction;
    }

    public void setIntroduction(String introduction){
        this.introduction=introduction;
    }

    public int getServings()
    {
        return servings;
    }

    public void setServings(int servings){
        this.servings=servings;
    }

    public boolean getRecipeComplete(){
        return recipeCompleteFlag;
    }

    public void completeRecipe(){
        
    }

    public String toString()
    {
        return name+" for "+servings;
    }

    /**
     * Indicates wether another Recipe is essentialy the same as this one - that is to say they have the same Ingredients and identical instructions.
     * 
     * This is the generic version of this method, and so it will always return false.
     * @param comparedRecipe - the recipe with which to compare
     * @return true if these Recipes contain the same Ingredients, and the same Steps; false otherwise
     */
    public boolean equals(Recipe comparedRecipe)
    {
        if(name.equals(comparedRecipe.getName())&&ingredients.equals(comparedRecipe.getIngredients())){
            return true;
        }
        return false;
    }

    public int calculateTime(){
        return 0;
    }
    public ArrayList<Ingredient> calculateIngredients(){
        return new ArrayList<>();
    }
    public String getIngredientsAsString(){
        return "this is the generic form of this method, it is overidden within SimpleRecipe and ComplexRecipe";
    }

    public String getTimeAsString(){
        return "this is the generic form of this method, it is overidden within SimpleRecipe and ComplexRecipe";
    }

}