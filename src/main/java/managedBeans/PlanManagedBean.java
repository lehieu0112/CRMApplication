/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBeans;

import ejb.MonthFacade;
import ejb.OrdersFacade;
import ejb.PlanFacade;
import ejb.UsersFacade;
import entities.Month;
import entities.Plan;
import entities.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Named(value = "planManagedBean")
@RequestScoped
public class PlanManagedBean implements Serializable {

    @Inject
    private PlanFacade planEJB;
    @Inject
    private UsersFacade userEJB;
    @Inject
    private MonthFacade monthEJB;
    @Inject
    private OrdersFacade orderEJB;

    private Plan plan = new Plan();
    private List<Plan> searchList;
    private HttpSession session = (HttpSession) FacesContext.
            getCurrentInstance().getExternalContext().getSession(false);
    private Users user = (Users) session.getAttribute("userlogin");
    private ArrayList<Month> monthList = addMonths();

    
    private ArrayList<Month> addMonths() {
        ArrayList<Month> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Month m = new Month();
            m.setMonth(i + 1);
            list.add(m);
        }
        return list;
    }

    public ArrayList<Month> getMonthList() {
        return monthList;
    }

    public void setMonthList(ArrayList<Month> monthList) {
        this.monthList = monthList;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public List<Plan> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Plan> searchList) {
        this.searchList = searchList;
    }

    public PlanManagedBean() {

    }

    public String doCreatePlan() {
        plan.setUserID(user);
        planEJB.create(plan);
        for (int i = 0; i < 12; i++) {
            monthList.get(i).setPlanID(plan);
            monthEJB.create(monthList.get(i));
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Create Success", "Create Success !"));
        plan = new Plan();
        monthList = new ArrayList<>();
        monthList = addMonths();
        return "addplan.xhtml";
    }

    public List<Plan> doFindAllPlans() {
        return planEJB.findAll();
    }
    
    public String doDeletePlan(Integer id){
        planEJB.remove(planEJB.find(id));
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Success", "Delete Success !"));
        return "viewplans.xhtml";
    }

}
