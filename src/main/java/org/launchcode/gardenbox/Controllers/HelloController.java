package org.launchcode.gardenbox.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.RequestWrapper;

@Controller
public class HelloController {

    @RequestMapping(value = "")
    public String index (Model model) {

        return "fragments";
    }

}
