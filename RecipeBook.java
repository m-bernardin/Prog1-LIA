import java.util.ArrayList;

public class RecipeBook {

    private String title;
    private ArrayList<Recipe> recipes;

    public RecipeBook(String title) {
        this.title = title;
        recipes=new ArrayList<>();
        
    }
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);

    }

    public void upvoteRecipe(int recipeIndex) {
        recipes.get(recipeIndex).upVote();

    }

    public void downvoteRecipe(int recipeIndex) {
        recipes.get(recipeIndex).downVote();
    }
    public ArrayList<Recipe> searchName(String name){
        ArrayList<Recipe> results = new ArrayList<>();
        for(Recipe recipe : recipes){
            if(recipe.getName().equals(name)){
                results.add(recipe);
            }
        }
        return results;
    }

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

    public ArrayList<Recipe> searchTime(int time){
        ArrayList<Recipe> results = new ArrayList<>();
        for(Recipe recipe : recipes){
            if(recipe.getTime()<=time){
                results.add(recipe);
            }
        }
        return results;
    }
    public ArrayList<Recipe> searchTag(Tags tag){
        ArrayList<Recipe> results = new ArrayList<>();
        for(Recipe recipe : recipes){
            if(recipe.getTags().contains(tag)){
                results.add(recipe);
            }
        }
        return results;
    }

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

