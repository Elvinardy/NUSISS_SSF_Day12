package nus.iss.workshop.Day._SpringBoot.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import nus.iss.workshop.Day._SpringBoot.exception.RandomNumberException;
import nus.iss.workshop.Day._SpringBoot.model.Generate;

@Controller
public class GenerateController {
    private Logger logger = LoggerFactory.getLogger(GenerateController.class);
    
    @GetMapping("/")  //create a mapping at generate
    public String showGenerateForm(Model model) {
        Generate generate = new Generate();
        //generate.setNumberVal(10000);
        model.addAttribute("generate", generate);
        return "generate";
    }
    
    @PostMapping("/generate")
    public String generateNumbers(@ModelAttribute Generate generate, Model model) {
        logger.info("From the form " + generate.getNumberVal());
        int numberOfRandomNumbers = generate.getNumberVal();
        if(numberOfRandomNumbers > 10) {
            model.addAttribute("errorMessage", "Exceeded more than 10 value");
            return "error";
        }
        String[] imgNumbers = {
            "number1.jpg","number2.jpg","number3.jpg","number4.jpg","number5.jpg",
            "number6.jpg","number7.jpg","number8.jpg","number9.jpg","number10"};

            List<String> selectedImg = new ArrayList<>();
            Random rand = new Random();
            Set<Integer> uniqueGeneratedNumbers = new LinkedHashSet<Integer>();
            while(uniqueGeneratedNumbers.size() < numberOfRandomNumbers) {
                Integer resultofRandomNumbers = rand.nextInt(generate.getNumberVal() + 1);
                uniqueGeneratedNumbers.add(resultofRandomNumbers);
            }
            Iterator<Integer> it = uniqueGeneratedNumbers.iterator();
            Integer currentElem = null;
            while(it.hasNext()) {
                currentElem = it.next();
                logger.info("CurrentElem >" + currentElem);
                selectedImg.add(imgNumbers[currentElem.intValue()]);
            }
            model.addAttribute("randNumsResult", selectedImg.toArray());
        return "result";
    }
}
