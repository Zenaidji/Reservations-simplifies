package bookings.Services;

import bookings.Entity.Consultation;
import bookings.Entity.Creneaux;
import bookings.Ripository.ConsultationRepository;
import bookings.Ripository.CreneauxRipository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CreneauService {


    @Autowired
    private CreneauxRipository creneauRepository;
    @Autowired
    private ConsultationRepository consultationRepository;


    public List<Creneaux> getCreneauxParDate(LocalDate date) {
        return creneauRepository.findByDateAndEstPris(date,false);

    }

    public void updateEstpris(int id) {
         Creneaux c =getCreneauxById(id);
         c.setEstPris(true);
         saveOrUpdateCreneau(c);
    }


    public Creneaux getCreneauxById(int id) {
       return creneauRepository.findById(id);
    }


    public void saveOrUpdateCreneau(Creneaux c){
        creneauRepository.save(c);
    }

    public List<Consultation> getAllConsultation(){
        List<Consultation> allConsultations = consultationRepository.findAll();
        return allConsultations;
    }

    public void saveOrUpdateConsultation(Consultation c){
        consultationRepository.save(c);
    }


    public void annulerCreneau(int id) {

        Creneaux creneau = getCreneauxById(id);

        if (creneau == null) {
            throw new EntityNotFoundException("Creneau non trouvé avec l'ID: " + id);
        }


        creneau.setEst_annule(true);
        creneau.setDate_annulation(LocalDate.now());


        saveOrUpdateCreneau(creneau);
    }



    public void marquerPatientAbsent(int id) {
        Creneaux creneau = getCreneauxById(id);
        if (creneau != null) {

            creneau.setEst_absent(true);
            saveOrUpdateCreneau(creneau);
        } else {
            throw new EntityNotFoundException("Creneau non trouvé pour ID: " + id);
        }
    }






}
