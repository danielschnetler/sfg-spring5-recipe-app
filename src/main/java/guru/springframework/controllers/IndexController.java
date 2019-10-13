package guru.springframework.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;


@Controller
public class IndexController {
	
	private CategoryRepository categoryRepository;
	private UnitOfMeasureRepository uomRepository;
	
	

	public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository uomRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.uomRepository = uomRepository;
	}



	@RequestMapping({ "", "/", "index", "/index.html"})
	public String getIndexPage() {
		Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasureOptional = uomRepository.findByUom("Teaspoon");
		
		System.out.println("Cat ID is:" + categoryOptional.get().getId());
		System.out.println("UOM ID is:" + unitOfMeasureOptional.get().getId());
		
		return "index";
	}
}
