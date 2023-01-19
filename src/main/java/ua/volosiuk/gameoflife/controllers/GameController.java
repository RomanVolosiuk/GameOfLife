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

@Setter
@Controller
@AllArgsConstructor
public class GameController {

    private static final TypeReference<List<List<Boolean>>> PAYLOAD_TYPE = new TypeReference<>() {
    };

    private final GameService gameService;

    @GetMapping()
    public String index() {
        return "index";
    }

    @PostMapping()
    public String initField(@RequestParam int sideLength, Model model) {
        System.out.println("side length = " + sideLength);
        List<List<Boolean>> values = gameService.initField(sideLength);
        model.addAttribute("values", values);
        values.forEach(System.out::println);
        return "render";
    }

    @PostMapping("/step")
    public String nextStep(@RequestParam(name = "values") String valuesString, Model model) throws JsonProcessingException {
        System.out.println(valuesString);
        ObjectMapper mapper = new ObjectMapper();
        List<List<Boolean>> values = mapper.readValue(valuesString, PAYLOAD_TYPE);
//        values.forEach(System.out::println);
//        fieldValues.getValues().forEach(System.out::println);
//        FieldValues nextFieldValues = gameService.nextStep(fieldValues);
//        System.out.println("nextFieldValues = " + nextFieldValues);
//        model.addAttribute("fieldValues", nextFieldValues);
        model.addAttribute("values", values);
        System.out.println("CONTROLLER /step3");
        return "render";
    }
}
