package guru.springframework.domain;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.repositories.UnitOfMeasureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureIT {
	
	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void findByDescription() throws Exception{

		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Teaspoon");
		
		assertEquals("Teaspoon", uomOptional.get().getUom());
	}
	
	@Test
	public void findByDescriptionCup() throws Exception{

		Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByUom("Cup");
		
		assertEquals("Cup", uomOptional.get().getUom());
	}

}
