package br.com.teste.schedulingtransfer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class SchedulingDto {

    public String originAccount;
    public String destinationAccount;
    public String value;
    public String scheduledDate;
}
