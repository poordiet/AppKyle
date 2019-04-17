package com.example.demo.Service;


import com.example.demo.Models.*;
import com.example.demo.Presentation.DbMgmtPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.HardwareTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class DbMgmtService {

    @Autowired
    HardwareTypeRepository hardwareTypeRepository;

    public void saveHwType(DbMgmtPresentation dbMgmtPresentation){

        HwType hwType = new HwType();
        hwType.setHwType(dbMgmtPresentation.getHwType());
        hwType.setHwTypeDesc(dbMgmtPresentation.getHwTypeDesc());
        hardwareTypeRepository.save(hwType);


        }













}
