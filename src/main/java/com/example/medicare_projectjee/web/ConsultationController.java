package com.example.medicare_projectjee.web;

import com.example.medicare_projectjee.dao.entities.Consultation;
import com.example.medicare_projectjee.dao.entities.RendezVous;
import com.example.medicare_projectjee.dao.repositories.RendezVousRepository;
import com.example.medicare_projectjee.service.IHospitalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

@Controller
@AllArgsConstructor
public class ConsultationController {

    private IHospitalService hospitalService;
    private RendezVousRepository rdvRepository;

    // 1. Afficher le formulaire
    @GetMapping("/medecin/formConsultation")
    public String formConsultation(Model model, @RequestParam Long rdvId) {

        RendezVous rdv = rdvRepository.findById(rdvId).get();

        Consultation consultation = new Consultation();
        consultation.setRendezVous(rdv); // On lie la consultation au RDV

        model.addAttribute("consultation", consultation);
        model.addAttribute("rdv", rdv); // Pour afficher le nom du patient

        return "formConsultation";
    }

    @PostMapping("/medecin/saveConsultation")
    public String saveConsultation(Consultation consultation, @RequestParam Long rdvId) {

        RendezVous rdv = rdvRepository.findById(rdvId).get();
        consultation.setRendezVous(rdv);
        consultation.setDateConsultation(new Date());

        hospitalService.saveConsultation(consultation);

        return "redirect:/medecin/dashboard";
    }
    // 3. Voir le rapport (HTML)
    @GetMapping("/consultation/view")
    public String viewConsultation(Model model, @RequestParam Long rdvId) {
        RendezVous rdv = rdvRepository.findById(rdvId).get();
        if (rdv.getConsultation() == null) return "redirect:/"; // Sécurité

        model.addAttribute("rdv", rdv);
        model.addAttribute("consultation", rdv.getConsultation());
        return "viewConsultation";
    }


    // 4. Télécharger le PDF
    @GetMapping("/consultation/export/pdf")
    public void exportToPDF(HttpServletResponse response, @RequestParam Long rdvId) throws IOException {

        // Récupération des données
        RendezVous rdv = rdvRepository.findById(rdvId).get();
        Consultation consult = rdv.getConsultation();

        // Configuration de la réponse HTTP (C'est un fichier PDF)
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Rapport_" + rdv.getPatient().getNom() + ".pdf";
        response.setHeader(headerKey, headerValue);

        // Création du document PDF
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        // Styles de police
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
        Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
        Font fontBody = FontFactory.getFont(FontFactory.HELVETICA, 12);

        // Contenu
        Paragraph title = new Paragraph("RAPPORT MÉDICAL MEDICARE", fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        document.add(new Paragraph("\n"));

        document.add(new Paragraph("Date : " + new SimpleDateFormat("dd/MM/yyyy").format(consult.getDateConsultation())));
        document.add(new Paragraph("\n----------------------------------------------------------\n"));

        document.add(new Paragraph("Médecin : " + rdv.getMedecin().getNom() + " (" + rdv.getMedecin().getSpecialite() + ")", fontHeader));
        document.add(new Paragraph("Patient : " + rdv.getPatient().getNom(), fontHeader));

        document.add(new Paragraph("\n----------------------------------------------------------\n"));
        document.add(new Paragraph("\nCOMPTE-RENDU :\n", fontHeader));
        document.add(new Paragraph(consult.getRapport(), fontBody));

        document.add(new Paragraph("\n\n\nSignature : __________________"));

        document.close();
    }
}
