package riccardo.U5W2D3.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/example")
public class ExampleController {

    @GetMapping ("/paramExample/{parameter}")
    public String pathParamExample(@PathVariable String parameter){
        return "il parametro inserito è: " + parameter;
    }

    @GetMapping ("/queryExample")
    public String queryParamExample(@RequestParam String name, @RequestParam String lastName){
        return "i parametri inseriti sono: " + name + " " + lastName;
    }

    @PostMapping ("/postExample")
    public String postExample(@RequestBody String body){
        return "i parametri inseriti sono: " + body;
    }

}
