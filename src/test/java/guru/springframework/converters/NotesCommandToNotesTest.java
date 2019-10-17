package guru.springframework.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;

public class NotesCommandToNotesTest {
	
	public static final Long ID_VALUE = new Long(1L);
	public static final String RECIPE_NOTES = "Notes";
	
	NotesCommandToNotes converter;

	@Before
	public void setUp() throws Exception {
		converter = new NotesCommandToNotes();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new NotesCommand()));
	}

	@Test
	public void test() {
		//Given
		NotesCommand command = new NotesCommand();
		command.setId(ID_VALUE);
		command.setRecipeNotes(RECIPE_NOTES);
		
		//When
		Notes notes = converter.convert(command);
		
		//Then
		assertNotNull(notes);
		assertEquals(ID_VALUE, notes.getId());
		assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
	}

}
