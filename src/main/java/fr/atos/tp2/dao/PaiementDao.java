package fr.atos.tp2.dao;


import fr.atos.tp2.bean.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementDao extends JpaRepository<Paiement, Long> {

    Paiement findByReference(String reference);
    List<Paiement> findByCommandeReference(String reference);
    int deleteByCommandeReference(String reference);

    @Query("SELECT p FROM Paiement p WHERE p.montant >= :montant")
    List<Paiement> findByMontantSuperieur(@Param("montant") double montant);
}
