package guru.springframework.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;

public class IngredientControllerTest {
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	IngredientService ingredientService;
	
	@Mock
	UnitOfMeasureService unitOfMeasureService;
	
	IngredientController controller;
	
	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
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
	
	@Test
	public void showIngredient() throws Exception {
		//Given
		IngredientCommand command = new IngredientCommand();
		
		//When
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
		
		//Then
		mockMvc.perform(get("/recipe/1/ingredient/2/show"))
			.andExpect(status().isOk())
			.andExpect(view().name("recipe/ingredient/show"))
			.andExpect(model().attributeExists("ingredient"));
	}
	
	@Test
	public void testNewIngredientForm() throws Exception {
		//Given
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);
		
		//When
		when(recipeService.findCommandById(anyLong())).thenReturn(command);
		when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());
		
		//Then
		mockMvc.perform(get("/recipe/1/ingredient/new"))
			.andExpect(status().isOk())
			.andExpect(view().name("/recipe/ingredient/ingredientform"))
			.andExpect(model().attributeExists("ingredient"))
			.andExpect(model().attributeExists("uomList"));
		
		verify(recipeService, times(1)).findCommandById(anyLong());
	}
	
	@Test
	public void testUpdateIngredientForm() throws Exception {
		//Given
		IngredientCommand command = new IngredientCommand();
		
		//when
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(command);
		when(unitOfMeasureService.findAll()).thenReturn(new HashSet<>());
		
		//then
		mockMvc.perform(get("/recipe/1/ingredient/2/update"))
			.andExpect(status().isOk())
			.andExpect(view().name("/recipe/ingredient/ingredientform"))
			.andExpect(model().attributeExists("ingredient"))
			.andExpect(model().attributeExists("uomList"));			
	}
	
	@Test
	public void testSaveOrUpdateCommand() throws Exception {
		//Given
		IngredientCommand command = new IngredientCommand();
		command.setId(3L);
		command.setRecipeId(2L);
		
		//When
		when(ingredientService.saveIngredientCommand(any())).thenReturn(command);
		
		//Then
		mockMvc.perform(post("/recipe/2/ingredient")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some text description"))
					.andExpect(status().is3xxRedirection())
					.andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));			
	}
	
	@Test
	public void testDeleteIngredient() throws Exception {
		//Given
		Long recipeId = 2L;
		Long ingredientId=3L;
		
		//when 
		//Delete returns void so no need to mock 
		
		//Then
		mockMvc.perform(get("/recipe/" + recipeId + "/ingredient/" + ingredientId + "/delete"))
					.andExpect(status().is3xxRedirection())
					.andExpect(view().name("redirect:/recipe/2/ingredients"));
		
		verify(ingredientService, times(1)).deleteByRecipeIdAndIngredientId(anyLong(), anyLong());
	}

}
