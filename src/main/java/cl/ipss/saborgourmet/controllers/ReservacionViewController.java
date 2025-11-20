package cl.ipss.saborgourmet.controllers;

import cl.ipss.saborgourmet.models.Reservacion;
import cl.ipss.saborgourmet.services.ClienteService;
import cl.ipss.saborgourmet.services.MesaService;
import cl.ipss.saborgourmet.services.ReservacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservacionViewController {

    private final ReservacionService reservacionService;
    private final ClienteService clienteService;
    private final MesaService mesaService;

    public ReservacionViewController(
            ReservacionService reservacionService,
            ClienteService clienteService,
            MesaService mesaService
    ) {
        this.reservacionService = reservacionService;
        this.clienteService = clienteService;
        this.mesaService = mesaService;
    }

    @GetMapping("/reservaciones")
    public String listarReservaciones(Model model) {
        model.addAttribute("reservaciones", reservacionService.findAll());
        return "reservaciones/list"; // templates/reservaciones/list.html
    }

    @GetMapping("/reservaciones/nueva")
    public String nuevaReservacion(Model model) {
        model.addAttribute("reservacion", new Reservacion());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("mesas", mesaService.findAll());
        return "reservaciones/form"; // templates/reservaciones/form.html
    }

    @PostMapping("/reservaciones/guardar")
    public String guardarReservacion(@ModelAttribute Reservacion reservacion) {
        reservacionService.crear(reservacion);
        return "redirect:/reservaciones";
    }

    @GetMapping("/reservaciones/editar/{id}")
    public String editarReservacion(@PathVariable Long id, Model model) {
        Reservacion reservacion = reservacionService.findById(id);
        model.addAttribute("reservacion", reservacion);
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("mesas", mesaService.findAll());
        return "reservaciones/form";
    }

    @GetMapping("/reservaciones/eliminar/{id}")
    public String eliminarReservacion(@PathVariable Long id) {
        reservacionService.delete(id);
        return "redirect:/reservaciones";
    }
}
