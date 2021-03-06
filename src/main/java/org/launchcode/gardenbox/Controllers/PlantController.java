package org.launchcode.gardenbox.Controllers;

import org.launchcode.gardenbox.models.GardenBox;
import org.launchcode.gardenbox.models.Plant;
import org.launchcode.gardenbox.models.PlantType;
import org.launchcode.gardenbox.models.data.PlantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlantController {

    @Autowired
    private PlantDao plantDao;

    GardenBox gardenBox = new GardenBox();

    @RequestMapping(value = "")
    public String index (Model model) {


        model.addAttribute("title", "Plants");
        model.addAttribute("plants", gardenBox.getPlants());

        //List<Plant> allCompanionPlants = gardenBox.getCompanionPlants();
        //List<Plant> allAvoidedPlants = gardenBox.getAvoidedPlants();

        model.addAttribute("companionPlants",gardenBox.getCompanionPlants());
        model.addAttribute("avoidedPlants", gardenBox.getAvoidedPlants());

        return "plants/index";
    }

    @RequestMapping(value = "list", method=RequestMethod.GET)
    public String listPlants (Model model) {
        model.addAttribute("title", "Add a Plant");
        model.addAttribute("plants", plantDao.findAll());


        return "plants/list";
    }

    @RequestMapping(value = "list", method=RequestMethod.POST)
    public String listPlants (Model model, @RequestParam(value = "plantId", required=false, defaultValue = "0") int plantId) {

        if (plantId==0){
            return "plants/index";
        }

        Plant newPlant = plantDao.findOne(plantId);
        gardenBox.addPlant(newPlant);
        return "redirect:";
    }

    @RequestMapping(value="remove", method=RequestMethod.GET)
    public String removePlant(Model model){
        model.addAttribute("title", "Remove Plants from Garden Box");
        model.addAttribute("plants", gardenBox.getPlants());
        return "plants/remove";
    }

    @RequestMapping(value="remove", method=RequestMethod.POST)
    public String removePlant(Model model, @RequestParam(value= "plantId", required=false, defaultValue= "0") int plantId){
        Plant newPlant = plantDao.findOne(plantId);
        for (Plant plant:gardenBox.getPlants()){
            if(plant.getId()==plantId){
                gardenBox.removePlant(plant);
                break;
            }

            }
        gardenBox.clearAvoidedPlants();
        gardenBox.clearCompanionPlants();;
        return "redirect:";
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
    public String processAddPlantForm (@ModelAttribute @Valid Plant newPlant, @RequestParam(value = "companionsId", required=false, defaultValue = "0") int [] companionsId, @RequestParam(value = "avoidedsId", required=false, defaultValue = "0") int [] avoidedsId, @RequestParam PlantType type, Model model, Errors errors) {


        newPlant.setType(type);
        for (int companionId : companionsId) {
            Plant companionPlant = plantDao.findOne(companionId);
            newPlant.addCompanionPlants(companionPlant);
        }
        for (int avoidedId : avoidedsId) {
            Plant avoidedPlant = plantDao.findOne(avoidedId);
            newPlant.addAvoidedPlants(avoidedPlant);
        }


        if (errors.hasErrors()){
            model.addAttribute("title", "Create A Plant");
            model.addAttribute("plants", plantDao.findAll());
            return "plants/create";
        }

        plantDao.save(newPlant);
        return "redirect:";
    }

    @RequestMapping(value="clear", method = RequestMethod.POST)
    public String clearGardenBox (){
        gardenBox.clearGardenBox();
        gardenBox.clearAvoidedPlants();
        gardenBox.clearCompanionPlants();;
        return "redirect:";
    }





}
