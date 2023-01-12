package ua.volosiuk.gameoflife.controllers;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.volosiuk.gameoflife.model.FieldParams;
import ua.volosiuk.gameoflife.model.FieldValues;
import ua.volosiuk.gameoflife.service.GameService;

@Setter
@Controller
@AllArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("fieldParams", new FieldParams());

        return "index";
    }

    @PostMapping()
    public String initField(@ModelAttribute(value="fieldParams") FieldParams fieldParams, Model model) {
        System.out.println("side length = " + fieldParams.getSideLength());
        FieldValues fieldValues = gameService.initField(fieldParams.getSideLength());
        model.addAttribute("fieldValues", fieldValues);
        fieldValues.getValues().forEach(System.out::println);
        return "render";
    }

    @PostMapping("/step")
    public String nextStep(@ModelAttribute FieldValues fieldValues, Model model) {
        fieldValues.getValues().forEach(System.out::println);
        FieldValues nextFieldValues = gameService.nextStep(fieldValues);
        System.out.println("nextFieldValues = " + nextFieldValues);
        model.addAttribute("fieldValues", nextFieldValues);
        System.out.println("CONTROLLER /step3");
        return "render";
    }
}
