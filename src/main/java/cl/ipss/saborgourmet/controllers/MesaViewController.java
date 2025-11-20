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
    public String listar(Model model) {
        model.addAttribute("mesas", mesaService.findAll());
        return "mesas/list"; 
    }

    @GetMapping("/nueva")
    public String nuevaMesa(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "mesas/form";
    }

    @GetMapping("/editar/{id}")
    public String editarMesa(@PathVariable Long id, Model model) {
        model.addAttribute("mesa", mesaService.findById(id));
        return "mesas/form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Mesa mesa) {
        mesaService.save(mesa);
        return "redirect:/mesas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        mesaService.delete(id);
        return "redirect:/mesas";
    }
}
