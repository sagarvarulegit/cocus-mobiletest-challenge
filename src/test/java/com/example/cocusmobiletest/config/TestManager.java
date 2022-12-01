package com.example.cocusmobiletest.config;

import com.example.cocusmobiletest.pageobjects.DashBoardPO;
import com.example.cocusmobiletest.pageobjects.NewNotePO;
import com.example.cocusmobiletest.pageobjects.StatisticsPO;


public class TestManager {
    private DashBoardPO dashBoardPO;
    private NewNotePO newNotePO;
    private StatisticsPO statisticsPO;

    public TestManager() {
        dashBoardPO = new DashBoardPO(DriverManager.appiumDriver);
        newNotePO = new NewNotePO(DriverManager.appiumDriver);
        statisticsPO = new StatisticsPO(DriverManager.appiumDriver);
    }

    public DashBoardPO getDashBoardPO() {
        return dashBoardPO;
    }

    public NewNotePO getNewNotePO() {
        return newNotePO;
    }

    public StatisticsPO getStatisticsPO() {
        return statisticsPO;
    }

    public void setStatisticsPO(StatisticsPO statisticsPO) {
        this.statisticsPO = statisticsPO;
    }

    public void setDashBoardPO(DashBoardPO dashBoardPO) {
        this.dashBoardPO = dashBoardPO;
    }

    public void setNewNotePO(NewNotePO newNotePO) {
        this.newNotePO = newNotePO;
    }

}
