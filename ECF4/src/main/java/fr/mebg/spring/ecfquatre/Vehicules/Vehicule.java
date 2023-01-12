package fr.mebg.spring.ecfquatre.Vehicules;

import fr.mebg.spring.ecfquatre.Utils.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Vehicule extends Entity {
//    @Id
//    private String id;
    private String modele;
    private String etat;
    private int prix;
    private Boolean disponibilite;
    private String marque;
    private String immatriculation;

}
