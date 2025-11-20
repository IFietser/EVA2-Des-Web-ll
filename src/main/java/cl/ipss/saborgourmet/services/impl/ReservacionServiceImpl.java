package cl.ipss.saborgourmet.services.impl;

import cl.ipss.saborgourmet.models.EstadoReservacion;
import cl.ipss.saborgourmet.models.Reservacion;
import cl.ipss.saborgourmet.repositories.ReservacionRepository;
import cl.ipss.saborgourmet.services.ReservacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservacionServiceImpl implements ReservacionService {

    private final ReservacionRepository reservacionRepository;

    public ReservacionServiceImpl(ReservacionRepository reservacionRepository) {
        this.reservacionRepository = reservacionRepository;
    }

    @Override
    public Reservacion crear(Reservacion reservacion) {

        // Validar solapamiento de horarios
        var conflictos = reservacionRepository.findConflictingReservations(
                reservacion.getMesa().getId(),
                EstadoReservacion.CANCELADA,       // Ignora canceladas
                reservacion.getStartDateTime(),
                reservacion.getEndDateTime()
        );

        if (!conflictos.isEmpty()) {
            throw new RuntimeException("La mesa ya está reservada en ese horario.");
        }

        // Guardar
        return reservacionRepository.save(reservacion);
    }

    @Override
    public Reservacion findById(Long id) {
        return reservacionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reservacion> findAll() {
        return reservacionRepository.findAll();
    }

    @Override
    public List<Reservacion> findByCliente(Long clienteId) {
        return reservacionRepository.findByClienteId(clienteId);
    }

    @Override
    public void cancelar(Long id) {
        Reservacion r = reservacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservación no encontrada"));

        r.setEstado(EstadoReservacion.CANCELADA);
        reservacionRepository.save(r);
    }
}
