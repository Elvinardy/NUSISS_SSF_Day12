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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import nus.iss.workshop.Day._SpringBoot.exception.RandomNumberException;
import nus.iss.workshop.Day._SpringBoot.model.Generate;

@Controller
public class GenerateController {
    private Logger logger = LoggerFactory.getLogger(GenerateController.class);
    // end point to forward to generate.html
    // root of the path is set therefore when user access this
    // web app will alwys default to this method
    @GetMapping("/")  //create a mapping at generate
    public String showGenerateForm(Model model) {
        // instantiate the Generate class where it represent the form
        // of the generate html page
        Generate generate = new Generate();
        //set the generate class into the page scope of the generate html
        //generate.setNumberVal(10000);
        model.addAttribute("generate", generate);
        // forward this endpoint to the page 'generate.html'
        return "generate"; 
    }
    // this endpoint is to handle the form submission
    @PostMapping("/generate")
    public String generateNumbers(@ModelAttribute Generate generate, Model model) {
        logger.info("From the form " + generate.getNumberVal());
        // capture the number of times that the user inputon the random image page
        int numberOfRandomNumbers = generate.getNumberVal();
        if(numberOfRandomNumbers > 10) {
            // throw new RandomNumberException();
            model.addAttribute("errorMessage", "Exceeded more than 10 value");
            return "error";
        }
        String[] imgNumbers = {
            "number1.jpg","number2.jpg","number3.jpg","number4.jpg","number5.jpg",
            "number6.jpg","number7.jpg","number8.jpg","number9.jpg","number10"};

            List<String> selectedImg = new ArrayList<String>();
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
