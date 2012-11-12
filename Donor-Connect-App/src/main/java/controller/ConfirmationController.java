package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfirmationController {

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public String showConfirmationDetail(@ModelAttribute("model") ModelMap modelMap, @RequestParam int id,@RequestParam double donationAmount) {
        modelMap.addAttribute("projectID",id);
        modelMap.addAttribute("donationAmount", donationAmount);

        return "confirmation";
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public void persistDonationToProject(@ModelAttribute("model") ModelMap modelMap, @RequestParam int id,@RequestParam double donationAmount) {

    }
}
