package guru.springframework.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;

public class NotesToNotesCommandTest {
	
	public static final Long ID_VALUE = new Long(1L);
	public static final String RECIPE_NOTES = "Notes";
	
	NotesToNotesCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new NotesToNotesCommand();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test 
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Notes()));
	}

	@Test
	public void testConvert() {
		//Given
		Notes notes = new Notes();
		notes.setId(ID_VALUE);
		notes.setRecipeNotes(RECIPE_NOTES);
		
		//When
		NotesCommand command = converter.convert(notes);
		
		//Then
		assertNotNull(command);
		assertNotNull(command.getRecipeNotes());
		assertEquals(ID_VALUE, command.getId());
		assertEquals(RECIPE_NOTES, command.getRecipeNotes());
		
	}

}
