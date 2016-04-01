/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.CampaignFacade;
import ejb.OrdersFacade;
import entities.Orders;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

@Named(value = "campaignReportManagedBean")
@RequestScoped
public class CampaignReportManagedBean implements Serializable {

    @Inject
    private OrdersFacade orderEJB;
    @Inject
    private CampaignFacade campaignEJB;
    
    private Integer id;
    private BarChartModel barModel;

    public BarChartModel getBarModel() {
        createBarModels();
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public CampaignReportManagedBean() {
    }
    
    public Double doGetCampaignTarget(){
        return campaignEJB.find(id).getTarget();
    }
    
    public Double doGetCampainPerformance(){
        List<Orders> list = orderEJB.doFindOrdersByCampaign(id);
        Double sum = 0.0;
        for(Orders o: list){
            sum += o.getTotalAmount();
        }
        return sum;
    }
    
    public String doFormatCampaignTarget(){
        NumberFormat f = NumberFormat.getNumberInstance();
        return f.format(doGetCampaignTarget());
    }
    
    public String doFormatCampaignPerformance(){
        NumberFormat f = NumberFormat.getNumberInstance();
        return f.format(doGetCampainPerformance());
    }
    
    public String doGetCampainPercent(){
        NumberFormat f = NumberFormat.getPercentInstance();
        return f.format(doGetCampainPerformance()/doGetCampaignTarget());
    }
    
    private void createBarModels() {
        createBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Campaign");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Value");
        yAxis.setMin(0);
        double m = (doGetCampaignTarget()>doGetCampainPerformance())? doGetCampaignTarget():doGetCampainPerformance();
        yAxis.setMax((m*1.5)/1000000.00);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries target = new ChartSeries();
        target.setLabel("Target");
        target.set("1", ((double)doGetCampaignTarget())/1000000.00);
        ChartSeries actually = new ChartSeries();
        actually.setLabel("Actually");
        actually.set("1", ((double)doGetCampainPerformance())/1000000.00);
        model.addSeries(target);
        model.addSeries(actually);
        return model;
    }
}
