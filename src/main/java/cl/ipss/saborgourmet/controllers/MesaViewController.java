package cl.ipss.saborgourmet.controllers;

import cl.ipss.saborgourmet.services.MesaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MesaViewController {

    private final MesaService mesaService;

    public MesaViewController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping("/mesas")
    public String listarMesas(Model model) {
        model.addAttribute("mesas", mesaService.findAll());
        return "mesas/list"; // templates/mesas/list.html
    }

    @GetMapping("/mesas/nueva")
    public String nuevaMesa(Model model) {
        model.addAttribute("mesa", new cl.ipss.saborgourmet.models.Mesa());
        return "mesas/form"; // templates/mesas/form.html
    }
}
