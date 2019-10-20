package guru.springframework.services;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
	
	private final UnitOfMeasureRepository uomRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
		
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		super();
		this.uomRepository = uomRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Override
	public Set<UnitOfMeasureCommand> findAll() {
		return StreamSupport.stream(uomRepository.findAll().spliterator(), false)
				.map(unitOfMeasureToUnitOfMeasureCommand::convert)
				.collect(Collectors.toSet());		
	}
}
