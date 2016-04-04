/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.OrdersFacade;
import ejb.PlanFacade;
import entities.Month;
import entities.Plan;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import util.Report;

@Named(value = "planReportManagedBean")
@ViewScoped
public class PlanReportManagedBean implements Serializable {

    @Inject
    private PlanFacade planEJB;
    @Inject
    private OrdersFacade orderEJB;

    private Plan plan = new Plan();
    private ArrayList<Month> monthList = addMonths();
    private Integer id;
    private ArrayList<Double> actuallyValueList = new ArrayList<>();
    private ArrayList<Double> percentageList = new ArrayList<>();
    private ArrayList<Report> report = new ArrayList<>();
    private BarChartModel barModel;

    public PlanReportManagedBean() {
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public ArrayList<Month> getMonthList() {
        return monthList;
    }

    public void setMonthList(ArrayList<Month> monthList) {
        this.monthList = monthList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Double> getActuallyValueList() {
        return actuallyValueList;
    }

    public void setActuallyValueList(ArrayList<Double> actuallyValueList) {
        this.actuallyValueList = actuallyValueList;
    }

    public ArrayList<Double> getPercentageList() {
        return percentageList;
    }

    public void setPercentageList(ArrayList<Double> percentageList) {
        this.percentageList = percentageList;
    }

    public ArrayList<Report> getReport() {
        return report;
    }

    public void setReport(ArrayList<Report> report) {
        this.report = report;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    private ArrayList<Month> addMonths() {
        ArrayList<Month> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Month m = new Month();
            m.setMonth(i + 1);
            list.add(m);
        }
        return list;
    }

    public void doPlanReport() {
        monthList = new ArrayList<>();
        monthList = addMonths();
        actuallyValueList = new ArrayList<>();
        percentageList = new ArrayList<>();
        report = new ArrayList<>();
        
        plan = planEJB.find(id);
        plan.setMonthList(planEJB.getMonthList(plan));

        for (int i = 0; i < 12; i++) {
            if(monthList.get(i)!=null){
                monthList.get(i).setValue(plan.getMonthList().get(i).getValue());
            }           
        }
        int year = plan.getPlanYear();
        actuallyValueList = orderEJB.doReportOrders(year);
        for (int i = 0; i < 12; i++) {
            Double p = (actuallyValueList.get(i) / monthList.get(i).getValue());
            percentageList.add(p);
        }
        for (int i = 0; i < 12; i++) {
            Report r = new Report();
            r.setMonth(monthList.get(i).getMonth());
            r.setPlanValue(monthList.get(i).getValue());
            r.setActuallyValue(actuallyValueList.get(i));
            r.setPercentage(percentageList.get(i));
            report.add(r);
        }
        createBarModels();
    }

    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Month");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Value");
        yAxis.setMin(0);

        yAxis.setMax((maxValue() * 1.5) / 1000000.00);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries plans = new ChartSeries();
        plans.setLabel("Plan");
        for (int i = 0; i < 12; i++) {
            plans.set(i + 1, ((double) monthList.get(i).getValue()) / 1000000.00);
        }
        ChartSeries actually = new ChartSeries();
        actually.setLabel("Actually");
        for (int i = 0; i < 12; i++) {
            actually.set(i + 1, ((double) actuallyValueList.get(i)) / 1000000.00);
        }
        model.addSeries(plans);
        model.addSeries(actually);
        return model;
    }

    private double maxValue() {
        double max = 0.0;
        for (Month m : monthList) {
            if (max < m.getValue()) {
                max = m.getValue();
            }
        }
        for (Double a : actuallyValueList) {
            if (max < a) {
                max = a;
            }
        }
        return max;
    }

    public String doGetTotalPlan() {
        Double sumPlan = 0.0;
        for (Month m : monthList) {
            sumPlan += m.getValue();
        }
        NumberFormat f = NumberFormat.getNumberInstance();
        return f.format(sumPlan);
    }

    public String doGetTotalActually() {
        Double sumTotal = 0.0;
        for (Double d : actuallyValueList) {
            sumTotal += d;
        }
        NumberFormat f = NumberFormat.getNumberInstance();
        return f.format(sumTotal);
    }

    public String doGetTotalPercent() {
        NumberFormat f = NumberFormat.getPercentInstance();
        Double sumPlan = 0.0;
        for (Month m : monthList) {
            sumPlan += m.getValue();
        }
        Double sumTotal = 0.0;
        for (Double d : actuallyValueList) {
            sumTotal += d;
        }
        Double percent = sumTotal / sumPlan;
        return f.format(percent);
    }

}
