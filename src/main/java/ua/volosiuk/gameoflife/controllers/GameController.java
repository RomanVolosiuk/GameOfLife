package ua.volosiuk.gameoflife.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.volosiuk.gameoflife.service.Structure;

@Controller
public class GameController {
    Structure structure;

    @GetMapping("")
    public String index(@ModelAttribute("structure") Structure structure) {
        return "index";
    }

    @PostMapping("/")
    public String setEdgeLength(@ModelAttribute("structure") Structure structure, Model model) {
        this.structure = structure;
        model.addAttribute("structure", structure.getEdgeLength());
        return "redirect:/renderpage";
    }

    @GetMapping("/renderpage")
    public String renderPage(Model model) {
        model.addAttribute("structure", structure);
        return "renderpage";
    }
}
