package ntic.tlsi.safqabe.services;


import ntic.tlsi.safqabe.entities.Adress;
import ntic.tlsi.safqabe.entities.Supplier;

import java.util.List;

public interface SupplierServiceInterface {
    Supplier saveSupplier(Supplier supplier);
    List<Supplier> listSuppliers();

    Supplier updateSupplier(Long supplierId);
    void deleteSupplier(Long supplierId);

    Adress assignAdress2Supplier(Adress adress , Long supplierId);


}
