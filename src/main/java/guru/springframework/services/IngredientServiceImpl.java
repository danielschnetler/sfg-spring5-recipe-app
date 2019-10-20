package guru.springframework.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientCommandToIngredient;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final IngredientCommandToIngredient ingredientCommandToIngredient;

	public IngredientServiceImpl(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
			IngredientToIngredientCommand ingredientToIngredientCommand,
			IngredientCommandToIngredient ingredientCommandToIngredient) {
		super();
		this.recipeRepository = recipeRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.ingredientCommandToIngredient = ingredientCommandToIngredient;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()) {
			throw new RuntimeException("Recipe " + recipeId + " not found");
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findAny();

		if (!ingredientCommandOptional.isPresent()) {
			throw new RuntimeException("Ingredient " + ingredientId + " does not exist for recipe " + recipeId);
		}

		return ingredientCommandOptional.get();
	}

	@Override
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

		if (!recipeOptional.isPresent()) {
			log.error("Recipe " + command.getRecipeId() + " not found");
			return new IngredientCommand();
		}

		Recipe recipe = recipeOptional.get();

		Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

		if (!ingredientOptional.isPresent()) {
			recipe.addIngredient(ingredientCommandToIngredient.convert(command));
		} else {
			Ingredient ingredient = ingredientOptional.get();
			ingredient.setAmount(command.getAmount());
			ingredient.setDescription(command.getDescription());
			ingredient.setUom(unitOfMeasureRepository.findById(command.getUom().getId())
					.orElseThrow(() -> new RuntimeException("UOM not found")));
		}
		
		Recipe savedRecipe = recipeRepository.save(recipe);
		
		Optional<Ingredient> savedIngredient = savedRecipe.getIngredients()
				.stream()
				.filter(ingredient -> ingredient.getId().equals(command.getId()))
				.findFirst();
		
		return ingredientToIngredientCommand.convert(savedIngredient.get());
	}

}
