import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.DisplayNameGenerator.Simple;

public class SimpleRecipe extends Recipe {

    ArrayList<Step> steps = new ArrayList<>();
    
    public SimpleRecipe(String name, int servings) {
        super(name, servings);
    }
    
    public boolean addStep(Step step) {
        return true;
    }

    public int calculateTime() {
        return 0;
    }

    public HashSet<Ingredient> calculateIngrediants() {
        return null;
    }

    public SimpleRecipe scale(int factor) {
        return null;
    }
    
    public ArrayList<Step> getSteps() {
        return steps;
    }

}
