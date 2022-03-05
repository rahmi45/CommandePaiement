package fr.atos.tp2.ws;

import fr.atos.tp2.bean.Commande;
import fr.atos.tp2.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stock/commande")
public class CommandeProvided {

    @Autowired
    private CommandeService commandeService;

    @DeleteMapping("/reference/{reference}")
    public int deleteByReference(@PathVariable String reference) {
        return commandeService.deleteByReference(reference);
    }

    @PutMapping("/")
    public void update(@RequestBody Commande commande) {
        commandeService.update(commande);
    }

    @PostMapping("/")
    public int save(@RequestBody Commande commande) {
        return commandeService.save(commande);
    }

    @GetMapping("/reference/{reference}")
    public Commande findByReference(@PathVariable String reference) {
        return commandeService.findByReference(reference);
    }
    @GetMapping("/")
    public List<Commande> findAll() {
        return commandeService.findAll();
    }

}
