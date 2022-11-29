package com.example.cocusmobiletest.config;

import com.example.cocusmobiletest.pageobjects.DashBoardPO;
import com.example.cocusmobiletest.pageobjects.NewNotePO;
import com.example.cocusmobiletest.stepdefinitions.Hooks;

public class TestManager {
    private DashBoardPO dashBoardPO;
    private NewNotePO newNotePO;

    public TestManager() {
        dashBoardPO = new DashBoardPO(Hooks.appiumDriver);
        newNotePO = new NewNotePO(Hooks.appiumDriver);
    }

    public DashBoardPO getDashBoardPO() {
        return dashBoardPO;
    }

    public NewNotePO getNewNotePO() {
        return newNotePO;
    }

}
