package guru.springframework.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;

public class UnitOfMeasureToUnitOfMeasureCommandTest {
	
	public static final Long ID_VALUE = Long.valueOf(1L);
	public static final String DESCRIPTION = "description";
	
	UnitOfMeasureToUnitOfMeasureCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}

	@Test
	public void testConvert() {
		//Given
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(ID_VALUE);
		uom.setUom(DESCRIPTION);
		
		//When
		UnitOfMeasureCommand command = converter.convert(uom);
		
		//Then
		assertNotNull(command);
		assertEquals(ID_VALUE, command.getId());
		assertEquals(DESCRIPTION, command.getDescription());
	}

}
