package ntic.tlsi.safqabe.services;
import ntic.tlsi.safqabe.entities.Adress;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.entities.Supplier;
import ntic.tlsi.safqabe.repositories.AdressRepository;
import ntic.tlsi.safqabe.repositories.ClientRepository;
import ntic.tlsi.safqabe.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AdressService {

    @Autowired
    private AdressRepository adressRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Adress> getAllAdresses() {
        return adressRepository.findAll();
    }

    public Optional<Adress> getAdressById(Long id) {
        return adressRepository.findById(id);
    }


    public Adress updateAdress(Long id, Adress adressDetails) {
        Adress adress = adressRepository.findById(id).orElseThrow(() -> new RuntimeException("Adress not found"));

        adress.setWilaya(adressDetails.getWilaya());
        adress.setBaladiya(adressDetails.getBaladiya());
        adress.setCodePostal(adressDetails.getCodePostal());
        adress.setAddress1(adressDetails.getAddress1());
        adress.setAddress2(adressDetails.getAddress2());

        return adressRepository.save(adress);
    }

    public void deleteAdress(Long id) {
        Adress adress = adressRepository.findById(id).orElseThrow(() -> new RuntimeException("Adress not found"));
        adressRepository.delete(adress);
    }

    public Adress saveAdressForClient(Adress adress, Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        adress = adressRepository.save(adress);
        client.getAdress().add(adress);
        clientRepository.save(client);
        return adress;
    }

    public Adress saveAdressForSupplier(Adress adress, Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new RuntimeException("Supplier not found"));
        adress = adressRepository.save(adress);
        supplier.setAdress(adress);
        supplierRepository.save(supplier);
        return adress;
    }



    public Set<Adress> getAddressesForClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        return client.getAdress();
    }
}
