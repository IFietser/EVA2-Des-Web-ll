package cl.ipss.saborgourmet.services.impl;

import cl.ipss.saborgourmet.models.EstadoReservacion;
import cl.ipss.saborgourmet.models.Mesa;
import cl.ipss.saborgourmet.models.Reservacion;
import cl.ipss.saborgourmet.repositories.ReservacionRepository;
import cl.ipss.saborgourmet.services.MesaService;
import cl.ipss.saborgourmet.services.ReservacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservacionServiceImpl implements ReservacionService {

    private final ReservacionRepository reservacionRepository;
    private final MesaService mesaService;

    public ReservacionServiceImpl(ReservacionRepository reservacionRepository,
                                  MesaService mesaService) {
        this.reservacionRepository = reservacionRepository;
        this.mesaService = mesaService;
    }

    @Override
    public Reservacion crear(Reservacion reservacion) {

        // ===============================================
        // 🔥 1) RECARGAR LA MESA REAL DESDE LA BASE DE DATOS
        // ===============================================
        Mesa mesaReal = mesaService.findById(reservacion.getMesa().getId());
        reservacion.setMesa(mesaReal);

        // ==============================
        // 2) Validar capacidad de mesa
        // ==============================
        if (reservacion.getCantidadPersonas() > mesaReal.getAsientos()) {
            throw new RuntimeException(
                    "La mesa solo soporta " + mesaReal.getAsientos() +
                    " personas. Cantidad solicitada: " + reservacion.getCantidadPersonas()
            );
        }

        // ============================================
        // 3) Validar solapamiento de horarios
        // ============================================
        var conflictos = reservacionRepository.findConflictingReservations(
                mesaReal.getId(),
                EstadoReservacion.CANCELADA,
                reservacion.getStartDateTime(),
                reservacion.getEndDateTime()
        );

        if (!conflictos.isEmpty()) {
            throw new RuntimeException("La mesa ya está reservada en ese horario.");
        }

        // ======================
        // 4) Guardar reservación
        // ======================
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

    @Override
    public void delete(Long id) {
        reservacionRepository.deleteById(id);
    }
}
