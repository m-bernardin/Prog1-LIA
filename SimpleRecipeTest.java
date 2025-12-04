import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class SimpleRecipeTest {
    SimpleRecipe simpleRecipe;
    public SimpleRecipeTest() {
    }
    @BeforeEach
    public void setUp() {
        simpleRecipe = new SimpleRecipe("Grilled Cheese",1);
        simpleRecipe.addStep(new Recipe(new HashSet(new Ingredient[]{new Ingredient("Sliced Bread",2,Unit.),})"Butter the bread"));
    }
    @AfterEach
    public void tearDown() {   
    }
    @Test
    public void testCalculateTime() {
    
    }
    @Test
    public void testCalculateIngrediants() {
    
    }
    @Test
    public void testGetIngrediantsAsString() {
    
    }
    @Test
    public void testGetTimesAsString() {
    
    }
    
    @Test
    public void testFormatRecipeAsString() {
    
    }
    @Test
    public void testScale() {
    
    }
    @Test
    public void testAddStep() {
    
    }
    

}
