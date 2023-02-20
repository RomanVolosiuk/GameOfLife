package ua.volosiuk.gameoflife.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.volosiuk.gameoflife.service.GameService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@AllArgsConstructor
public class GameController {

    public static final Logger logger = Logger.getLogger(GameController.class.getName());

    private final GameService gameService;

    @GetMapping()
    public String index() {
        logger.log(Level.INFO, "controller / index() started");
        return "index";
    }

    @PostMapping()
    public String initField(@RequestParam int sideLength, Model model) {
        logger.log(Level.INFO, "controller / initField() started");
        logger.log(Level.INFO, "side length = " + sideLength);
        List<List<Boolean>> values = gameService.initListList(sideLength);
        model.addAttribute("values", values);
        return "render";
    }

    @PostMapping("/step")
    public String nextStep(@RequestParam(name = "values") List<List<Boolean>> values, Model model) {
        List<List<Boolean>> newValues = gameService.nextStep(values);
        model.addAttribute("values", newValues);
        return "render";
    }
}
