package cl.ipss.saborgourmet.services;

import cl.ipss.saborgourmet.models.Mesa;
import java.util.List;

public interface MesaService {
    Mesa save(Mesa mesa);
    Mesa findById(Long id);
    List<Mesa> findAll();
    List<Mesa> findDisponibles(int cantidadPersonas);
    void delete(Long id);
}
