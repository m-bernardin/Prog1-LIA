import java.util.ArrayList;
/**
 * A representation of a single recipe book, containing many Recipes. Allows for searching through each stored Recipe based on name, ingredients, time, or tags. 
 * @author Madeleine Siwick
 */
public class RecipeBook {
    /**
     * The title of this RecipeBook
     */
    private String title;
    /**
     * A list of all recipes contained in  this book
     */
    private ArrayList<Recipe> recipes;
    /**
     * The default constructor for this class. 
     * Creates a new recipe book containing no recipes.
     * @param title - the title of this RecipeBook
     */
    public RecipeBook(String title) {
        this.title = title;
        recipes=new ArrayList<>();
    }
    /**
     * Adds a single recipe to this book.
     * @param recipe - the recipe to be added 
     * @return A sucess/failure message to be displayed
     */
    public String addRecipe(Recipe recipe) {
        if(recipe.recipeCompleteFlag){
            recipes.add(recipe);
            return "Recipe sucessfully added!";
        }
        else{
            return "Please complete this recipe before adding it!";
        }
    }
    /**
     * Upvotes a single Recipe
     * @param recipeIndex - the index of the Recipe to be upvoted
     */
    public void upvoteRecipe(int recipeIndex) {
        recipes.get(recipeIndex).upVote();
    }
    /**
     * Downvotes a single Recipe
     * @param recipeIndex - the index of the Recipe to be downvoted
     */
    public void downvoteRecipe(int recipeIndex) {
        recipes.get(recipeIndex).downVote();
    }
    /**
     * Serches this RecipeBook for Recipes who's name contains a search term.
     * @param name - the name to be searched for 
     * @return An ArrayList containing the Recipes found
     */
    public ArrayList<Recipe> searchName(String name){
        ArrayList<Recipe> results = new ArrayList<>();
        for(Recipe recipe : recipes){
            if(recipe.getName().equals(name)){
                results.add(recipe);
            }
        }
        return results;
    }
    /**
     * Serches this RecipeBook for Recipes who's Ingredients contains a search term.
     * @param Ingredient - the Ingredient to be searched for 
     * @return An ArrayList containing the Recipes found
     */
    public ArrayList<Recipe> searchIngredient(String ingredient){
        ArrayList<Recipe> results = new ArrayList<>();
        for(Recipe recipe : recipes){
            for(Ingredient presentIngredient:recipe.getIngredients())
                if(presentIngredient.getName().equals(ingredient)){
                    results.add(recipe);
                    break;
                }
        }
        return results;
    }
    /**
     * Searches this RecipeBook for Recipes who's time is no more than a certain time.
     * @param time - the max time
     * @return An ArrayList containing the Recipes found
     */
    public ArrayList<Recipe> searchTime(int time){
        ArrayList<Recipe> results = new ArrayList<>();
        for(Recipe recipe : recipes){
            if(recipe.getTime()<=time){
                results.add(recipe);
            }
        }
        return results;
    }
    /**
     * Serches this RecipeBook for Recipes who's Tags contains a search term.
     * @param tag - the Tag to be searched for 
     * @return An ArrayList containing the Recipes found
     */
    public ArrayList<Recipe> searchTag(Tags tag){
        ArrayList<Recipe> results = new ArrayList<>();
        for(Recipe recipe : recipes){
            if(recipe.getTags().contains(tag)){
                results.add(recipe);
            }
        }
        return results;
    }
    /**
     * Finds the Recipe which currently has the highest rating.
     * @return The found Recipe
     */
    public ArrayList<Recipe> getTopRated() {
        Recipe highestRated=recipes.get(0);
        ArrayList<Recipe> results=new ArrayList<>();
        for(Recipe recipe:recipes){
            if(recipe.getRating()>highestRated.getRating()){
                highestRated=recipe;
            }
        }
        results.add(highestRated);
        return results;
    }
    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
    public String toString(){
        return title;
    }
}

