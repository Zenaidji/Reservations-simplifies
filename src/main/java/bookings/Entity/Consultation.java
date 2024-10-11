package bookings.Entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name="Consultations")
@Getter
@Setter
@EqualsAndHashCode
public class Consultation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", nullable = false, length = 100)
    @NonNull
    private String nom;
    @NonNull

    @Column(name = "prenom", nullable = false, length = 100)
    private String prenom;

    @Column(name = "adresse", length = 255)
    private String adresse;

    @Column(name = "mail", nullable = false, unique = true, length = 255)
    private String mail;



    @Column(name = "portable", length = 20)
    private String portable;

    @Column(name = "motif_consultation", length = 40)
    private String motifConsultation;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCreneau", nullable = false)
    private Creneaux creneau;
}


