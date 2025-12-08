import java.util.ArrayList;

public class ComplexRecipe extends Recipe{
    
    ArrayList<SimpleRecipe> Subrecipes = new ArrayList<>();
    
    public ComplexRecipe(String name, int servings) {
        super(name, servings);
    }
}