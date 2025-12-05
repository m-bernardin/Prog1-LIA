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
        simpleRecipe.addStep(new Step(new HashSet(new Ingredient[]{new Ingredient("Sliced Bread",2,Unit.INDIVIDUAL),new Ingredient("Butter",1,Unit.TABLESPOON)}),"Butter the bread",1));
        simpleRecipe.addStep(new Step(new HashSet(new Ingredient[]{new Ingredient("Cheese slice",2,Unit.INDIVIDUAL)}),"Make a sandwich with the cheese slices",1));
    }
    @AfterEach
    public void tearDown() {   
    }
    @Test
    public void testCalculateTime() {
        assertEquals(2,simpleRecipe.calculateTime());
    
    }
    @Test
    public void testCalculateIngrediants() {
        HashSet<Ingredient> expectedIngredients = new HashSet<>();
        expectedIngredients.add(new Ingredient("Sliced Bread",2,Unit.INDIVIDUAL));
        expectedIngredients.add(new Ingredient("Butter",1,Unit.TABLESPOON));
        expectedIngredients.add(new Ingredient("Cheese slice",2,Unit.INDIVIDUAL));
        assertEquals(expectedIngredients, simpleRecipe.calculateIngrediants());
    
    }
    
    @Test
    public void testScale() {
        Recipe expectedRecipe = new SimpleRecipe("Grilled Cheese",2);
        expectedRecipe.addStep(new Step(new HashSet(new Ingredient[]{new Ingredient("Sliced Bread",4,Unit.INDIVIDUAL),new Ingredient("Butter",2,Unit.TABLESPOON)}),"Butter the bread",1));
        expectedRecipe.addStep(new Step(new HashSet(new Ingredient[]{new Ingredient("Cheese slice",4,Unit.INDIVIDUAL)}),"Make a sandwich with the cheese slices",1));
        assertEquals(expectedRecipe, simpleRecipe.scale(2));

        //scale will now return a new SimpleRecipe object
    }
    @Test
    public void testAddStep() {
        simpleRecipe.addStep(new Step(new HashSet(),"Fry sandwich on both sides",7));
        assertEquals(3,simpleRecipe.getSteps().size());
    }
    
    

}
