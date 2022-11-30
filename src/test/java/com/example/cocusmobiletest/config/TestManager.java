package com.example.cocusmobiletest.config;

import com.example.cocusmobiletest.pageobjects.DashBoardPO;
import com.example.cocusmobiletest.pageobjects.NewNotePO;


public class TestManager {
    private DashBoardPO dashBoardPO;
    private NewNotePO newNotePO;

    public TestManager() {
        dashBoardPO = new DashBoardPO(DriverManager.appiumDriver);
        newNotePO = new NewNotePO(DriverManager.appiumDriver);
    }

    public DashBoardPO getDashBoardPO() {
        return dashBoardPO;
    }

    public NewNotePO getNewNotePO() {
        return newNotePO;
    }

}
