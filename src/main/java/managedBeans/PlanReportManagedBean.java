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
import java.util.ArrayList;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import util.Report;

@Named(value = "planReportManagedBean")
@RequestScoped
public class PlanReportManagedBean {

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
        plan = planEJB.find(id);
        for (int i = 0; i < 12; i++) {
            monthList.get(i).setValue(plan.getMonthList().get(i).getValue());
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
            plans.set(i+1, ((double) monthList.get(i).getValue())/1000000.00);
        }
//        plans.set("1", (double) monthList.get(0).getValue());
//        plans.set("2", (double) monthList.get(1).getValue());
//        plans.set("3", (double) monthList.get(2).getValue());
//        plans.set("4", (double) monthList.get(3).getValue());
//        plans.set("5", (double) monthList.get(4).getValue());
//        plans.set("6", (double) monthList.get(5).getValue());
//        plans.set("7", (double) monthList.get(6).getValue());
//        plans.set("8", (double) monthList.get(7).getValue());
//        plans.set("9", (double) monthList.get(8).getValue());
//        plans.set("10", (double) monthList.get(9).getValue());
//        plans.set("11", (double) monthList.get(10).getValue());
//        plans.set("12", (double) monthList.get(11).getValue());

        ChartSeries actually = new ChartSeries();
        actually.setLabel("Actually");
        for (int i = 0; i < 12; i++) {
            actually.set(i+1, ((double) actuallyValueList.get(i))/1000000.00);
        }
//        actually.set("1", (double) actuallyValueList.get(0));
//        actually.set("2", (double) actuallyValueList.get(1));
//        actually.set("3", (double) actuallyValueList.get(2));
//        actually.set("4", (double) actuallyValueList.get(3));
//        actually.set("5", (double) actuallyValueList.get(4));
//        actually.set("6", (double) actuallyValueList.get(5));
//        actually.set("7", (double) actuallyValueList.get(6));
//        actually.set("8", (double) actuallyValueList.get(7));
//        actually.set("9", (double) actuallyValueList.get(8));
//        actually.set("10", (double) actuallyValueList.get(9));
//        actually.set("11", (double) actuallyValueList.get(10));
//        actually.set("12", (double) actuallyValueList.get(11));

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
}
