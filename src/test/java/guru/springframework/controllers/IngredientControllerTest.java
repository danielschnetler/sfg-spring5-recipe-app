package guru.springframework.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.RecipeService;

public class IngredientControllerTest {
	
	@Mock
	RecipeService recipeService;
	
	IngredientController controller;
	
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controller = new IngredientController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testListingIngredients() throws Exception {
		//Given
		RecipeCommand command = new RecipeCommand();
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		
		//When
		mockMvc.perform(get("/recipe/1/ingredients"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/list"))
			.andExpect(model().attributeExists("recipe"));
		
		//then
		verify(recipeService, times(1)).findCommandById(anyLong());
	}

}
