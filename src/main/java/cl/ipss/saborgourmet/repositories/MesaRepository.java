package cl.ipss.saborgourmet.repositories;

import cl.ipss.saborgourmet.models.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
    // Buscar mesas disponibles (por asientos, por ejemplo)
    List<Mesa> findByAsientosGreaterThanEqualAndDisponibleTrue(int asientos);
}