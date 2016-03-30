/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Administrator
 */
public class Report {
    private Integer month;
    private Double planValue;
    private Double actuallyValue;
    private Double percentage;
    
    public Report(){
        
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getPlanValue() {
        return planValue;
    }

    public void setPlanValue(Double planValue) {
        this.planValue = planValue;
    }

    public Double getActuallyValue() {
        return actuallyValue;
    }

    public void setActuallyValue(Double actuallyValue) {
        this.actuallyValue = actuallyValue;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
