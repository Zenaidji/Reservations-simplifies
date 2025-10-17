package bookings.Ripository;

import bookings.Entity.Creneaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CreneauxRipository extends JpaRepository<Creneaux, Integer> {

    List<Creneaux> findByDateAndEstPris(LocalDate date, boolean estPris);
    Creneaux findById(int id);

    @Query("SELECT DISTINCT c.date FROM Creneaux c WHERE c.estPris = false")
    List<LocalDate> findDatesAvecCreneauxDisponibles();


}
