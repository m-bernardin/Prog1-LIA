import java.util.ArrayList;

public class ComplexRecipe extends Recipe{
    
    ArrayList<SimpleRecipe> Subrecipes = new ArrayList<>();
    
    public ComplexRecipe(String name, int servings) {
        super(name, servings);
    }

    public void addRecipe(SimpleRecipe subrecipe1) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addRecipe'");
    }

    public int getTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTime'");
    }

    public void calculateTime() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateTime'");
    }

    public ArrayList<SimpleRecipe> getRecipes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecipes'");
    }

    public String scale(double factor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scale'");
    }
}