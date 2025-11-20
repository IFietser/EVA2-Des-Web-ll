package cl.ipss.saborgourmet.controllers;

import cl.ipss.saborgourmet.models.Mesa;
import cl.ipss.saborgourmet.services.MesaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mesas")
public class MesaViewController {

    private final MesaService mesaService;

    public MesaViewController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public String listar(Model model,
                         @RequestParam(value = "success", required = false) String success,
                         @RequestParam(value = "error", required = false) String error) {

        model.addAttribute("mesas", mesaService.findAll());
        model.addAttribute("successMessage", success);
        model.addAttribute("errorMessage", error);

        return "mesas/list";
    }

    @GetMapping("/nueva")
    public String nuevaMesa(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "mesas/form";
    }

    @GetMapping("/editar/{id}")
    public String editarMesa(@PathVariable Long id, Model model) {
        try {
            Mesa mesa = mesaService.findById(id);
            if (mesa == null) {
                model.addAttribute("errorMessage", "La mesa no existe.");
                return "mesas/list";
            }

            model.addAttribute("mesa", mesa);
            return "mesas/form";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error al cargar la mesa: " + e.getMessage());
            return "mesas/list";
        }
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Mesa mesa, Model model) {
        try {
            mesaService.save(mesa);
            return "redirect:/mesas?success=Mesa guardada exitosamente";

        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("mesa", mesa);
            return "mesas/form";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        try {
            mesaService.delete(id);
            return "redirect:/mesas?success=Mesa eliminada correctamente";

        } catch (Exception e) {
            return "redirect:/mesas?error=No se pudo eliminar la mesa: " + e.getMessage();
        }
    }
}
