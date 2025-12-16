import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;

public class SimpleRecipeTest {
    SimpleRecipe simpleRecipe;
    public SimpleRecipeTest() {
    }
    @BeforeEach
    public void setUp() {
        simpleRecipe = new SimpleRecipe("Grilled Cheese",1);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Sliced Bread",2,Units.INDIVIDUAL));
        ingredients.add(new Ingredient("Butter",1,Units.TABLESPOON));
        simpleRecipe.addStep(new Step(ingredients,"Butter the bread",1,""));
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Cheese slice",2,Units.INDIVIDUAL));
        simpleRecipe.addStep(new Step(ingredients,"Make a sandwich with the cheese slices",1,""));
    }
    @AfterEach
    public void tearDown() {   
    }
    @Test
    public void testCalculateTime() {
        assertEquals(2,simpleRecipe.calculateTime());
    
    }
    @Test
    public void testCalculateIngredients() {
        ArrayList<Ingredient> expectedIngredients = new ArrayList<>();
        expectedIngredients.add(new Ingredient("Sliced Bread",2,Units.INDIVIDUAL));
        expectedIngredients.add(new Ingredient("Butter",1,Units.TABLESPOON));
        expectedIngredients.add(new Ingredient("Cheese slice",2,Units.INDIVIDUAL));
        System.out.println(expectedIngredients.get(0).equals(simpleRecipe.calculateIngredients().get(0)));
        assertTrue(expectedIngredients.get(2).equals(simpleRecipe.calculateIngredients().get(2)));
    
    }
    
    @Test
    public void testScale() {
        SimpleRecipe expectedRecipe = new SimpleRecipe("Grilled Cheese",2);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Sliced Bread",4,Units.INDIVIDUAL));
        ingredients.add(new Ingredient("Butter",2,Units.TABLESPOON));
        expectedRecipe.addStep(new Step(ingredients,"Butter the bread",1,""));
        ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Cheese slice",4,Units.INDIVIDUAL));
        expectedRecipe.addStep(new Step(ingredients,"Make a sandwich with the cheese slices",1,""));
        System.out.println();
        assertTrue(expectedRecipe.equals(simpleRecipe.scale(2)));

        //scale will now return a new SimpleRecipe object
    }
    @Test
    public void testAddStep() {
        simpleRecipe.addStep(new Step(new ArrayList<>(),"Fry sandwich on both sides",7,""));
        assertEquals(3,simpleRecipe.getSteps().size());
    }
    @Test
    public void testAddRecipe(){
        assertFalse(simpleRecipe.addRecipe(new Recipe("Bolognese Sauce",8)));
    }
    @Test
    public void testIngredientQtyBoundry(){
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Butter",-1,Units.TABLESPOON));
        assertFalse(simpleRecipe.addStep(new Step(ingredients,"Fry sandwich on both sides",7,"Stove")));
    }
    

}
