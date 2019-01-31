package org.launchcode.gardenbox.Controllers;


import org.launchcode.gardenbox.models.data.PlantTypeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("plant-type")
public class PlantTypeController {

    @Autowired
    private PlantTypeDao plantTypeDao;

    @RequestMapping(value="category")
    public String index(Model model){

        return "index";
    }


}
