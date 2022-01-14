package org.springframework.samples.petclinic.feeding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.pet.PetValidator;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@Controller
public class FeedingController {


    private static final String VIEW_FEEDING_CREATE_FORM = "feedings/createOrUpdateFeedingForm";
    private static final String VIEW_WELCOME = "welcome";

    private final FeedingService feedingService;


    @Autowired
    public FeedingController(FeedingService fService) {
        this.feedingService = fService;

    }


    @ModelAttribute("types")
    public Collection<FeedingType> populateFeedingsTypes() {

        Collection<FeedingType> all_feedingtypes = this.feedingService.getAllFeedingTypes();
        log.info("all_feedingtypes size: ");
        log.info(String.valueOf(all_feedingtypes.size()));

        return all_feedingtypes;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


    @GetMapping(value = "/feeding/create")
    public String createFeeding( ModelMap model)
    {
        log.info("createFeeding");
        System.out.println("createFeeding");
        Feeding feeding = new Feeding();
        model.put("feeding", feeding);
        return VIEW_FEEDING_CREATE_FORM;
    }

    @PostMapping(value = "/feeding/create")
    public String processFeeding(@Valid Feeding feeding, BindingResult result)
    {
        log.info("processFeeding");

        if(result.hasErrors())
        {
            log.error("ERROR:" + result.getFieldErrors());
            return VIEW_FEEDING_CREATE_FORM;
        }
        try
        {
            log.info("saving feeding");
            this.feedingService.save(feeding);
            log.info("saved feeding");

        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }

        return VIEW_WELCOME;
    }




    
}
