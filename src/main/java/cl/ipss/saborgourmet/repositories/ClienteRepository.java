package cl.ipss.saborgourmet.repositories;

import cl.ipss.saborgourmet.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Puedes agregar consultas personalizadas si es necesario, ej. buscar por email
    Cliente findByEmail(String email);
}