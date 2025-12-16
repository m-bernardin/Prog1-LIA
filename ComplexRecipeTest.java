import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

public class ComplexRecipeTest{
    private ComplexRecipe recipe;
    private SimpleRecipe subrecipe1;
    private SimpleRecipe subrecipe2;
    private SimpleRecipe subrecipe3;
    
    public ComplexRecipeTest()
    {

    }

    @BeforeEach
    public void startup()
    {
        recipe=new ComplexRecipe("Pasta", 4);
    }

    public void setupSubrecipes() {
        subrecipe1=new SimpleRecipe("Dough", 4);
        subrecipe1.addStep(new Step(new ArrayList<>(),"Chill dough",80,"Refrigerator"));
        subrecipe2=new SimpleRecipe("Sauce", 4);
        subrecipe3=new SimpleRecipe("All Together", 4);
        subrecipe1.completeRecipe();
        subrecipe2.completeRecipe();
        subrecipe3.completeRecipe();
        recipe.addRecipe(subrecipe1);
        recipe.addRecipe(subrecipe2);
        recipe.addRecipe(subrecipe3);
        recipe.completeRecipe();
    }

    @AfterEach
    public void tearDown()
    {

    }

    @Test
    public void calculateTimeTest()
    {
        setupSubrecipes();
        recipe.calculateTime();
        assertEquals(80, recipe.getTime());
    }

    @Test
    public void addRecipeTest()
    {
        Recipe subrecipe=new SimpleRecipe("Sauce", 2);
        recipe.addRecipe(subrecipe);
        ArrayList<Recipe> subrecipes=recipe.getRecipes();
        assertEquals(subrecipe, subrecipes.get(0));
    }

    @Test
    public void scaleTest()
    {
        setupSubrecipes();
        assertEquals("placeholder stringt", recipe.scale(2));
    }
}
