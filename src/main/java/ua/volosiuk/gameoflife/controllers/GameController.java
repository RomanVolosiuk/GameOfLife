package ua.volosiuk.gameoflife.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.volosiuk.gameoflife.model.Structure;

@Controller
public class GameController {
    Structure structure;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("structure", structure);
        return "index";
    }

    @PostMapping()
    public String setEdgeLength(@RequestParam ("edgeLength") int edgeLength, Model model) { // requestParam
        Structure structure = new Structure(edgeLength);
        model.addAttribute("structure", structure);
        return "redirect:/renderpage";
    }

    @GetMapping("/renderpage")
    public String renderPage(Model model) {
        model.addAttribute("structure", structure);
        return "renderpage";
    }
}
