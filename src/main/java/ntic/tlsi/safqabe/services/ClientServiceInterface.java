package ntic.tlsi.safqabe.services;


import ntic.tlsi.safqabe.entities.Client;

import java.util.List;

public interface ClientServiceInterface {
    Client saveClient(Client client);
    List<Client> listClients();

    Client updateClient(Long clientId);
    void deleteClient(Long clientId);



}
