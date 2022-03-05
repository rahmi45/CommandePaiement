package fr.atos.tp2.ws;

import fr.atos.tp2.bean.Paiement;
import fr.atos.tp2.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/stock/paiement")
public class PaiementProvided {

    @Autowired
    private PaiementService paiementService;

    @PostMapping("/")
    public int save(@RequestBody Paiement paiement) {
        return paiementService.save(paiement);
    }
    @GetMapping("/reference/{reference}")
    public Paiement findByReference(@PathVariable String reference) {
        return paiementService.findByReference(reference);
    }
    @GetMapping("/commande/reference/{reference}")
    public List<Paiement> findByCommandeReference(@PathVariable String reference) {
        return paiementService.findByCommandeReference(reference);
    }
    @DeleteMapping("/reference/{reference}")
    public int deleteByCommandeReference(@PathVariable String reference) {
        return paiementService.deleteByCommandeReference(reference);
    }
    @GetMapping("/")
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }

    @GetMapping("/montant-greater-than/{montant}")
    public List<Paiement> findByMontantSuperieur(@PathVariable double montant) {
        return paiementService.findByMontantSuperieur(montant);
    }

}
