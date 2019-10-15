package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private final RecipeRepository recipeRepository;
	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository uomRepository;

	public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UnitOfMeasureRepository uomRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.uomRepository = uomRepository;
	}

	private List<Recipe> getRecipes() {
		List<Recipe> recipies = new ArrayList<>(2);

		Optional<UnitOfMeasure> eachUomOptional = uomRepository.findByUom("Each");

		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("Each UOM not found");
		}

		Optional<UnitOfMeasure> teaspoonUomOptional = uomRepository.findByUom("Teaspoon");

		if (!teaspoonUomOptional.isPresent()) {
			throw new RuntimeException("Teaspoon UOM not found");
		}

		Optional<UnitOfMeasure> tablespoonUomOptional = uomRepository.findByUom("Tablespoon");

		if (!tablespoonUomOptional.isPresent()) {
			throw new RuntimeException("Tablespoon UOM not found");
		}
		Optional<UnitOfMeasure> cupUomOptional = uomRepository.findByUom("Cup");

		if (!cupUomOptional.isPresent()) {
			throw new RuntimeException("Cup UOM not found");
		}
		Optional<UnitOfMeasure> pinchUomOptional = uomRepository.findByUom("Pinch");

		if (!pinchUomOptional.isPresent()) {
			throw new RuntimeException("Pinch UOM not found");
		}
		Optional<UnitOfMeasure> ounceUomOptional = uomRepository.findByUom("Ounce");

		if (!ounceUomOptional.isPresent()) {
			throw new RuntimeException("Ounce UOM not found");
		}
		Optional<UnitOfMeasure> dashUomOptional = uomRepository.findByUom("Dash");

		if (!dashUomOptional.isPresent()) {
			throw new RuntimeException("Dash UOM not found");
		}
		Optional<UnitOfMeasure> pintUomOptional = uomRepository.findByUom("Pint");

		if (!pintUomOptional.isPresent()) {
			throw new RuntimeException("Pint UOM not found");
		}
		
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
		UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
		UnitOfMeasure cupUom = cupUomOptional.get();
		UnitOfMeasure pinchUom = pinchUomOptional.get();
		UnitOfMeasure ounceUom = ounceUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = pintUomOptional.get();
		
		Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
		if(!americanCategoryOptional.isPresent()) {
			throw new RuntimeException("American category not found");
		}
		Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
		if(!italianCategoryOptional.isPresent()) {
			throw new RuntimeException("Italian category not found");
		}
		Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
		if(!mexicanCategoryOptional.isPresent()) {
			throw new RuntimeException("Mexican category not found");
		}
		Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");
		if(!fastFoodCategoryOptional.isPresent()) {
			throw new RuntimeException("Fast Food category not found");
		}
		
		Category americanCategory = americanCategoryOptional.get();
		Category italianCategory = italianCategoryOptional.get();
		Category mexicanCategory = mexicanCategoryOptional.get();
		Category fastFoodCategory = fastFoodCategoryOptional.get();
		
		/*****Guacamole Recipe*****/
		
		Recipe guacamoleRecipe = new Recipe();
		guacamoleRecipe.setDescription("The Perfect Guacamole Recipe");
		guacamoleRecipe.setPrepTime(10);
		guacamoleRecipe.setCookTime(0);
		guacamoleRecipe.setDifficulty(Difficulty.EASY);
		guacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. "
				+ "Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon." + "\n"
				+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole "
				+ "should be a little chunky.)" + "\n" +
				" 3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in"
				+ " the lime juice will provide some balance to the richness of the avocado and will help delay the"
				+ " avocados from turning brown. Add the chopped onion, cilantro, black pepper, and chiles. Chili "
				+ "peppers vary individually in their hotness. So, start with a half of one chili pepper and add to "
				+ "the guacamole to your desired degree of hotness.Remember that much of this is done to taste because "
				+ "of the variability in the fresh ingredients. Start with this recipe and adjust to your taste." + "\n"
				+ "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it "
				+ "and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole "
				+ "brown.) Refrigerate until ready to serve. Chilling tomatoes hurts their flavor, so if you want to "
				+ "add chopped tomato to your guacamole, add it just before serving.");
		guacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
		
		Notes guacamoleNotes = new Notes();
		guacamoleNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your "
				+ "mashed avocados.Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and "
				+ "chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, "
				+ "or strawberries (see our Strawberry Guacamole).The simplest version of guacamole is just mashed "
				+ "avocados with salt. Don't let the lack of availability of other ingredients stop you from making "
				+ "guacamole. To extend a limited supply of avocados, add either sour cream or cottage cheese to "
				+ "your guacamole dip. Purists may be horrified, but so what? It tastes great.For a deviled egg "
				+ "version with guacamole, try our Guacamole Deviled Eggs!");
		guacamoleRecipe.setNotes(guacamoleNotes);
		
		guacamoleRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), eachUom));
		guacamoleRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(0.5), teaspoonUom));
		guacamoleRecipe.addIngredient(new Ingredient("Fresh Lime or Lemon juice", new BigDecimal(1), tablespoonUom));
		guacamoleRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2), tablespoonUom));
		guacamoleRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", new BigDecimal(2), eachUom));
		guacamoleRecipe.addIngredient(new Ingredient("cilantro (leaves and tender stems), finely chopped", new BigDecimal(2), tablespoonUom));
		guacamoleRecipe.addIngredient(new Ingredient("freshly grated black pepper", new BigDecimal(1), dashUom));
		guacamoleRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", new BigDecimal(0.5), eachUom));
		
		guacamoleRecipe.getCategories().add(americanCategory);
		guacamoleRecipe.getCategories().add(mexicanCategory);
		
		recipies.add(guacamoleRecipe);
		
		//Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(9);
        tacosRecipe.setPrepTime(20);
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ");
        tacoNotes.setRecipe(tacosRecipe);

        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaspoonUom));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", new BigDecimal(1), eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", new BigDecimal(1), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", new BigDecimal(3), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", new BigDecimal(4), tablespoonUom));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", new BigDecimal(8), eachUom));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", new BigDecimal(3), cupUom));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", new BigDecimal(2), eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", new BigDecimal(".5"), pintUom));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", new BigDecimal(".25"), eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", new BigDecimal(4), cupUom));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", new BigDecimal(4), eachUom));

        tacosRecipe.getCategories().add(americanCategory);
        tacosRecipe.getCategories().add(mexicanCategory);

        recipies.add(tacosRecipe);
		return recipies;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		recipeRepository.saveAll(getRecipes());
		
	}

}
