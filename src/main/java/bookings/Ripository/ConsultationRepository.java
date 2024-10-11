package bookings.Ripository;

import bookings.Entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {


    Consultation save(Consultation consultation);
}
