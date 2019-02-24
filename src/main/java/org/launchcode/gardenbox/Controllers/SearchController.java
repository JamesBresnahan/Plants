package org.launchcode.gardenbox.Controllers;

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
@RequestMapping("search")
public class SearchController {

    @Autowired
    private PlantDao plantDao;

    @Autowired
    private GardenBoxDao gardenBoxDao;

    @RequestMapping(value = "")
    public String index(Model model){

        model.addAttribute("title", "search");
        model.addAttribute("plants",plantDao.findAll());
        return "search/index";

    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public String index(Model model, @RequestParam String name){

        List<Plant> plants = plantDao.findByName(name);
        model.addAttribute("plants", plants);
        return "search/index";
    }





}
