package br.com.teste.schedulingtransfer.services;

import br.com.teste.schedulingtransfer.dto.SchedulingDto;
import br.com.teste.schedulingtransfer.models.Scheduling;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SchedulingService {

    List<Scheduling> findAll();

    Optional<Scheduling> findById(UUID schedulingId);

    void delete(Scheduling scheduling);

    Scheduling save(SchedulingDto schedulingDto);
}
