package br.com.teste.schedulingtransfer.controllers;

import br.com.teste.schedulingtransfer.dto.SchedulingDto;
import br.com.teste.schedulingtransfer.models.Scheduling;
import br.com.teste.schedulingtransfer.services.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    @Autowired
    private SchedulingService schedulingService;

    @PostMapping("/schedule")
    public ResponseEntity<Object> schedule(@RequestBody SchedulingDto schedulingDto){
        Scheduling scheduling = schedulingService.save(schedulingDto);
        if (scheduling.getIdScheduling() != null){
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduling);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Não Foi possível realiazar o agendamento!");
    }


    @GetMapping
    public ResponseEntity<List<Scheduling>> listSchedulings(){
        return ResponseEntity.status(HttpStatus.OK).body(schedulingService.findAll());
    }

    @GetMapping("/{schedulingId}")
    public ResponseEntity<Object> getOneScheduling(@PathVariable(value = "schedulingId")UUID schedulingId){
        Optional<Scheduling> schedulingOptional = schedulingService.findById(schedulingId);
        if(!schedulingOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AGENDAMENTO NÃO ENCONTRADO");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(schedulingOptional.get());
        }
    }

    @DeleteMapping("/{schedulingId}")
    public ResponseEntity<Object> deleteScheduling(@PathVariable(value = "schedulingId")UUID schedulingId){
        Optional<Scheduling> schedulingOptional = schedulingService.findById(schedulingId);
        if(!schedulingOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("AGENDAMENTO NÃO ENCONTRADO, NÃO FOI POSSIVEL DELETAR!");
        }else{
            schedulingService.delete(schedulingOptional.get());            ;
            return ResponseEntity.status(HttpStatus.OK).body("AGENDAMENTO DELETADO COM SUCESSO");
        }
    }
}
