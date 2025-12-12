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
        ArrayList<Recipe> results=book.searchIngredient("egg");
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
    public void testSearchTimeOverBound()
    {
        setupSearchTest();
        ArrayList<Recipe> results=book.searchTime(90);
        assertEquals(new Recipe("invalidRecipe",0), results.get(0));
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
        recipe1=new SimpleRecipe("Pancakes",4);
        recipe2=new Recipe("Waffles", 4);
        recipe3=new Recipe("Pasta", 2);
        recipe4=new SimpleRecipe("Omelette",2);
    }

    public void setupSearchTest()
    {
        book.addRecipe(recipe1);
        book.addRecipe(recipe2);
        book.addRecipe(recipe3);
    }
}
