package cl.ipss.saborgourmet.repositories;

import cl.ipss.saborgourmet.models.Reservacion;
import cl.ipss.saborgourmet.models.EstadoReservacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservacionRepository extends JpaRepository<Reservacion, Long> {

    @Query("SELECT r FROM Reservacion r " +
           "WHERE r.mesa.id = ?1 AND r.estado != ?2 AND " +
           "((r.startDateTime <= ?3 AND r.endDateTime > ?3) OR " +
           "(r.startDateTime < ?4 AND r.endDateTime >= ?4))")
    List<Reservacion> findConflictingReservations(
            Long mesaId,
            EstadoReservacion estado,
            LocalDateTime start,
            LocalDateTime end
    );

    List<Reservacion> findByClienteId(Long clienteId);

    List<Reservacion> findByEstadoNot(EstadoReservacion estado);
}
