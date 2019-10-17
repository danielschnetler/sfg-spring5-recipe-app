package guru.springframework.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;

public class RecipeToRecipeCommandTest {
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

	RecipeToRecipeCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new RecipeToRecipeCommand(new CategoryToCategoryCommand(),
				new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
				new NotesToNotesCommand());
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Recipe()));
	}

	@Test
	public void testConvert() {
		//Given
		Recipe recipe = new Recipe();
		recipe.setId(RECIPE_ID);
		recipe.setCookTime(COOK_TIME);
		recipe.setPrepTime(PREP_TIME);
		recipe.setDescription(DESCRIPTION);
		recipe.setDirections(DIRECTIONS);
		recipe.setDifficulty(DIFFICULTY);
		recipe.setServings(SERVINGS);
		recipe.setSource(SOURCE);
		recipe.setUrl(URL);
		
		Category category1 = new Category();
		category1.setId(CAT_ID_1);
		
		Category category2 = new Category();
		category2.setId(CAT_ID2);
		
		recipe.getCategories().add(category1);
		recipe.getCategories().add(category2);
		
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(INGRED_ID_1);
		
		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(INGRED_ID_2);
		
		recipe.getIngredients().add(ingredient1);
		recipe.getIngredients().add(ingredient2);
		
		//When
		RecipeCommand command = converter.convert(recipe);
		
		//Then
		assertNotNull(command);
		assertEquals(RECIPE_ID, command.getId());
		assertEquals(COOK_TIME, command.getCookTime());
		assertEquals(PREP_TIME, command.getPrepTime());
		assertEquals(DESCRIPTION, command.getDescription());
		assertEquals(DIRECTIONS, command.getDirections());
		assertEquals(DIFFICULTY, command.getDifficulty());
		assertEquals(SERVINGS, command.getServings());
		assertEquals(SOURCE, command.getSource());
		assertEquals(URL, command.getUrl());
		assertEquals(2, command.getCategories().size());
		assertEquals(2, command.getIngredients().size());
	}

}
