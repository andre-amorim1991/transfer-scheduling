package br.com.teste.schedulingtransfer.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Table(name="SCHEDULING")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
public class Scheduling implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idScheduling;

    @Column(nullable = false, length = 10)
    private String originAccount;

    @Column(nullable = false, length = 10)
    private String destinationAccount;

    @Column(nullable = false)
    private int transferValue;

    @Column(nullable = false)
    private double transferRate;

    @Column(nullable = false)
    private String schedulingDate;

    @Column(nullable = false)
    private String scheduledDate;

}