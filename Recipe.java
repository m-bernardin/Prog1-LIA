import java.util.ArrayList;
import java.util.HashSet;
/**
 * This is the generalized form for both SimpleRecipe and ComplexRecipe. This allows the RecipeBook to not need to distinguish between the two when interacting with any recipes it contains. 
 * This class contains all relevant getters and setters for both its child classes, as well generic forms for all of their unique methods.
 * 
 * @version 1.0
 * @author Mathieu Bernardin
 * @see SimpleRecipe
 * @see ComplexRecipe
 */
public class Recipe {
    /**
     * The name of this recipe.
     */
    protected String name;
    /**
     * How many servings this recipe makes.
     */
    protected int servings;
    /**
     * The "rating" of this recipe, based on a upvote/downvote system.
     */
    protected Integer rating;
    /**
     * A short paragraph or two to be displayed at the beginning of the recipe.
     */
    protected String introduction;
    /**
     * An ArrayList containing all Ingredients used in this recipe.
     * @see Ingredient
     */
    protected ArrayList<Ingredient> ingredients;
    /**
     * A HashSet containing all equipment used in this recipe.
     */
    protected HashSet<String> equipment;
    /**
     * The total estimated time required to follow this recipe.
     */
    protected int time;
    /**
     * A HashSet containing all Tags applied to this recipe.
     * @see Tags
     */
    protected HashSet<Tags> tags;
    /**
     * A flag for wether this recipe is done being written, used by RecipeBookManager when writing a new Recipe through the interface.
     * @see RecipeBookManager
     */
    protected boolean recipeCompleteFlag=false;
    /**
     * The default constructor for Recipe and its child classes.
     * 
     * @param name - the name of this recipe
     * @param servings - how many servings this recipe makes
     */
    public Recipe(String name, int servings) {
        this.name = name;
        this.servings = servings;
        this.rating = 0;
        tags=new HashSet<>();
        ingredients=new ArrayList<>();
        equipment=new HashSet<>(); 
    }
    /**
     * Increase the rating of this rating by one point.
     */
    public void upVote() {
        rating += 1;
    }
    /**
     * Decrease the rating of this recipe by one point.
     */
    public void downVote() {
        rating -= 1;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating){
        this.rating=rating;
    }
    /**
     * Generic form of the addStep method of SimpleRecipe. Will always return false here.
     * @param step - the step to be added to this recipe
     * @return true if the provided step was succesfully added; false otherwise
     * @see SimpleRecipe
     */
    public boolean addStep(Step step){
        return false;
    }
    /**
     * Generic form of the addRecipe method of ComplexRecipe. Will always return false here.
     * @param recipe - the subrecipe to be added to this recipe
     * @return true if the provided step was succesfully added; false otherwise
     * @see ComplexRecipe
     */
    public boolean addRecipe(Recipe recipe){
        return false;
    }
    /**
     * Generic form of the scale method specified by SimpleRecipe and ComplexRecipe. This version will always return null.
     * @param factor - the factor by which to scale this recipe
     * @return this recipe, scaled by the given factor, reperesented as a String
     * @see SimpleRecipe
     * @see ComplexRecipe
     */
    public Recipe scale(int factor){
        return null;
    }
    /**
     * Applies a specified tag to this recipe.
     * @param tag - the tag to be applied
     * @return a sucess statement to be printed by the RecipeBookManager
     * @see Tags
     * @see RecipeBookManager
     */
    public String addTag(Tags tag){
        tags.add(tag);
        return "Tag sucessfully applied.";
    }
    /**
     * Attempts to remove a specified tag from this recipe
     * @param tag - the tag to be removed
     * @return a sucess/failure statement to be printed by the RecipeBookManager
     * @see Tags
     * @see RecipeBookManager
     */
    public String removeTag(Tags tag){
        try {
            tags.remove(tag);
        } catch (Exception e) {
            return "Error removing tag: no such tag has been applied to this recipe.";
        }
        return "Tag sucessfully removed.";
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
    public String getName() {
        return name;
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
    public String formatAsSubRecipeString()
    {
        String recipeString="";
        return recipeString;
    }
    public boolean getRecipeComplete(){
        return recipeCompleteFlag;
    }
    public String toSimpleString()
    {
        return name+" for "+servings;
    }
    /**
     * Indicates wether another Recipe is essentialy the same as this one - that is to say they have the same Ingredients and identical instructions.
     * @param comparedRecipe - the recipe with which to compare
     * @return true if these Recipes contain the same Ingredients, and the same Steps; false otherwise
     */
    public boolean equals(Recipe comparedRecipe)
    {
        if(name.trim().toLowerCase().equals(comparedRecipe.getName().trim().toLowerCase())&&ingredients.equals(comparedRecipe.getIngredients())){
            return true;
        }
        return false;
    }
    /**
     * Generic form of the method of the same in each of the child classes.
     * @see SimpleRecipe
     * @see ComplexRecipe
     */
    public int calculateTime(){
        return 0;
    }
    /**
     * Generic form of the method of the same in each of the child classes.
     * @see Ingredient
     * @see SimpleRecipe
     * @see ComplexRecipe
     */    
    public ArrayList<Ingredient> calculateIngredients(){
        return new ArrayList<>();
    }
    public HashSet<String> calculateEquipment(){
        return new HashSet<>();
    }
    /**
     * Generic form of the method of the same in each of the child classes.
     * @see SimpleRecipe
     * @see ComplexRecipe
     */
    public String getIngredientsAsString(){
        return "this is the generic form of this method, it is overidden within SimpleRecipe and ComplexRecipe";
    }
    /**
     * Generic form of the method of the same in each of the child classes.
     * @see SimpleRecipe
     * @see ComplexRecipe
     */
    public String getTimeAsString(){
        return "this is the generic form of this method, it is overidden within SimpleRecipe and ComplexRecipe";
    }
    public void completeRecipe(){
        ingredients=calculateIngredients();
        time=calculateTime();
        equipment=calculateEquipment();
        recipeCompleteFlag=true;
    }
    /**
     * Generic form of the method of the same in each of the child classes.
     * @see SimpleRecipe
     * @see ComplexRecipe
     */
    public String formatAsString(){
        return "this is the generic form of this method, it is overidden within SimpleRecipe and ComplexRecipe";
    }
}