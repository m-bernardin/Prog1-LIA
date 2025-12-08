import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

//@author m-bernardin
public class RecipeBookTest
{
    private RecipeBook book;
    private Recipe recipe;

    public RecipeBookTest()
    {
    }

    @BeforeEach
    public void setUp()
    {
        book=new RecipeBook("Mom's Recipes");
        recipe=new SimpleRecipe("Pancakes",4);
    }

    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testUpvoteRecipe()
    {
        book.addRecipe(recipe);
        book.upvoteRecipe(recipe);
        assertEquals(1,recipe.getRating());
    }

    @Test
    public void testDownvoteRecipe()
    {
        book.addRecipe(recipe);
        book.downvoteRecipe(recipe);
        assertEquals(1,recipe.getRating());
    }

    @Test
    public void testGetTopRated()
    {
        Recipe recipe2=new SimpleRecipe("Omelette",2);
        book.addRecipe(recipe);
        book.addRecipe(recipe2);
        book.upvoteRecipe(recipe2);
        assertEquals(recipe2,book.getTopRated());
    }

    @Test
    public void testGetAll()
    {
        Recipe recipe2=new SimpleRecipe("Omelette",2);
        book.addRecipe(recipe2);
        book.addRecipe(recipe);
        assertEquals(new ArrayList<>(), book.getAll());
    }

    @Test
    public void testAddRecipe()
    {

    }

    @Test
    public void testSearchName()
    {
        recipe.setName("pasta");
        book.addRecipe(recipe);
        ArrayList<Recipe> expectedResult=new ArrayList<>();
        expectedResult.add(recipe);
        assertEquals(expectedResult, book.searchName("pasta"));
    }

    @Test
    public void testSearchIngredients()
    {
    }

    @Test
    public void testSearchTime()
    {
        /**Step step = new Step();
        step.setTime(12);
        recipe.addStep(step);
        assertEquals(step, step);**/
    }

    @Test
    public void testSearchTimeOverBound()
    {

    }

    @Test
    public void testSearchTimeUnderBound()
    {

    }

    @Test
    public void testSearchTag()
    {
        
    }
}
