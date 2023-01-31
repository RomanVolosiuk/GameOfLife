package ua.volosiuk.gameoflife.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.volosiuk.gameoflife.service.GameService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Controller
@AllArgsConstructor
public class GameController {

    public static final Logger logger = Logger.getLogger(GameController.class.getName());

    private static final TypeReference<List<List<Boolean>>> PAYLOAD_TYPE = new TypeReference<>() {};

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
    public String nextStep(@RequestParam(name = "values") String valuesString, Model model) throws JsonProcessingException {
        logger.log(Level.INFO, "controller / nextStep() started");
        ObjectMapper mapper = new ObjectMapper();
        List<List<Boolean>> values = mapper.readValue(valuesString, PAYLOAD_TYPE);
        values = gameService.nextStep(values);
        model.addAttribute("values", values);

        return "render";
    }
}
