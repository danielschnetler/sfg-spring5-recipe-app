package guru.springframework.services;

import java.util.Set;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

public interface RecipeService {
	
	Set<Recipe> getRecipes();
	
	Recipe findById(long id);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);

}
