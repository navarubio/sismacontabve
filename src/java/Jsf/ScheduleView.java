package Jsf;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@ViewScoped
public class ScheduleView implements Serializable {

    private ScheduleModel eventModel;

    private ScheduleModel lazyEventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private Date endDate, endDate1, endDate2, endDate3, endDate4, endDate5,
            endDate6, endDate7, endDate8, endDate9, endDate10, endDate11, endDate12,
            endDate13, endDate14, endDate15, endDate16, endDate17, endDate18, endDate19,
            endDate20, endDate21, endDate22, endDate23, endDate24, endDate25, endDate26, 
            endDate27, endDate28, endDate29, endDate30, endDate31, endDate32, endDate33,
            endDate34, endDate35, endDate36, endDate37, endDate38, endDate39, endDate40,
            endDate41, endDate42, endDate43, endDate44, endDate45, endDate46, endDate47;

    @PostConstruct
    public void init() {
        eventModel = new DefaultScheduleModel();
        try {
            endDate = formatter.parse("2017-03-20");
            endDate1 = formatter.parse("2017-03-20");
            endDate2 = formatter.parse("2017-03-3");
            endDate3 = formatter.parse("2017-03-7");
            endDate4 = formatter.parse("2017-03-24");
            endDate5 = formatter.parse("2017-04-25");
            endDate6 = formatter.parse("2017-04-25");
            endDate7 = formatter.parse("2017-04-07");
            endDate8 = formatter.parse("2017-04-11");
            endDate9 = formatter.parse("2017-05-18");
            endDate10 = formatter.parse("2017-05-18");
            endDate11 = formatter.parse("2017-05-4");
            endDate12 = formatter.parse("2017-05-8");
            endDate13 = formatter.parse("2017-06-23");
            endDate14 = formatter.parse("2017-06-23");
            endDate15 = formatter.parse("2017-06-7");
            endDate16 = formatter.parse("2017-06-9");
            endDate17 = formatter.parse("2017-06-14");
            endDate18 = formatter.parse("2017-07-20");
            endDate19 = formatter.parse("2017-07-20");
            endDate20 = formatter.parse("2017-07-07");
            endDate21 = formatter.parse("2017-07-11");
            endDate22 = formatter.parse("2017-07-14");
            endDate23 = formatter.parse("2017-08-22");
            endDate24 = formatter.parse("2017-08-22");
            endDate25 = formatter.parse("2017-08-07");
            endDate26 = formatter.parse("2017-08-09");
            endDate27 = formatter.parse("2017-08-15");           
            endDate28 = formatter.parse("2017-09-20");           
            endDate29 = formatter.parse("2017-09-20");           
            endDate30 = formatter.parse("2017-09-05");           
            endDate31 = formatter.parse("2017-09-07");           
            endDate32 = formatter.parse("2017-09-13");           
            endDate33 = formatter.parse("2017-10-23");           
            endDate34 = formatter.parse("2017-10-23");           
            endDate35 = formatter.parse("2017-10-06");           
            endDate36 = formatter.parse("2017-10-10");           
            endDate37 = formatter.parse("2017-10-16");           
            endDate38 = formatter.parse("2017-11-20");           
            endDate39 = formatter.parse("2017-11-20");           
            endDate40 = formatter.parse("2017-11-03");           
            endDate41 = formatter.parse("2017-11-08");           
            endDate42 = formatter.parse("2017-11-13");
            endDate43 = formatter.parse("2017-12-22");           
            endDate44 = formatter.parse("2017-12-22");           
            endDate45 = formatter.parse("2017-12-07");           
            endDate46 = formatter.parse("2017-12-12");           
            endDate47 = formatter.parse("2017-12-15");
            

        } catch (ParseException ex) {
            Logger.getLogger(ScheduleView.class.getName()).log(Level.SEVERE, null, ex);
        }

        eventModel.addEvent(new DefaultScheduleEvent("IVA FEB", endDate, endDate));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate1, endDate1));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate2, endDate2));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate3, endDate3));
        eventModel.addEvent(new DefaultScheduleEvent("ISLR 2016", endDate4, endDate4));

        eventModel.addEvent(new DefaultScheduleEvent("IVA MAR", endDate5, endDate5));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate6, endDate6));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate7, endDate7));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate8, endDate8));

        eventModel.addEvent(new DefaultScheduleEvent("IVA ABR", endDate9, endDate9));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate10, endDate10));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate11, endDate11));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate12, endDate12));

        eventModel.addEvent(new DefaultScheduleEvent("IVA MAY", endDate13, endDate13));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate14, endDate14));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate15, endDate15));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate16, endDate16));
        eventModel.addEvent(new DefaultScheduleEvent("ESTIMADA ISLR", endDate17, endDate17));
        
        eventModel.addEvent(new DefaultScheduleEvent("IVA JUN", endDate18, endDate18));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate19, endDate19));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate20, endDate20));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate21, endDate21));
        eventModel.addEvent(new DefaultScheduleEvent("ESTIMADA ISLR", endDate22, endDate22));

        eventModel.addEvent(new DefaultScheduleEvent("IVA JUL", endDate23, endDate23));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate24, endDate24));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate25, endDate25));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate26, endDate26));
        eventModel.addEvent(new DefaultScheduleEvent("ESTIMADA ISLR", endDate27, endDate27));

        eventModel.addEvent(new DefaultScheduleEvent("IVA AGO", endDate28, endDate28));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate29, endDate29));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate30, endDate30));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate31, endDate31));
        eventModel.addEvent(new DefaultScheduleEvent("ESTIMADA ISLR", endDate32, endDate32));

        eventModel.addEvent(new DefaultScheduleEvent("IVA SEP", endDate33, endDate33));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate34, endDate34));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate35, endDate35));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate36, endDate36));
        eventModel.addEvent(new DefaultScheduleEvent("ESTIMADA ISLR", endDate37, endDate37));

        eventModel.addEvent(new DefaultScheduleEvent("IVA OCT", endDate38, endDate38));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate39, endDate39));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate40, endDate40));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate41, endDate41));
        eventModel.addEvent(new DefaultScheduleEvent("ESTIMADA ISLR", endDate42, endDate42));
       
        eventModel.addEvent(new DefaultScheduleEvent("IVA NOV", endDate43, endDate43));
        eventModel.addEvent(new DefaultScheduleEvent("R1 IVA/IGTF", endDate44, endDate44));
        eventModel.addEvent(new DefaultScheduleEvent("R2 IVA/IGTF", endDate45, endDate45));
        eventModel.addEvent(new DefaultScheduleEvent("RET ISLR", endDate46, endDate46));
        eventModel.addEvent(new DefaultScheduleEvent("ESTIMADA ISLR", endDate47, endDate47));
       
        lazyEventModel = new LazyScheduleModel() {

            @Override
            public void loadEvents(Date start, Date end) {
                Date random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));

                random = getRandomDate(start);
                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
            }
        };
    }

    public Date DeStringADate(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String strFecha = fecha;
        Date fechaDate = null;
        try {
            fechaDate = formato.parse(strFecha);
            System.out.println(fechaDate.toString());
            return fechaDate;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return fechaDate;
        }
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public ScheduleModel getLazyEventModel() {
        return lazyEventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

   
    private Date fourDaysLater3pm() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.PM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 4);
        t.set(Calendar.HOUR, 3);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(ActionEvent actionEvent) {
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
