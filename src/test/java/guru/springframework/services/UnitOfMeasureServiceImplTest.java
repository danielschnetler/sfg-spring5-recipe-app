package guru.springframework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;

public class UnitOfMeasureServiceImplTest {
	
	UnitOfMeasureService service;
	UnitOfMeasureToUnitOfMeasureCommand uomToUomCommand = new UnitOfMeasureToUnitOfMeasureCommand();
	
	@Mock
	UnitOfMeasureRepository uomRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		service = new UnitOfMeasureServiceImpl(uomRepository, uomToUomCommand);
	}

	@Test
	public void testFindAll() {
		//Given
		Set<UnitOfMeasure> uomSet = new HashSet<UnitOfMeasure>();
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(1L);
		uomSet.add(uom1);
		
		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom2.setId(2L);
		uomSet.add(uom2);
		
		when(uomRepository.findAll()).thenReturn(uomSet);
		
		//When
		Set<UnitOfMeasureCommand> uomCommandSet = service.findAll();
		
		//Then
		assertNotNull(uomCommandSet);
		assertEquals(uomSet.size(), uomCommandSet.size());
		
		verify(uomRepository, times(1)).findAll();
	}

}
