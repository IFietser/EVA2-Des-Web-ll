package cl.ipss.saborgourmet.services;

import cl.ipss.saborgourmet.models.Reservacion;
import java.util.List;

public interface ReservacionService {
    Reservacion crear(Reservacion reservacion);
    Reservacion findById(Long id);
    List<Reservacion> findAll();
    List<Reservacion> findByCliente(Long clienteId);
    void cancelar(Long id);
    void delete(Long id);
}

