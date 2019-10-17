package guru.springframework.converters;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;

public class IngredientCommandToIngredientTest {
	
	public static final Recipe RECIPE = new Recipe();
	public static final BigDecimal AMOUNT = new BigDecimal("1");
	public static final String DESCRIPTION = "description";
	public static final Long ID_VALUE = new Long(1L);
	public static final Long UOM_ID = new Long(2L);
	
	IngredientCommandToIngredient converter;

	@Before
	public void setUp() throws Exception {
		converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}
	
	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new IngredientCommand()));
	}

	@Test
	public void testConvert() {
		//Given
		IngredientCommand command = new IngredientCommand();
		command.setId(ID_VALUE);
		command.setAmount(AMOUNT);
		command.setDescription(DESCRIPTION);
		UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
		uomCommand.setId(UOM_ID);		
		command.setUnitOfMeasure(uomCommand);
		
		//when
		Ingredient ingredient = converter.convert(command);
		
		//then
		assertNotNull(ingredient);
		assertNotNull(ingredient.getUom());
		assertEquals(ID_VALUE, ingredient.getId());
		assertEquals(AMOUNT, ingredient.getAmount());
		assertEquals(DESCRIPTION, ingredient.getDescription());
		assertEquals(UOM_ID, ingredient.getUom().getId());
		
	}
	
	@Test
	public void testConvertWithNullUOM() {
		//given
		IngredientCommand command = new IngredientCommand();
		command.setId(ID_VALUE);
		command.setAmount(AMOUNT);
		command.setDescription(DESCRIPTION);
		
		//When
		Ingredient ingredient = converter.convert(command);
		
		//then
		assertNotNull(ingredient);
		assertNull(ingredient.getUom());
		assertEquals(ID_VALUE, ingredient.getId());
		assertEquals(AMOUNT, ingredient.getAmount());
		assertEquals(DESCRIPTION, ingredient.getDescription());
	}

}
