package bookings.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity

@Table(name = "creneaux")
@Getter
@Setter
@EqualsAndHashCode
public class Creneaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


   @Column(name = "heure_debut", nullable = false)

    private LocalTime heureDebut;

    @Column(name = "heure_fin", nullable = false)
    private LocalTime heureFin;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "est_pris", nullable = false)
    private Boolean estPris = Boolean.FALSE;
    @JsonIgnore
    @OneToOne(mappedBy = "creneau")
    private Consultation consultation;
    @Column(name = "est_annule", nullable = false)
    private boolean est_annule;

    @Column(name = "date_annulation", nullable = true)
    private LocalDate date_annulation;


    @Column(name="est_absent",nullable = false)
    private boolean est_absent;;




}
