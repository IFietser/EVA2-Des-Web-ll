package cl.ipss.saborgourmet.controllers;

import cl.ipss.saborgourmet.models.Reservacion;
import cl.ipss.saborgourmet.services.ClienteService;
import cl.ipss.saborgourmet.services.MesaService;
import cl.ipss.saborgourmet.services.ReservacionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservaciones")
public class ReservacionViewController {

    private final ReservacionService reservacionService;
    private final ClienteService clienteService;
    private final MesaService mesaService;

    public ReservacionViewController(ReservacionService reservacionService,
                                     ClienteService clienteService,
                                     MesaService mesaService) {
        this.reservacionService = reservacionService;
        this.clienteService = clienteService;
        this.mesaService = mesaService;
    }

    @GetMapping
    public String listar(Model model,
                         @RequestParam(value = "success", required = false) String success,
                         @RequestParam(value = "error", required = false) String error) {

        model.addAttribute("reservaciones", reservacionService.findAll());
        model.addAttribute("successMessage", success);
        model.addAttribute("errorMessage", error);
        return "reservaciones/list";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("reservacion", new Reservacion());
        model.addAttribute("clientes", clienteService.findAll());
        model.addAttribute("mesas", mesaService.findAll());
        return "reservaciones/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Reservacion reservacion, Model model) {
        try {
            reservacionService.crear(reservacion);
            return "redirect:/reservaciones?success=Reservación creada exitosamente";

        } catch (Exception e) {
            model.addAttribute("reservacion", reservacion);
            model.addAttribute("clientes", clienteService.findAll());
            model.addAttribute("mesas", mesaService.findAll());

            model.addAttribute("errorMessage", e.getMessage());
            return "reservaciones/form";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        try {
            reservacionService.delete(id);
            return "redirect:/reservaciones?success=Reservación eliminada";

        } catch (Exception e) {
            return "redirect:/reservaciones?error=" + e.getMessage();
        }
    }
}
