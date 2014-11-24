/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.weblite.codename1.mirah.jsondemo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author shannah
 */
public class Calendar {
    private String summary;
    private String description;
    private Date updated;
    private List<Event> items;

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the updated
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * @param updated the updated to set
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * @return the items
     */
    public List<Event> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Event> items) {
        this.items = items;
    }
    
    
    public static class Event {
        private String id;
        private String status;
        private Date created;
        private Date updated;
        private Date start;
        private Date end;
        private String iCalUID;
        private String description;
        private String summary;

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the status
         */
        public String getStatus() {
            return status;
        }

        /**
         * @param status the status to set
         */
        public void setStatus(String status) {
            this.status = status;
        }

        /**
         * @return the created
         */
        public Date getCreated() {
            return created;
        }

        /**
         * @param created the created to set
         */
        public void setCreated(Date created) {
            this.created = created;
        }

        /**
         * @return the updated
         */
        public Date getUpdated() {
            return updated;
        }

        /**
         * @param updated the updated to set
         */
        public void setUpdated(Date updated) {
            this.updated = updated;
        }

        /**
         * @return the start
         */
        public Date getStart() {
            return start;
        }

        /**
         * @param start the start to set
         */
        public void setStart(Date start) {
            this.start = start;
        }

        /**
         * @return the end
         */
        public Date getEnd() {
            return end;
        }

        /**
         * @param end the end to set
         */
        public void setEnd(Date end) {
            this.end = end;
        }

        /**
         * @return the iCalUID
         */
        public String getiCalUID() {
            return iCalUID;
        }

        /**
         * @param iCalUID the iCalUID to set
         */
        public void setiCalUID(String iCalUID) {
            this.iCalUID = iCalUID;
        }

        /**
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * @param description the description to set
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         * @return the summary
         */
        public String getSummary() {
            return summary;
        }

        /**
         * @param summary the summary to set
         */
        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
