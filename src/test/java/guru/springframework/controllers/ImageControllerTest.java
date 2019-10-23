package guru.springframework.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;

public class ImageControllerTest {

	@Mock
	RecipeService recipeService;

	@Mock
	ImageService imageService;

	ImageController controller;

	MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		controller = new ImageController(recipeService, imageService);

		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new ControllerExceptionHandler())
				.build();
	}

	@Test
	public void testGetImageForm() throws Exception {
		// Given
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.findCommandById(anyLong())).thenReturn(command);

		// When
		mockMvc.perform(get("/recipe/2/image")).andExpect(status().isOk())
				.andExpect(view().name("/recipe/imageuploadform")).andExpect(model().attributeExists("recipe"));

		// Then
		verify(recipeService, times(1)).findCommandById(anyLong());
	}

	@Test
	public void testSetImage() throws Exception {
		// Given
		MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
				"Spring Framework Guru".getBytes());

		// When
		mockMvc.perform(multipart("/recipe/2/image").file(multipartFile)).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/2/show"))
				.andExpect(header().string("Location", "/recipe/2/show"));

		// Then
		verify(imageService, times(1)).saveImageFile(anyLong(), any(MultipartFile.class));
	}

	@Test
	public void testRenderImage() throws Exception {
		// Given
		RecipeCommand command = new RecipeCommand();
		command.setId(1L);

		String s = "fake image text";
		Byte[] byteBoxed = new Byte[s.getBytes().length];

		int i = 0;
		for (byte primByte : s.getBytes()) {
			byteBoxed[i++] = primByte;
		}

		command.setImage(byteBoxed);

		when(recipeService.findCommandById(anyLong())).thenReturn(command);

		MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage")).andExpect(status().isOk())
				.andReturn().getResponse();

		byte[] responseBytes = response.getContentAsByteArray();

		assertEquals(s.getBytes().length, responseBytes.length);

	}

	@Test
	public void testGetImageNumberFOrmatException() throws Exception {
		mockMvc.perform(get("/recipe/asdf/recipeimage")).andExpect(status().isBadRequest())
				.andExpect(view().name("400error"));
	}

}
