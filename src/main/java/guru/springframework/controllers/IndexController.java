package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	
	private final RecipeService recipeService;	
		
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}



	@RequestMapping({ "", "/", "index", "/index.html"})
	public String getIndexPage(Model model) {
		log.debug("index page accessed");
		model.addAttribute("recipes", recipeService.getRecipes());
		
		return "index";
	}
}
