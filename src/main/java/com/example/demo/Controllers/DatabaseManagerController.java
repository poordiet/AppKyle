package com.example.demo.Controllers;

import com.example.demo.Models.*;
import com.example.demo.Presentation.CustomerPresentation;
import com.example.demo.Presentation.DbMgmtPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import com.example.demo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

@RequestMapping(value = "/dbMgmt", method={RequestMethod.GET, RequestMethod.POST})
@Controller
public class DatabaseManagerController {

    @Autowired
    HardwareTypeRepository hardwareTypeRepository;

    @Autowired
    DbMgmtService dbMgmtService;

    @GetMapping("/dbMgmt")
    public String dbMgmt(Model theModel)
    {

        DbMgmtPresentation dbMgmtPresentation = new DbMgmtPresentation();

        theModel.addAttribute("dbMgmtPresentation",dbMgmtPresentation);


        return("db_mgmt");
    }

    @GetMapping("/showHardwareTypes")
    public String hardwareTypes(Model theModel)
    {

        DbMgmtPresentation dbMgmtPresentation = new DbMgmtPresentation();

        theModel.addAttribute("dbMgmtPresentation",dbMgmtPresentation);

        List<HwType> hwTypes = hardwareTypeRepository.findAll();
        theModel.addAttribute("hwTypes", hwTypes);






        return("hwTypeManage");
    }

    @GetMapping("/showAddHardwareType")
    public String addHardwareTypes(Model theModel)
    {

        DbMgmtPresentation dbMgmtPresentation = new DbMgmtPresentation();

        theModel.addAttribute("dbMgmtPresentation",dbMgmtPresentation);





        return("addHwType");
    }


    @PostMapping("/addNewHardwareTypes")
    public String addNewHardwareType(@ModelAttribute("dbMgmtPresentation") DbMgmtPresentation dbMgmtPresentation, final RedirectAttributes redirectAttributes)
    {


        dbMgmtService.saveHwType(dbMgmtPresentation);



        // Passes the model attribute to the showFormForAssigningContractors method to be used there
        redirectAttributes.addFlashAttribute("dbMgmtPresentation", dbMgmtPresentation);

        return "redirect:/dbMgmt/showHardwareTypes";
    }
}
