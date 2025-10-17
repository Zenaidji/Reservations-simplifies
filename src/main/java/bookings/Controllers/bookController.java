package bookings.Controllers;

import bookings.Entity.Consultation;
import bookings.Entity.Creneaux;
import bookings.Services.CreneauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class bookController {


    @Autowired
    private CreneauService creneauService;
    @GetMapping("/")
    public String index() {
        return "acueil";  // Le nom de votre page HTML (index.html) dans le répertoire "templates"
    }
    @GetMapping("/getAllCreneauxParDate")
    @ResponseBody
    public List<Creneaux> getAllCreneauxParDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return creneauService.getCreneauxParDate(date);
    }
    @GetMapping("/getDatesDisponibles")
    @ResponseBody
    public List<LocalDate> getDatesDisponibles() {
        return creneauService.getDatesDisponibles();
    }

    @GetMapping("/rendezvous")
    public String rendezVous() {
        return "rendezvous";
    }

    @PostMapping("/submit-form")
    public String submitForm(@RequestParam("nom") String nom,
                             @RequestParam("prenom") String prenom,
                             @RequestParam("email") String email,
                             @RequestParam("portable") String portable,
                             @RequestParam("adresse") String adresse,
                             @RequestParam("motif") String motif,
                             @RequestParam("creneauSelectionne") int  id,
                             Model model) {
        LocalDate date=null;
        LocalTime heure=null;

        creneauService.updateEstpris(id);
        Consultation c=new Consultation();
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setMail(email);
        c.setPortable(portable);
        c.setAdresse(adresse);
        c.setMotifConsultation(motif);
        Creneaux creneau = creneauService.getCreneauxById(id);
        if (creneau != null) {
            c.setCreneau(creneau);

            creneauService.updateEstpris(id);
            creneauService.saveOrUpdateConsultation(c);
        }

        model.addAttribute("nom", nom);
        model.addAttribute("prenom", prenom);
        model.addAttribute("email", email);
        model.addAttribute("creneau", creneau);
        model.addAttribute("portable", portable);
        model.addAttribute("motif", motif);
        model.addAttribute("id", id);
        model.addAttribute("adresse", adresse);

        return "confirmation";
    }


    @GetMapping("/listeConsultation")
    public String getAllConsultations() {
        return "consultations";


    }
    @GetMapping("/acueil")
    public String acueil(){
        return "acueil";
    }


    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/presentation")
    public String presentation(){
        return "presentation";
    }


    @PostMapping("/Annulation")
    public String annulation(@RequestParam("id") int id) {
        creneauService.annulerCreneau(id);
        return "consultations";
    }


    @PostMapping("/MarquerAbsent")
    public String marquerAbsent(@RequestParam("id") int id) {
        creneauService.marquerPatientAbsent(id);
        return "consultations";
    }





}
