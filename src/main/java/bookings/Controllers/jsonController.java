package bookings.Controllers;

import bookings.Entity.Consultation;
import bookings.Services.CreneauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class jsonController {
    @Autowired
    private CreneauService creneauService;

    @GetMapping("/consultations")
    public List<Consultation> getAllConsultations() {
        return creneauService.getAllConsultation();


    }
}
