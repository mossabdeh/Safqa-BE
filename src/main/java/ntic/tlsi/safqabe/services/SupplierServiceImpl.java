package ntic.tlsi.safqabe.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ntic.tlsi.safqabe.entities.Adress;
import ntic.tlsi.safqabe.entities.Client;
import ntic.tlsi.safqabe.entities.Supplier;
import ntic.tlsi.safqabe.repositories.AdressRepository;
import ntic.tlsi.safqabe.repositories.ClientRepository;
import ntic.tlsi.safqabe.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierServiceInterface {
    private SupplierRepository supplierRepository;
    private AdressRepository adressRepository ;

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> listSuppliers() {
        return supplierRepository.findAll();
    }

    @Override //todo
    public Supplier updateSupplier(Long supplierId) {
        return null;
    }

    @Override
    public void deleteSupplier(Long supplierId) {
      supplierRepository.deleteById(supplierId);
    }

    @Override
    @Transactional
    public Adress assignAdress2Supplier(Adress adress, Long supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new IllegalArgumentException
                        ("Supplier with id " + supplierId + " not found"));
        adressRepository.save(adress);
        supplier.setAdress(adress);
        supplierRepository.save(supplier);
        return adress;
    }
}
