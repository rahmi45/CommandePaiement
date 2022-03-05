package fr.atos.tp2.dao;

import fr.atos.tp2.bean.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeDao extends JpaRepository<Commande, Long> {
    Commande findByReference(String reference);
    int deleteByReference(String reference);

    @Query("SELECT c FROM Commande c WHERE c.total=c.totalPaye")
    List<Commande>  findFinalise();
}
