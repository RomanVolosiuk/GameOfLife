package ua.volosiuk.gameoflife.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.volosiuk.gameoflife.model.FieldParams;
import ua.volosiuk.gameoflife.model.FieldValues;
import ua.volosiuk.gameoflife.service.GameService;

@Controller
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping()
    public String index(Model model) {
        // todo: remove object FieldParams, use int instead
        model.addAttribute("fieldParams", new FieldParams());
        return "index";
    }

    @PostMapping()
    public String initField(@ModelAttribute(value="fieldParams") FieldParams fieldParams, Model model) {
        // todo: remove object FieldValuesm use list of lists instead
        FieldValues fieldValues = gameService.initField(fieldParams.getSideLength());
        model.addAttribute("fieldValues", fieldValues);
        return "render";
    }

    @PostMapping("/step")
    public String nextStep(@ModelAttribute(value="fieldValues") FieldValues fieldValues, Model model) {
        FieldValues nextFieldValues = gameService.nextStep(fieldValues);
        model.addAttribute("fieldValues", nextFieldValues);
        return "render";
    }
}
