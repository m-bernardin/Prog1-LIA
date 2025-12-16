import java.util.Scanner;
import java.util.ArrayList;
/**
 * This class serves as the primary method with which the user interacts with RecipeBooks. 
 * It contains methods to respond to the user's input based on an internally stored state, essentially 'where' the user is.
 */
public class InputManager {
    /**
     * The state of the input manager determines what page the user is on.
     */
    private States state;
    /**
     * Scanner to read user input.
     */
    private Scanner reader;
    /**
     * A collection of recipe books.
     */
    private ArrayList<RecipeBook> books;
    /**
     * The recipe book the user is currently exploring.
     */
    private RecipeBook openBook;
    /**
     * The results of the most recent search.
     */
    private ArrayList<Recipe> results;
    /**
     * The last input read in by the scanner.
     */
    private String input;
    /**
     * The recipe the user is currently viewing.
     */
    private Recipe openRecipe;
    /**
     * Indicates if the program is currently running.
     */
    private boolean running=true;

    private States lastVisited;
    /**
     * The default constructor for this class. Initializes certain non-primitive fields and adds several demonstrative RecipeBooks.
     */
    public InputManager(){
        reader=new Scanner(System.in);
        state=States.RECIPEBOOKS;
        books=new ArrayList<>();
        setDefaultRecipes();
    }
    /**
     * Enumeration of all possible pages the user can be on.
     */
    public enum States{
        RECIPEBOOKS,WITHINBOOK,ADDRECIPE,ADDRECIPEBOOK,SEARCH,SEARCHRESULTS,RECIPE;
    }
    /**
     * Chooses how to get and handle user input based on the current state.
     */
    public void getInput(){
        
        switch(state){
            case States.RECIPEBOOKS:
                recipeBooksPage();
                lastVisited=States.RECIPEBOOKS;
                break;
            case States.ADDRECIPE:
                addRecipePage();
                lastVisited=States.WITHINBOOK;
                break;
            case States.ADDRECIPEBOOK:
                addRecipeBookPage();
                lastVisited=States.RECIPEBOOKS;
                break;
            case States.WITHINBOOK:
                openBookPage();
                lastVisited=States.RECIPEBOOKS;
                break;
            case States.SEARCH:
                searchPage();
                lastVisited=States.WITHINBOOK;
                break;
            case States.SEARCHRESULTS:
                searchResultsPage();
                lastVisited=States.SEARCH;
                break;
            case States.RECIPE:
                recipePage();
                lastVisited=States.SEARCHRESULTS;
        }
    }
    /**
     * Allows the user to choose a recipe book to open, or create a new one.
     */
    private void recipeBooksPage(){
        System.out.printf("\nPlease enter the number of the recipe book you would like to open. \n\n");
        for(int i=0;i<books.size();i++){
            System.out.println(i+".  "+books.get(i));
        }
        System.out.println(books.size()+".  "+"Create new recipe book");
        System.out.println((books.size()+1)+".  Quit");
        System.out.print("\n> ");
        input=reader.nextLine();
        try{
            Integer intInput=Integer.parseInt(input);
            if(intInput==books.size()+1){
                System.out.println("Quitting...\nGoodbye!");
                running=false;
            }
            else if(intInput>books.size()&&intInput<0){
                System.out.println("Please enter a number present in the options.");
            }
            else if(intInput==books.size()){
                state=States.ADDRECIPEBOOK;
            }
            else{
                openBook=books.get(intInput);
                state=States.WITHINBOOK;
            }
        }
        catch(NumberFormatException e){
            System.out.println("Please enter a number.");
        }
    }
    /**
     * Presents options of how the user can navigate the current open recipe book, including searching, viewing all recipes or creating a new recipe.
     */
    private void openBookPage(){
        System.out.println("Book open: "+openBook);
        System.out.println("Enter the number for what you would like to do, or enter 'x' to go back.");
        System.out.println("""
                0. Show all recipes
                1. Search for a recipe
                2. Add a new recipe
                """);
        System.out.print("\n> ");
        input=reader.nextLine();
        
        try{
            Integer intInput=Integer.parseInt(input);
            if(intInput==0){
                results=openBook.getRecipes();
                state=States.SEARCHRESULTS;
            }
            else if(intInput==1){
                state=States.SEARCH;
            }
            else if(intInput==2){
                state=States.ADDRECIPE;
            }
            else{
                System.out.println("Please enter a number present in the options.");
            }
        }
        catch(NumberFormatException e){
            if (input.equals("x")){
                state=lastVisited;
            }
            else{
                System.out.println("Please enter a number, or 'x' to go back.");
            }
        }
    }
    /**
     * Allows the user to choose a caracteristic to search by within a recipe book, collects a parameter for the search and stores the result.
     */
    private void searchPage(){
        System.out.println("Please enter the number for what you would like to search by, or enter 'x' to go back to options.");
        System.out.println("""
                0. Name
                1. Ingredient
                2. Tag
                4. Maximum time
                3. Top Rated
                """);
        System.out.print("\n> ");
        input=reader.nextLine();
        try{
            Integer intInput=Integer.parseInt(input);
            if(intInput==0){
                System.out.println("Please enter the name of the recipe you are looking for.");
                System.out.print("\n> ");
                input=reader.nextLine();
                results=openBook.searchName(input);
                if(results.size()>0){
                    state=States.SEARCHRESULTS;
                }
                else{
                    while(results.size()==0){
                        System.out.println("This recipe doesn't seem to exist. Make sure it is spelled correctly, or type 'x' to search by something else.");
                        System.out.print("\n> ");
                        input=reader.nextLine();
                        if(input.equals("x")){
                            state=lastVisited;
                            break;
                        }
                        else{
                            results=openBook.searchName(input);
                            if (results.size()>0){
                                state=States.SEARCHRESULTS;
                            }
                        }
                    }
                }
            }
            else if(intInput==1){
                System.out.println("Please enter the ingredient you want to search by.");
                System.out.print("\n> ");
                input=reader.nextLine();
                results=openBook.searchIngredient(input);
                if(results.size()>0){
                    state=States.SEARCHRESULTS;
                }
                else{
                    while(results.size()==0){
                        System.out.println("Recipes with this ingredeient don't seem to exist. Make sure it is spelled correctly, or type 'x' to search by something else.");
                        System.out.print("\n> ");
                        input=reader.nextLine();
                        if(input.equals("x")){
                            state=lastVisited;
                            break;
                        }
                        else{
                            results=openBook.searchIngredient(input);
                            if (results.size()>0){
                                state=States.SEARCHRESULTS;
                            }
                        }
                    }
                }
            }
            else if(intInput==2){
                Tags[] tags=Tags.values();
                boolean found=false;
                System.out.println("Please enter the number for the tag you want to search by.");
                for(int i=0;i<tags.length;i++){
                    System.out.println(i+". "+tags[i].name());
                }
                System.out.print("\n> ");
                input=reader.nextLine();
                try{
                    Integer tagIndex=Integer.parseInt(input);
                    if(tagIndex<=tags.length&&tagIndex>=0){
                        results=openBook.searchTag(tags[tagIndex]);
                        if (results.size()>0){
                            found=true;
                            state=States.SEARCHRESULTS;
                        }
                        else{
                            System.out.println("Recipes with this tag don't seem to exist. Try searching with another or enter 'x' to search by something else.");
                        }
                    }
                    else{
                        System.out.println("Please enter a number present in the options, or enter 'x' to search by something else.");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Please enter a number, or enter 'x' to search by something else.");
                }
                while(!found){
                    System.out.print("\n> ");
                    input=reader.nextLine();
                    if(input.equals("x")){
                        state=States.SEARCH;
                    }
                    try{
                        Integer tagIndex=Integer.parseInt(input);
                        if(tagIndex<tags.length&&tagIndex>=0){
                            results=openBook.searchTag(tags[tagIndex]);
                            if (results.size()>0){
                                found=true;
                                state=States.SEARCHRESULTS;
                            }
                            else{
                                System.out.println("Recipes with this tag don't seem to exist in this book. Try searching with another or enter 'x' to search by something else.");
                            }
                        }
                        else{
                            System.out.println("Please enter a number present in the options, or enter 'x' to search by something else.");
                        }
                    }
                    catch(NumberFormatException e){
                        if(input.equals("x")){
                            state=lastVisited;
                        }
                        System.out.println("Please enter a number, or enter 'x' to search by something else.");
                    }
                }
            }
            else if(intInput==3){
                results=openBook.getTopRated();
                state=States.SEARCHRESULTS;
            }
            else{
                System.out.println("Please enter a number present in the options.");
            }
        }
        catch(NumberFormatException e){
            if (input.equals("x")){
                state=lastVisited;
            }
            else{
                System.out.println("Please enter a number, or 'x' to go back to options.");
            }
        }
    }
    /**
     * Displays the results of the most recent search and allows the user to choose one to view in full.
     */
    private void searchResultsPage(){
        System.out.println("Enter the number of a search result to see the full recipe, or enter 'x' to go back to search.\n");
        System.out.println("Search results:");
        for(int i=0; i<results.size();i++){
            System.out.println(i+". "+results.get(i).toSimpleString());
        }
        System.out.print("\n> ");
        input=reader.nextLine();
        try{
            Integer intInput=Integer.parseInt(input);
            if(intInput>=0&&intInput<results.size()){
                openRecipe=results.get(intInput);
                state=States.RECIPE;
            }
            else{
                System.out.println("Please enter a number present in the options.");
            }
        }
        catch(NumberFormatException e){
            if(input.equals("x")){
                state=lastVisited;
            }
            else{
                System.out.println("Please enter a number, or 'x' to go back to search.");
            }
        }
    }
    /**
     * Displays the full recipe and allows the user to upvote or downvote the recipe.
     */
    private void recipePage(){
        System.out.println(openRecipe.formatAsString());
        System.out.println("Enter '1' to upvote this recipe, '2' to downvote it, or 'x' to go back to search results.");
        System.out.print("\n> ");
        input=reader.nextLine();
        if(input.equals("1")){
            openRecipe.upVote();
        }
        else if(input.equals("2")){
            openRecipe.downVote();
        }
        else if(input.equals("x")){
            state=States.SEARCHRESULTS;
        }
        else{
            System.out.println("Please enter something that appears in the options.");
        }
    }
    /**
     * Allows the user to add a new recipe by collecting the necessary information and storing the new recipe.
     */
    private void addRecipePage(){
        int recipeType=-1;
        String dishName="";
        int servings=0;
        boolean moveOn=false;
        System.out.println("""
            Please enter the number for the type of recipe you would like to add, or enter 'x' to go back.
            0. Simple Recipe: A coherent recipe with one part. Ex: Muffins
            1. Complex Recipe: A recipe with multiple isolated parts. Ex: Birthday cake with icing
            """);
        System.out.print("\n> ");
        input=reader.nextLine();
        try{
            int intInput=Integer.parseInt(input);
            if(intInput==0||intInput==1){
                recipeType=intInput;
                moveOn=true;
            }
            else{
                System.out.println("Please enter a number present in the options.");
            }
        }catch(NumberFormatException e){
            if(input.equals("x")){
                state=lastVisited;
            }
            else{
                System.out.println("Please enter a number, or 'x' to go back.");
            }
        }
        if(moveOn){
            System.out.println("Please enter the name of this recipe.");
            System.out.print("\n> ");
            dishName=reader.nextLine();
            moveOn=false;
            System.out.println("Please enter how many servings this recipe will make.");
            System.out.print("\n> ");
            input=reader.nextLine();
            while(!moveOn){
                try{
                    servings=Integer.parseInt(input);
                    moveOn=true;
                }
                catch(NumberFormatException e){
                    if(input.equals("x")){
                        state=lastVisited;
                        break;
                    }
                    System.out.println("Please enter a number, or enter 'x' to go back");
                    System.out.print("\n> ");
                    input=reader.nextLine();
                }
            }
            if (moveOn){
                if(recipeType==0){
                    SimpleRecipe newRecipe=new SimpleRecipe(dishName, servings);
                    boolean addingSteps=true;
                    System.out.println("Now add steps to your recipe.");
                    while(addingSteps){
                        newRecipe.addStep(buildStep());
                        System.out.println("Enter '0' to add another step, or enter '1' to move on.");
                        System.out.print("\n> ");
                        input=reader.nextLine();
                        moveOn=false;
                        while(!moveOn){
                            if(input.equals("0")){
                                moveOn=true;
                            }
                            else if(input.equals("1")){
                                moveOn=true;
                                addingSteps=false;
                            }
                            else{
                                System.out.println("Please enter a number present in the options.");
                                System.out.print("\n> ");
                                input=reader.nextLine();
                            }
                        }
                    }
                    if(moveOn){
                        newRecipe.completeRecipe();
                        openRecipe=newRecipe;
                        state=States.RECIPE;
                    }
                }
                else{
                    ComplexRecipe newRecipe=new ComplexRecipe(dishName, servings);
                    boolean addingRecipes=true;
                    while(addingRecipes){
                        System.out.println("Add a subrecipe to your complex recipe.");
                        System.out.println("Enter the name of this subrecipe.");
                        System.out.print("\n> ");
                        input=reader.nextLine();
                        String name=input;
                        SimpleRecipe subRecipe=new SimpleRecipe(name,servings);
                        boolean addingSteps=true;
                        System.out.println("Now add steps to your subrecipe.");
                        while(addingSteps){
                            subRecipe.addStep(buildStep());
                            System.out.println("Enter '0' to add another step, or enter '1' to move on.");
                            System.out.print("\n> ");
                            input=reader.nextLine();
                            moveOn=false;
                            while(!moveOn){
                                if(input.equals("0")){
                                    moveOn=true;
                                }
                                else if(input.equals("1")){
                                    moveOn=true;
                                    addingSteps=false;
                                }
                                else{
                                    System.out.println("Please enter a number present in the options.");
                                    System.out.print("\n> ");
                                    input=reader.nextLine();
                                }
                            }
                        }
                        subRecipe.completeRecipe();
                        newRecipe.addRecipe(subRecipe);
                        System.out.println("Enter '0' to add another subrecipe, or enter '1' to move on");
                        System.out.print("\n> ");
                        input=reader.nextLine();
                        moveOn=false;
                        while(!moveOn){
                            if(input.equals("0")){
                                moveOn=true;
                            }
                            else if(input.equals("1")){
                                moveOn=true;
                                addingRecipes=false;
                            }
                            else{
                                System.out.println("Please enter a number present in the options.");
                                System.out.print("\n> ");
                                input=reader.nextLine();
                            }
                        }
                    }
                    newRecipe.completeRecipe();
                    openRecipe=newRecipe;
                    
                    
                }
            }
        }
        if(moveOn){
            System.out.println("Please pick a tag to  add to this recipe.");
            Tags[] tags = Tags.values();
            boolean addingTags=true;
            for(int i=0;i<tags.length;i++){
                System.out.println(i+". "+tags[i].name());
            }
            System.out.println(tags.length+". "+"Don't add any more tags.");
            System.out.print("\n> ");
            input=reader.nextLine();
            while(addingTags){
                try{
                    Integer tagIndex=Integer.parseInt(input);
                    if(tagIndex<tags.length&&tagIndex>=0){
                        openRecipe.addTag(tags[tagIndex]);
                    }
                    else if(tagIndex==tags.length){
                        addingTags=false;
                        break;
                    }
                    else{
                        System.out.println("Please enter a number present in the options.");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Please enter a number.");
                }
                for(int i=0;i<tags.length;i++){
                System.out.println(i+". "+tags[i].name());
                }
                System.out.println(tags.length+". "+"Don't add any more tags.");
                System.out.print("\n> ");
                input=reader.nextLine();
            }
                state=States.RECIPE;
        }
    }
    /**
     * Allows the user to create a new recipe by responding to prompts
     */
    private void addRecipeBookPage(){
        System.out.println("Please enter the name of the new recipe book.");
        System.out.print("\n> ");
        input=reader.nextLine();
        books.add(new RecipeBook(input));
        System.out.println("Recipe book '"+input+"' added.");
        state=States.RECIPEBOOKS;
    }
    /**
     * Handles the step creation that appears in the addRecipePage method
     * @return the step that it builds
     */
    private Step buildStep(){
        boolean moveOn;
        ArrayList<Ingredient> ingredients=new ArrayList<>();
        Units unit=Units.CUP;
        String ingredientName="";
        double qty=0.0;
        String equipment;
        String description;
        int time=0;
        Units[] units=Units.values();
        System.out.println("What ingredients does this step need?");
        boolean addingIngredients=true;
        while(addingIngredients){
            System.out.println("Please enter the ingredient name.");
            System.out.print("\n> ");
            input=reader.nextLine();
            ingredientName=input;
            System.out.println("Please enter the number for he unit this ingredient will be mesured in.");
            for(int i=0;i<units.length;i++){
                System.out.println(i+". "+units[i]);
            }
            moveOn=false;
            System.out.print("\n> ");
            input=reader.nextLine();
            while(!moveOn){
                try{
                    int intInput=Integer.parseInt(input);
                    if(intInput>=0&&intInput<units.length){
                        unit=units[intInput];
                        moveOn=true;
                        break;
                    }
                    else{
                        System.out.println("Please enter a number that appears in the options.");
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Please enter a number.");
                }
                System.out.print("\n> ");
                input=reader.nextLine();
            }
            System.out.println("Please enter the quantity of this ingredient. Only enter a number, the unit is already saved.");
            System.out.print("\n> ");
            input=reader.nextLine();
            moveOn=false;
            while(!moveOn){
                try{
                    double doubleInput=Double.parseDouble(input);
                    qty=doubleInput;
                    moveOn=true;
                    break;
                }
                catch(NumberFormatException e){
                    System.out.println("Please enter a number.");
                }
                System.out.print("\n> ");
                input=reader.nextLine();
            }
            ingredients.add(new Ingredient(ingredientName,qty,unit));
            System.out.println("Enter '0' to add another ingredient, or enter '1' to move on.");
            System.out.print("\n> ");
            input=reader.nextLine();
            moveOn=false;
            while(!moveOn){
                if(input.equals("0")){
                    moveOn=true;
                }
                else if(input.equals("1")){
                    addingIngredients=false;
                    moveOn=true;
                }
                else{
                    System.out.println("Please enter a number that appears in the options.");
                    System.out.print("\n> ");
                    input=reader.nextLine();
                }
            }
        }
        System.out.println("What piece of equipment is needed for this step?");
        System.out.print("\n> ");
        input=reader.nextLine();
        equipment=input;
        System.out.println("What does this step entail? Please enter a description");
        System.out.print("\n> ");
        input=reader.nextLine();
        description=input;
        System.out.println("How long do you estimate this step will take, in minutes?");
        System.out.print("\n> ");
        input=reader.nextLine();
        moveOn=false;
        while(!moveOn){
            try{
                int intInput=Integer.parseInt(input);
                time=intInput;
                moveOn=true;
            }
            catch(NumberFormatException e){
                System.out.println("Please enter a number.");
                System.out.print("\n> ");
                input=reader.nextLine();
            }
        }
        return new Step(ingredients,description,time,equipment);
    }
    /**
     * Sets default recipes at the start of the program.
     */
    private void setDefaultRecipes(){
        books.add(new RecipeBook("Mom's Recipes"));
        Recipe recipe1= new SimpleRecipe("Grilled Cheese Sandwich",1);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Sliced Bread",2,Units.INDIVIDUAL));
        ingredients.add(new Ingredient("Butter",1,Units.TABLESPOON));
        recipe1.addStep(new Step(ingredients,"Butter the bread",1,"Knife"));
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Cheese slice",2,Units.INDIVIDUAL));
        recipe1.addStep(new Step(ingredients,"Make a sandwich with the cheese slices",1,""));
        recipe1.addStep(new Step(new ArrayList<>(),"Fry sandwich on both =sides",7,"Frying Pan"));
        recipe1.addTag(Tags.LUNCH);
        recipe1.addTag(Tags.VEGETARIAN);
        recipe1.completeRecipe();
        Recipe recipe2=new ComplexRecipe("Spaghetti from Scratch", 4);
        Recipe subRecipe1=new SimpleRecipe("Spaghetti Noodles",4);
        ingredients=new ArrayList<>();
        ingredients.add(new Ingredient("Flour", 2, Units.CUP));
        ingredients.add(new Ingredient("Egg yolk", 6, Units.INDIVIDUAL));
        subRecipe1.addStep(new Step(ingredients, "Mix eggs yolks and flour to make pasta dough. Knead until no longer sticky.", 30, "Mixing Bowl"));
        subRecipe1.addStep(new Step(new ArrayList<>(),"Chill dough for four hours.",240,"Refrigerator"));
        subRecipe1.addStep(new Step(new ArrayList<>(),"Roll out dough and cut into thin strips to make noodles.",20,"Pasta Roller"));
        subRecipe1.addStep(new Step(new ArrayList<>(),"Boil noodles in salted water until al dente.",10,"Stove"));
        subRecipe1.completeRecipe();
        recipe2.addRecipe(subRecipe1);
        Recipe subRecipe2=new SimpleRecipe("Tomato Sauce",4);
        ingredients=new ArrayList<>();
        ingredients.add(new Ingredient("Olive Oil",2,Units.TABLESPOON));
        ingredients.add(new Ingredient("Onion",1,Units.INDIVIDUAL));
        subRecipe2.addStep(new Step(ingredients,"Sauté onions in olive oil until translucent.",10,"Stove"));
        ingredients=new ArrayList<>();
        ingredients.add(new Ingredient("Can of tomatoes",2,Units.INDIVIDUAL));
        subRecipe2.addStep(new Step(ingredients,"Add tomatoes, simmer and break down until it forms a sauce.",30,"Stove"));
        subRecipe2.completeRecipe();
        recipe2.addRecipe(subRecipe2);
        recipe2.completeRecipe();
        books.get(0).addRecipe(recipe1);
        books.get(0).addRecipe(recipe2);
        books.add(new RecipeBook("The Soup Bible"));
        ingredients=new ArrayList<>();
        ingredients.add(new Ingredient("Egg", 2, Units.INDIVIDUAL));
        Recipe recipe3=new SimpleRecipe("Pancakes",4);
        recipe3.addStep(new Step(ingredients, "Beat eggs", 3, "whisk"));
        Recipe recipe4=new Recipe("Waffles", 4);
        Recipe recipe5=new Recipe("Pasta", 2);
        Recipe recipe6=new SimpleRecipe("Omelette",2);
        recipe6.addStep(new Step(ingredients, "Beat eggs", 3, "whisk"));
        recipe3.addTag(Tags.BREAKFAST);
        recipe4.addTag(Tags.BREAKFAST);
        recipe6.addTag(Tags.BREAKFAST);
        recipe3.completeRecipe();
        recipe4.completeRecipe();
        recipe5.completeRecipe();
        recipe6.completeRecipe();
        books.get(0).addRecipe(recipe3);
        books.get(0).addRecipe(recipe4);
        books.get(0).addRecipe(recipe5);
        books.get(0).addRecipe(recipe6);
    }
    public boolean getRunning(){
        return running;
    }
}