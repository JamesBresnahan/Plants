package org.launchcode.gardenbox.Controllers;

import org.launchcode.gardenbox.models.PlantType;
import org.launchcode.gardenbox.models.data.PlantDao;
import org.launchcode.gardenbox.models.Plant;
import org.launchcode.gardenbox.models.forms.UpdatePlantForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class SearchController {

    @Autowired
    private PlantDao plantDao;

    @RequestMapping(value = "search")
    public String index(Model model){

        //model.addAttribute("title", "search");
        model.addAttribute("plants",plantDao.findAll());
        return "search/index";

    }

    @RequestMapping(value="form")
    public String search(Model model){

        model.addAttribute("title", "search");
        return "search/form";
    }

    @RequestMapping(value="form", method = RequestMethod.POST)
    public String index(Model model, @RequestParam String name){

        List<Plant> plants = plantDao.findByName(name);
        model.addAttribute("plants", plants);
        return "search/index";
    }

    @RequestMapping(value="vegetable")
    public String listVegetable(Model model){

        List<Plant> plants = plantDao.findByType(PlantType.VEGETABLE);
        model.addAttribute("plants", plants);
        return "search/index";
    }

    @RequestMapping(value="fruit")
    public String listFruit(Model model){

        List<Plant> plants = plantDao.findByType(PlantType.FRUIT);
        model.addAttribute("plants", plants);
        return "search/index";
    }

    @RequestMapping(value="herb")
    public String listHerb(Model model){

        List<Plant> plants = plantDao.findByType(PlantType.HEB);
        model.addAttribute("plants", plants);
        return "search/index";
    }

    @RequestMapping(value="update/{plantId}", method= RequestMethod.GET)
    public String displayUpdate (Model model, @PathVariable int plantId) {

        Plant plant = plantDao.findOne(plantId);

        UpdatePlantForm form = new UpdatePlantForm(plantId);
        form.setPlant(plant);

        model.addAttribute("title", "Update " + plant.getName());
        model.addAttribute("form", form);
        model.addAttribute("plants", plantDao.findAll());
        return "search/update";

    }

    @RequestMapping(value="update/update", method= RequestMethod.POST)
    public Object processUpdate (Model model, @ModelAttribute @Valid UpdatePlantForm form, Errors errors, @RequestParam int plantId, @RequestParam(value = "companionsId", required=false, defaultValue = "0") int [] companionsId, @RequestParam(value = "avoidedsId", required=false, defaultValue = "0") int [] avoidedsId) {

        Plant plant = plantDao.findOne(form.getPlantId());
        plant.clearAvoidedPlants();
        plant.clearCompanionPlants();

        for (int companionId : companionsId) {
            Plant companionPlant = plantDao.findOne(companionId);
            plant.addCompanionPlants(companionPlant);
        }
        for (int avoidedId : avoidedsId) {
            Plant avoidedPlant = plantDao.findOne(avoidedId);
            plant.addAvoidedPlants(avoidedPlant);
        }


        if (errors.hasErrors()){
            model.addAttribute("title", "Update " + plant.getName());
            model.addAttribute("plants", plantDao.findAll());
            return "plants/update/{plantId}";
        }
        plantDao.save(plant);
        return "redirect:/search";

    }


}
