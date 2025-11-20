package cl.ipss.saborgourmet.controllers;

import cl.ipss.saborgourmet.models.Reservacion;
import cl.ipss.saborgourmet.services.ReservacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservaciones")
@CrossOrigin("*")
public class ReservacionController {

    private final ReservacionService reservacionService;

    public ReservacionController(ReservacionService reservacionService) {
        this.reservacionService = reservacionService;
    }

    @GetMapping
    public List<Reservacion> getAll() {
        return reservacionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservacion> getById(@PathVariable Long id) {
        Reservacion r = reservacionService.findById(id);
        return r != null ? ResponseEntity.ok(r) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Reservacion create(@RequestBody Reservacion reservacion) {
        return reservacionService.crear(reservacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservacion> update(@PathVariable Long id, @RequestBody Reservacion reservacion) {
        reservacion.setId(id);
        Reservacion actualizada = reservacionService.crear(reservacion);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservacionService.cancelar(id);
        return ResponseEntity.noContent().build();
    }
}
