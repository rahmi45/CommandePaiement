package fr.atos.tp2.service;

import fr.atos.tp2.bean.Commande;
import fr.atos.tp2.dao.CommandeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeDao commandeDao;

    @Autowired
    private PaiementService paiementService;

    @Transactional
    public int deleteByReference(String reference) {
        int resultPaiement = paiementService.deleteByCommandeReference(reference);
        int resultCommande =  commandeDao.deleteByReference(reference);
        return  resultCommande + resultPaiement;
    }
    public void update(Commande commande){
        commandeDao.save(commande);
    }

    public int save(Commande commande){
        if(findByReference(commande.getReference())!= null){
            return -1;
        }else if (commande.getTotal()<commande.getTotalPaye()){
            return -2;
        }else {
            commandeDao.save(commande);
            return 1;
        }
    }

    public Commande findByReference(String reference) {
        return commandeDao.findByReference(reference);
    }

    public List<Commande> findAll() {
        return commandeDao.findAll();
    }
}
