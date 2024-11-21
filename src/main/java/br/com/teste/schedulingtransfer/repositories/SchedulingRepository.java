package br.com.teste.schedulingtransfer.repositories;

import br.com.teste.schedulingtransfer.models.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchedulingRepository extends JpaRepository<Scheduling, UUID> {

}
