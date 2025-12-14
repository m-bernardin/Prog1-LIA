import java.util.Scanner;
import java.util.ArrayList;

public class InputManager {
    private States state;
    private Scanner reader;
    private ArrayList<RecipeBook> books;
    private RecipeBook openBook;
    private ArrayList<Recipe> results;
    private String input;
    private Recipe openRecipe;

    public InputManager(){

        reader=new Scanner(System.in);
        state=States.RECIPEBOOKS;
        books=new ArrayList<>();
        setDefaultRecipes();
    }
    public enum States{
        RECIPEBOOKS,WITHINBOOK,ADDRECIPE,SEARCH,SEARCHRESULTS,RECIPE;
    }
    public void getInput(){
        
        switch(state){
            case States.RECIPEBOOKS:
                recipeBooksPage();
                break;
            case States.ADDRECIPE:
                System.out.println("Adding Recipe");
                break;
            case States.WITHINBOOK:
                openBookPage();
                break;
            case States.SEARCH:
                searchPage();
                break;
            case States.SEARCHRESULTS:
                searchResultsPage();
                break;
            case States.RECIPE:
                recipePage();
        }

    }
    private void recipeBooksPage(){
        System.out.printf("\nPlease enter the number of the recipe book you would like to open. \n\n");
        for(int i=0;i<books.size();i++){
            System.out.println(i+".  "+books.get(i));
        }
        System.out.println(books.size()+".  "+"Create new recipe book");
        System.out.print("\n> ");
        input=reader.nextLine();
        try{
            Integer intInput=Integer.parseInt(input);
            if(intInput>books.size()&&intInput<0){
                System.out.println("Please enter a number present in the options.");
            }
            else if(intInput==books.size()){
                state=States.ADDRECIPE;
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
    private void openBookPage(){
        System.out.println("Book open: "+openBook);
        System.out.println("Enter the number for what you would like to do.");
        System.out.println("""
                0. Show all recipes
                1. Search for a recipe
                2. Go back to all recipe books
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
                state=States.RECIPEBOOKS;
            }
            else{
                System.out.println("Please enter a number present in the options.");
            }
        }
        catch(NumberFormatException e){
            System.out.println("Please enter a number.");
        }
    }
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
                            state=States.SEARCH;
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
                            state=States.SEARCH;
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
                        if(tagIndex<=tags.length&&tagIndex>=0){
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
                state=States.WITHINBOOK;
            }
            else{
                System.out.println("Please enter a number, or 'x' to go back to options.");
            }
        }

    }
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
                state=States.SEARCH;
            }
            else{
                System.out.println("Please enter a number, or 'x' to go back to search.");
            }
        }

    }
    private void recipePage(){
        System.out.println(openRecipe);
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
        recipe1.addStep(new Step(new ArrayList<>(),"Fry sandwich on both sides",7,"Frying Pan"));
        recipe1.addTag(Tags.LUNCH);
        recipe1.addTag(Tags.VEGETARIAN);
        recipe1.completeRecipe();
        
        Recipe recipe2=new ComplexRecipe("Spaghetti from Scratch", 4);
        Recipe subRecipe1=new SimpleRecipe("Spaghetti Noodles",4);
        ingredients=new ArrayList<>();
        ingredients.add(new Ingredient("Flour", 2, Units.CUP));
        ingredients.add(new Ingredient("Egg yolk", 6, Units.INDIVIDUAL));
        recipe2.addStep(new Step(ingredients, "Mix eggs yolks and flour to make pasta dough. Knead until no longer sticky.", 30, "Mixing Bowl"));
        recipe2.addStep(new Step(new ArrayList<>(),"Chill dough for four hours.",240,"Refrigerator"));
        recipe2.addStep(new Step(new ArrayList<>(),"Roll out dough and cut into thin strips to make noodles.",20,"Pasta Roller"));
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
        recipe2.addRecipe(subRecipe2);
        subRecipe2.completeRecipe();
        recipe2.addRecipe(subRecipe2);

        books.add(new RecipeBook("The Soup Bible"));

    }
}
