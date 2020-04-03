package com.zemoso.githubtask.assignment.controllers;

import com.zemoso.githubtask.assignment.pojos.MathResponse;
import com.zemoso.githubtask.assignment.services.CalculationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("calculate")
public class MathController {

    private final CalculationService calculationService;

    public MathController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping("double")
    public @ResponseBody MathResponse calculateQuery(@RequestParam(required = false) String query){
        MathResponse mathResponse = new MathResponse();
        mathResponse.setValueDouble(calculationService.calculateDouble(query));
        return mathResponse;
    }

// hello world shanmukha

}
