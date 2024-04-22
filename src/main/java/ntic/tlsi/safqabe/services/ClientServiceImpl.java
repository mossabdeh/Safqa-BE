package ntic.tlsi.safqabe.services;

import lombok.AllArgsConstructor;

import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientServiceInterface {
    private ClientRepository clientRepository;


    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    @Override // todo
    public Client updateClient(Long clientId) {
        return null;
    }

    @Override
    public void deleteClient(Long clientId) {
      clientRepository.deleteById(clientId);
    }
}
