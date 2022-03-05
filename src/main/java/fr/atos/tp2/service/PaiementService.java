package fr.atos.tp2.service;

import fr.atos.tp2.bean.Commande;
import fr.atos.tp2.bean.Paiement;
import fr.atos.tp2.dao.PaiementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private PaiementDao paiementDao;

    public int save(Paiement paiement){
        if(findByReference(paiement.getReference())!= null){
            return -1;
        }
        Commande commande = commandeService.findByReference(paiement.getCommande().getReference());
        paiement.setCommande(commande);
        if(commande==null){
            return -2;
        }else if(commande.getTotalPaye()+paiement.getMontant()>commande.getTotal()){
            return -3;
        } else {
            double nvTotalPaye = commande.getTotalPaye()+paiement.getMontant();
            commande.setTotalPaye(nvTotalPaye);
            commandeService.update(commande);
            paiementDao.save(paiement);
            return 1;
        }

    }

    public Paiement findByReference(String reference) {
        return paiementDao.findByReference(reference);
    }

    public List<Paiement> findByCommandeReference(String reference) {
        return paiementDao.findByCommandeReference(reference);
    }

    public int deleteByCommandeReference(String reference) {
        return paiementDao.deleteByCommandeReference(reference);
    }

    public List<Paiement> findAll() {
        return paiementDao.findAll();
    }
}
