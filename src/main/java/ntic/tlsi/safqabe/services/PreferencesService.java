package ntic.tlsi.safqabe.services;

import ntic.tlsi.safqabe.entities.Preferences;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.repositories.PreferencesRepository;
import ntic.tlsi.safqabe.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PreferencesService {

    @Autowired
    private PreferencesRepository preferencesRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Preferences savePreferencesForClient(Preferences preferences, Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        preferences.setClient(client);
        preferencesRepository.save(preferences);

        return preferences;
    }

    public Preferences getPreferencesForClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        return preferencesRepository.findByClient(client)
                .orElseThrow(() -> new RuntimeException("Preferences not found"));
    }
}
