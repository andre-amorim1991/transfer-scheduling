package br.com.teste.schedulingtransfer.services.impl;

import br.com.teste.schedulingtransfer.dto.SchedulingDto;
import br.com.teste.schedulingtransfer.models.Scheduling;
import br.com.teste.schedulingtransfer.repositories.SchedulingRepository;
import br.com.teste.schedulingtransfer.services.SchedulingService;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Autowired
    private SchedulingRepository schedulingRepository;

    @Override
    public List<Scheduling> findAll() {
        return schedulingRepository.findAll();
    }

    @Override
    public Optional<Scheduling> findById(UUID schedulingId) {
        return schedulingRepository.findById(schedulingId);
    }

    @Override
    public void delete(Scheduling scheduling) {
        schedulingRepository.delete(scheduling);
    }

    @Override
    public Scheduling save(SchedulingDto schedulingDto) {

        var scheduling = new Scheduling();
        scheduling.setOriginAccount(schedulingDto.getOriginAccount());
        scheduling.setDestinationAccount(schedulingDto.getDestinationAccount());
        scheduling.setTransferValue(convertInt(schedulingDto.getValue()));
        scheduling.setTransferRate(getRate(LocalDate.now(), dateFormater(schedulingDto.getScheduledDate())));
        scheduling.setScheduledDate(schedulingDto.getScheduledDate());// Verificar a data como vira da request
        scheduling.setSchedulingDate(java.time.LocalDate.now().toString());
        schedulingRepository.save(scheduling);
       return scheduling;
    }

    public static int convertInt(String value){
        return Integer.parseInt(value);
    }

    //Converte Data String que vem da Request para
    public static LocalDate dateFormater(String date){

        return LocalDate.parse(date);
    }

    //Calcula diferença de dias e devolve a taxa.
    public static double getRate(LocalDate schedulingDate, LocalDate scheduledDate){
        var days =  Days.daysBetween(schedulingDate, scheduledDate).getDays();

        if (days == 0){
            return 2.5;
        } else if (days >= 1 && days <= 10) {
            return 0.0;
        } else if (days >= 11 && days <= 20 ) {
            return 8.2;
        } else if (days >= 21 && days <= 30) {
            return 6.9;
        }else if (days >= 31 && days <= 40){
            return 4.7;
        }else if (days >= 41 && days <=50){
            return 1.7;
        }else {
             throw new IllegalArgumentException("Nâo foi possível agendar transferência. Tente com outra data.");

        }
    }
}
