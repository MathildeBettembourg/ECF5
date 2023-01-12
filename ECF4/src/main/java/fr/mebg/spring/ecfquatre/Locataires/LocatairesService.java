package fr.mebg.spring.ecfquatre.Locataires;

import java.util.List;

public interface LocatairesService {
    List<Locataire> findAll();

    Locataire save(Locataire entity);

    Locataire findById(String id);

    void deleteById(String id);

    Locataire miseAjourLocataire(String id, Locataire entity);
}
