import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class RecipeTest {
    Recipe recipe;
    public RecipeTest() {
    }
    @BeforeEach
    public void setUp() {
        Recipe recipe = new Recipe("Grilled Cheese");
    }
    @AfterEach
    public void tearDown() {   
    }
    @Test
    public void testUpVote() {
        recipe.upVote();
        assertEquals(1, recipe.getRating());
    
    }
    @Test
    public void testDownVote() {
        recipe.downVote();
        assertEquals(-1, recipe.getRating());
    }
    @Test
    public void testAddTag() {
        recipe.addTag(Tags.VEGETARIAN)
        assertTrue(recipe.getTags().contains(Tags.VEGETARIAN));
    }
        
}
