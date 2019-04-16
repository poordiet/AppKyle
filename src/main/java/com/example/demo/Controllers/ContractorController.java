package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.CustomerPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/contractors")
@Controller
public class ContractorController {

    @Autowired
    ContractorRepository contractorRepository;


    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    StateProvinceRepository stateProvinceRepository;

    @Autowired
    StateProvinceService stateProvinceService;

    @Autowired
    ContractorService contractorService;

    @Autowired
    ContractorStatusRepository contractorStatusRepository;

    @Autowired
    ContractorTypeRepository contractorTypeRepository;

    @Autowired
    AccessLevelRepository accessLevelRepository;

    @Autowired
    SkillRepository skillRepository;

    @GetMapping("/search")
    public String searchContractor(Model theModel)
    {

        List<ServiceOrderPresentation> serviceOrderPresentations = contractorService.getContractors(contractorService.findAll());

        theModel.addAttribute("serviceOrderPresentations",serviceOrderPresentations);

        return("search_contractor");
    }

    @GetMapping("/showAddNewContractor")
    public String addContractorForm(Model theModel)
    {

        ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();

        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

        List<Country> countries = countryService.findAll();
        theModel.addAttribute("countries", countries);

        List<StateProvince> stateProvinces = stateProvinceService.findAll();
        theModel.addAttribute("states", stateProvinces);

        List<ContractorStatus> contractorStatuses = contractorStatusRepository.findAll();
        theModel.addAttribute("contractorStatuses", contractorStatuses);

        List<ContractorType> contractorTypes = contractorTypeRepository.findAll();
        theModel.addAttribute("contractorTypes", contractorTypes);

        List<AccessLevel> accessLevels = accessLevelRepository.findAll();
        theModel.addAttribute("accessLevels", accessLevels);

        List<Skill> skills = skillRepository.findAll();
        theModel.addAttribute("skills", skills);



        return("add_contractor");
    }

    @PostMapping("/addNewContractor")
    public String addNewContractor(@ModelAttribute("serviceOrderPresentation") ServiceOrderPresentation serviceOrderPresentation, final RedirectAttributes redirectAttributes)
    {
       // System.out.println(customerPresentation.getCustSiteEmail());
        System.out.println(serviceOrderPresentation.getContractorFname());
        for(Skill skill:serviceOrderPresentation.getSkills()) {
            System.out.println(skill.getSkill());
        }


        // Save
        contractorService.saveContractorForm(serviceOrderPresentation);



        // Passes the model attribute to the showFormForAssigningContractors method to be used there
        redirectAttributes.addFlashAttribute("serviceOrderPresentation", serviceOrderPresentation);

        return "redirect:/contractors/search";
    }


    @GetMapping("/contractorProfile")
    public String showContractorProfile(@RequestParam("contractorId") int contractorId, Model theModel)
    {
        ServiceOrderPresentation serviceOrderPresentation = contractorService.getServiceOrderPresentationById(contractorId);

        theModel.addAttribute("serviceOrderPresentation",serviceOrderPresentation);

//        List<Skill> skills = serviceOrderPresentation.getSkills();
//        theModel.addAttribute("skills", skills);

        return("contractor_profile");
    }


}
