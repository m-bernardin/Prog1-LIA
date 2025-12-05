import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayNameGenerator.Simple;
import java.util.ArrayList;

import java.util.HashSet;
public class SimpleRecipeTest {
    SimpleRecipe simpleRecipe;
    public SimpleRecipeTest() {
    }
    @BeforeEach
    public void setUp() {
        simpleRecipe = new SimpleRecipe("Grilled Cheese",1);
        HashSet<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Sliced Bread",2,Units.INDIVIDUAL));
        ingredients.add(new Ingredient("Butter",1,Units.TABLESPOON));
        simpleRecipe.addStep(new Step(ingredients,"Butter the bread",1));
        ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Cheese slice",2,Units.INDIVIDUAL));
        simpleRecipe.addStep(new Step(ingredients,"Make a sandwich with the cheese slices",1));
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
        expectedIngredients.add(new Ingredient("Sliced Bread",2,Units.INDIVIDUAL));
        expectedIngredients.add(new Ingredient("Butter",1,Units.TABLESPOON));
        expectedIngredients.add(new Ingredient("Cheese slice",2,Units.INDIVIDUAL));
        assertEquals(expectedIngredients, simpleRecipe.calculateIngrediants());
    
    }
    
    @Test
    public void testScale() {
        SimpleRecipe expectedRecipe = new SimpleRecipe("Grilled Cheese",2);
        HashSet<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Sliced Bread",4,Units.INDIVIDUAL));
        ingredients.add(new Ingredient("Butter",2,Units.TABLESPOON));
        expectedRecipe.addStep(new Step(ingredients,"Butter the bread",1));
        ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Cheese slice",4,Units.INDIVIDUAL));
        expectedRecipe.addStep(new Step(ingredients,"Make a sandwich with the cheese slices",1));
        assertEquals(expectedRecipe, simpleRecipe.scale(2));

        //scale will now return a new SimpleRecipe object
    }
    @Test
    public void testAddStep() {
        simpleRecipe.addStep(new Step(new HashSet<>(),"Fry sandwich on both sides",7));
        assertEquals(3,simpleRecipe.getSteps().size());
    }
    @Test
    public void testIngrediantQtyBoundry(){
        HashSet<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("Butter",-1,Units.TABLESPOON));
        simpleRecipe.addStep(new Step(ingredients,"Fry sandwich on both sides",7));
        assertEquals(2,simpleRecipe.getSteps().size());
    }
    

}
