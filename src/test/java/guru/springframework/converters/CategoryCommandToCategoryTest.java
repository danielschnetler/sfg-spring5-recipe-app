package guru.springframework.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;

public class CategoryCommandToCategoryTest {

	private static final Long ID_VALUE = new Long(1L);
	private static final String DESCRIPTION = "description";
	CategoryCommandToCategory converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new CategoryCommandToCategory();
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	@Test
	public void testConvert() {
		//given
		CategoryCommand categoryCommand = new CategoryCommand();
		categoryCommand.setId(ID_VALUE);
		categoryCommand.setDescription(DESCRIPTION);
		
		//when
		Category category = converter.convert(categoryCommand);
		
		assertEquals(ID_VALUE, category.getId());
		assertEquals(DESCRIPTION, category.getDescription());
	}

}
