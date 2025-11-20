package cl.ipss.saborgourmet.services;

import cl.ipss.saborgourmet.models.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente save(Cliente cliente);
    Cliente findById(Long id);
    Cliente findByEmail(String email);
    List<Cliente> findAll();
    void delete(Long id);
}
