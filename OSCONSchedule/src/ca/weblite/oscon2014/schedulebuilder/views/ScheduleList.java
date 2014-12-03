/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.oscon2014.schedulebuilder.views;

import ca.weblite.oscon2014.schedulebuilder.formatters.EventFormatter;
import ca.weblite.oscon2014.schedulebuilder.models.Event;
import ca.weblite.oscon2014.schedulebuilder.models.Schedule;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.MultiList;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author shannah
 */
public class ScheduleList {
    private Form listView;
    private Form detailsView;
    Schedule model;
    private String filterCategory = null;
    Tabs tabs;
    
    public ScheduleList(Schedule model){
        this.model = model;
        listView = new Form("Events");
        listView.setLayout(new BorderLayout());
        ScheduleListTpl listTpl = new ScheduleListTpl(new HashMap());
        tabs = listTpl.getDateTabs();
        
        updateList();
        
        Command cmd = new Command("All Events"){

            @Override
            public void actionPerformed(ActionEvent evt) {
                refreshCategoryFilter(null);
            }
            
        };
        cmd.putClientProperty("uiid", "CategoryCommand");
        listView.addCommand(cmd);
        
        for ( final String category : model.getCategories()){
            cmd = new Command(category){

                @Override
                public void actionPerformed(ActionEvent evt) {
                    refreshCategoryFilter(category);
                }
                
                

            };
            cmd.putClientProperty("uiid", "CategoryCommand");
            listView.addCommand(cmd);
            
        }
        
        
        
        
        
        listView.addComponent(BorderLayout.CENTER, listTpl.getRoot());
    }
    
    
    private void updateList(){
        SimpleDateFormat fmt = new SimpleDateFormat("MMM dd");
        int numTabs = tabs.getTabCount();
        int selectedTab = tabs.getSelectedIndex();
        for ( int i=0; i< numTabs; i++){
            tabs.removeTabAt(0);
        }
        for ( Date d : model.getDateRange()){
            final MultiList cmp = new MultiList();
            cmp.addActionListener(new ActionListener(){

                public void actionPerformed(ActionEvent evt) {
                    Map m = (Map)cmp.getSelectedItem();
                    eventClicked((Event)m.get("event"));
                }
                
            });
            
            ArrayList<Map> items = new ArrayList<Map>();
            EventFormatter formatter = new EventFormatter();
            for ( Event event : model.getEventsOnDate(d)){
                if ( filterCategory == null || (event.getCategories() != null && event.getCategories().contains(filterCategory))){
                    Map m = new HashMap();
                    m.put("Line1", event.getName());
                    m.put("Line2", formatter.formatStartAndStop(event));
                    m.put("event", event);
                    items.add(m);
                }
            }
            cmp.setModel(new com.codename1.ui.list.DefaultListModel(items));
            
            tabs.addTab(fmt.format(d), cmp);
        }
        tabs.setSelectedIndex(selectedTab);
        if ( filterCategory == null ){
            listView.setTitle("Events");
        } else {
            listView.setTitle(filterCategory);
        }
        listView.animateLayout(100);
    }
    
    
    protected void eventClicked(Event e){
        showEvent(e);
    }
    
    protected void refreshCategoryFilter(String category){
        filterCategory = category;
        updateList();
    }
    
    private Form initDetailsView(){
        setDetailsView(new Form("Event Details"));
        getDetailsView().setLayout(new BorderLayout());
        getDetailsView().setBackCommand(new Command("Events"){

            @Override
            public void actionPerformed(ActionEvent evt) {
                getListView().setTransitionInAnimator(null);
                getListView().showBack();
            }
            
        });
        return getDetailsView();
    }
    
    
    public void showEvent(Event e){
        EventFormatter fmt = new EventFormatter();
        setDetailsView(initDetailsView());
        ScheduleListDetailsTpl tpl = new ScheduleListDetailsTpl(new HashMap());
        tpl.getEventDate().setText(fmt.formatDateAndTime(e));
        tpl.getEventName().setText(e.getName());
        tpl.getSpeakers().setText(fmt.formatSpeakers(model, e));
        tpl.getEventLocation().setText(fmt.formatVenue(model, e));
        tpl.getEventDescription().setText(e.getDescription());
        getDetailsView().addComponent(BorderLayout.CENTER, tpl.getRoot());
        
        getDetailsView().show();
        
        
    }

    /**
     * @return the listView
     */
    public Form getListView() {
        return listView;
    }

    /**
     * @param listView the listView to set
     */
    public void setListView(Form listView) {
        this.listView = listView;
    }

    /**
     * @return the detailsView
     */
    public Form getDetailsView() {
        return detailsView;
    }

    /**
     * @param detailsView the detailsView to set
     */
    public void setDetailsView(Form detailsView) {
        this.detailsView = detailsView;
    }
    
}
