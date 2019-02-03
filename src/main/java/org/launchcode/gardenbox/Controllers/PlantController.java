package org.launchcode.gardenbox.Controllers;

import org.launchcode.gardenbox.models.Plant;
import org.launchcode.gardenbox.models.PlantType;
import org.launchcode.gardenbox.models.data.PlantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PlantController {

    @Autowired
    private PlantDao plantDao;

    @RequestMapping(value = "")
    public String index (Model model) {
        model.addAttribute("title", "Plants");
        return "plants/index";
    }

    @RequestMapping(value = "list")
    public String listPlants (Model model) {
        model.addAttribute("title", "Add a Plant");
        return "plants/list";
    }

    @RequestMapping(value = "search")
    public String searchPlants (Model model) {
        model.addAttribute("title", "Search Plants");
        return "plants/search";
    }


    @RequestMapping(value = "create", method= RequestMethod.GET)
    public String displayCreatePlantForm (Model model) {
        model.addAttribute("title", "Create a Plant");
        model.addAttribute(new Plant());
        model.addAttribute("plantTypes", PlantType.values());
        model.addAttribute("plants", plantDao.findAll());
        return "plants/create";
    }

    @RequestMapping(value = "create", method= RequestMethod.POST)
    public String processAddPlantForm (@ModelAttribute @Valid Plant newPlant, @RequestParam int companionsId, @RequestParam PlantType type, Model model, Errors errors) {

        Plant companion = plantDao.findOne(companionsId);
        newPlant.setType(type);


        if (errors.hasErrors()){
            model.addAttribute("title", "Create A Plant");
            model.addAttribute("plants", plantDao.findAll());
            return "plants/create";
        }

        plantDao.save(newPlant);
        return "redirect:";
    }




}