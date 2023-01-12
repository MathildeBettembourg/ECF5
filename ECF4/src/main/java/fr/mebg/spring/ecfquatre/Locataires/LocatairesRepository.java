package fr.mebg.spring.ecfquatre.Locataires;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocatairesRepository extends MongoRepository<Locataire, String> {
}
