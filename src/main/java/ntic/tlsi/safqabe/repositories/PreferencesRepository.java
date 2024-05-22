package ntic.tlsi.safqabe.repositories;

import ntic.tlsi.safqabe.entities.Preferences;
import ntic.tlsi.safqabe.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PreferencesRepository extends JpaRepository<Preferences, Long> {
    Optional<Preferences> findByClient(Client client);
}
