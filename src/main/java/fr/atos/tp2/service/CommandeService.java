package fr.atos.tp2.service;

import fr.atos.tp2.bean.Commande;
import fr.atos.tp2.dao.CommandeDao;
import fr.atos.tp2.dto.CommandeTdo;
import fr.atos.tp2.service.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeDao commandeDao;

    @Autowired
    private PaiementService paiementService;

    @Autowired
    private EntityManager entityManager;

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

    public List<Commande> findFinalise() {
        return commandeDao.findFinalise();
    }

    public List<Commande> findByCriteria(CommandeTdo commandeTdo){
        String query = "SELECT c FROM Commande c WHERE 1=1";
        //commandeTdo.getReference()!=null && !commandeTdo.getReference().isEmpty()
        if(StringUtil.isNotEmpty(commandeTdo.getReference()))
            query+=" AND c.reference LIKE '%"+commandeTdo.getReference()+"%'";

        if(StringUtil.isNotEmpty(commandeTdo.getTotalMin()))
            query+=" AND c.total >= '"+commandeTdo.getTotalMin()+"'";

        if(StringUtil.isNotEmpty(commandeTdo.getTotalMax()))
            query+=" AND c.total <= '"+commandeTdo.getTotalMax()+"'";

        return entityManager.createQuery(query).getResultList();
    }
}
