package guru.springframework.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;

public class RecipeCommandToRecipeTest {
	public static final Long RECIPE_ID = 1L;
	public static final Integer COOK_TIME = Integer.valueOf("5");
	public static final Integer PREP_TIME = Integer.valueOf("7");
	public static final String DESCRIPTION = "My Recipe";
	public static final String DIRECTIONS = "Directions";
	public static final Difficulty DIFFICULTY = Difficulty.EASY;
	public static final Integer SERVINGS = Integer.valueOf("3");
	public static final String SOURCE = "Source";
	public static final String URL = "Some URL";
	public static final Long CAT_ID_1 = 1L;
	public static final Long CAT_ID2 = 2L;
	public static final Long INGRED_ID_1 = 3L;
	public static final Long INGRED_ID_2 = 4L;
	public static final Long NOTES_ID = 9L;

	RecipeCommandToRecipe converter;

	@Before
	public void setUp() throws Exception {
		converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
				new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
				new NotesCommandToNotes());
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new RecipeCommand()));
	}

	@Test
	public void testConvert() {
		//Given
		RecipeCommand command = new RecipeCommand();
		command.setId(RECIPE_ID);
		command.setCookTime(COOK_TIME);
		command.setPrepTime(PREP_TIME);
		command.setDescription(DESCRIPTION);
		command.setDirections(DIRECTIONS);
		command.setDifficulty(DIFFICULTY);
		command.setServings(SERVINGS);
		command.setSource(SOURCE);
		command.setUrl(URL);
		
		CategoryCommand categoryCommand1 = new CategoryCommand();
		categoryCommand1.setId(CAT_ID_1);
		
		CategoryCommand categoryCommand2 = new CategoryCommand();
		categoryCommand2.setId(CAT_ID2);
		
		command.getCategories().add(categoryCommand1);
		command.getCategories().add(categoryCommand2);
		
		IngredientCommand ingredientCommand1 = new IngredientCommand();
		ingredientCommand1.setId(INGRED_ID_1);
		
		IngredientCommand ingredientCommand2 = new IngredientCommand();
		ingredientCommand2.setId(INGRED_ID_2);
		
		command.getIngredients().add(ingredientCommand1);
		command.getIngredients().add(ingredientCommand2);
		
		//When
		Recipe recipe = converter.convert(command);
		
		//Then 
		assertNotNull(recipe);
		assertEquals(RECIPE_ID, recipe.getId());
		assertEquals(COOK_TIME, recipe.getCookTime());
		assertEquals(PREP_TIME, recipe.getPrepTime());
		assertEquals(DESCRIPTION, recipe.getDescription());
		assertEquals(DIRECTIONS, recipe.getDirections());
		assertEquals(DIFFICULTY, recipe.getDifficulty());
		assertEquals(SERVINGS, recipe.getServings());
		assertEquals(SOURCE, recipe.getSource());
		assertEquals(URL, recipe.getUrl());
		assertEquals(2, recipe.getCategories().size());
		assertEquals(2, recipe.getIngredients().size());		
	}
}
