import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.*;

//@author m-bernardin
public class RecipeBookTest
{
    private RecipeBook book;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    public RecipeBookTest()
    {
    }

    @BeforeEach
    public void setUp()
    {
        book=new RecipeBook("Mom's Recipes");
        initializeTestRecipes();
    }

    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testUpvoteRecipe()
    {
        book.addRecipe(recipe1);
        book.upvoteRecipe(0);
        assertEquals(1,recipe1.getRating());
    }

    @Test
    public void testDownvoteRecipe()
    {
        book.addRecipe(recipe1);
        book.downvoteRecipe(0);
        assertEquals(-1,recipe1.getRating());
    }

    @Test
    public void testGetTopRated()
    {
        book.addRecipe(recipe1);
        book.addRecipe(recipe2);
        book.upvoteRecipe(1);
        assertEquals(recipe2,book.getTopRated());
    }


    @Test
    public void testAddRecipe()
    {
        book.addRecipe(recipe1);
        assertEquals(recipe1,book.getRecipes().get(0));
    }

    @Test
    public void testSearchName()
    {
        book.addRecipe(recipe1);
        ArrayList<Recipe> expectedResult=new ArrayList<>();
        expectedResult.add(recipe1);
        assertEquals(expectedResult, book.searchName("Pancakes"));
    }

    @Test
    public void testSearchIngredients()
    {
        setupSearchTest();
        ArrayList<Recipe> results=book.searchIngredient("Egg");
        assertEquals(recipe2,results.get(1));
    }

    @Test
    public void testSearchTime()
    {
        setupSearchTest();
        ArrayList<Recipe> results=book.searchTime(30);
        assertEquals(recipe2,results.get(1));
    }

    @Test
    public void testSearchTimeUnderBound()
    {
        setupSearchTest();
        ArrayList<Recipe> results=book.searchTime(-1);
        assertEquals(new Recipe("invalidRecipe",0), results.get(0));
    }

    @Test
    public void testSearchTag()
    {
        setupSearchTest();
        ArrayList<Recipe> results=book.searchTag(Tags.BREAKFAST);
        assertEquals(recipe2, results.get(1));
    }

    public void initializeTestRecipes()
    {
        ArrayList<Ingredient> ingredients=new ArrayList<>();
        ingredients.add(new Ingredient("Egg", 2, Units.INDIVIDUAL));
        recipe1=new SimpleRecipe("Pancakes",4);
        recipe1.addStep(new Step(ingredients, "Beat eggs", 3, "whisk"));
        recipe2=new Recipe("Waffles", 4);
        recipe3=new Recipe("Pasta", 2);
        recipe4=new SimpleRecipe("Omelette",2);
        recipe2.addStep(new Step(ingredients, "Beat eggs", 3, "whisk"));
        recipe1.addTag(Tags.BREAKFAST);
        recipe2.addTag(Tags.BREAKFAST);
        recipe4.addTag(Tags.BREAKFAST);
    }

    public void setupSearchTest()
    {
        book.addRecipe(recipe1);
        book.addRecipe(recipe2);
        book.addRecipe(recipe3);
    }
}
