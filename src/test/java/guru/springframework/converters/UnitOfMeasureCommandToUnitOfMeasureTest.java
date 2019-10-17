package guru.springframework.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
	
	public static final Long ID_VALUE = Long.valueOf(1L);
	public static final String DESCRIPTION = "description";
	
	UnitOfMeasureCommandToUnitOfMeasure converter;

	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}

	@Test
	public void testConvert() {
		//Given
		UnitOfMeasureCommand command = new UnitOfMeasureCommand();
		command.setId(ID_VALUE);
		command.setDescription(DESCRIPTION);
		
		//When
		UnitOfMeasure uom = converter.convert(command);
		
		//Then
		assertNotNull(uom);
		assertEquals(ID_VALUE, uom.getId());
		assertEquals(DESCRIPTION, uom.getUom());
	}

}
