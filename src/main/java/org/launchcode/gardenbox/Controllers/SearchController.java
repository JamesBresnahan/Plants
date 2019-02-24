package org.launchcode.gardenbox.Controllers;

import org.launchcode.gardenbox.models.PlantType;
import org.launchcode.gardenbox.models.data.GardenBoxDao;
import org.launchcode.gardenbox.models.data.PlantDao;
import org.launchcode.gardenbox.models.Plant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("")
public class SearchController {

    @Autowired
    private PlantDao plantDao;

    @Autowired
    private GardenBoxDao gardenBoxDao;

    @RequestMapping(value = "search")
    public String index(Model model){

        model.addAttribute("title", "search");
        model.addAttribute("plants",plantDao.findAll());
        return "search/index";

    }

    @RequestMapping(value="search/form")
    public String search(Model model){

        model.addAttribute("title", "search");
        return "search/form";
    }

    @RequestMapping(value="search/form", method = RequestMethod.POST)
    public String index(Model model, @RequestParam String name){

        List<Plant> plants = plantDao.findByName(name);
        model.addAttribute("title", "search");
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







}
