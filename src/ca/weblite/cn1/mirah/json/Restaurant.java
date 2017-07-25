package ca.weblite.cn1.mirah.json;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.Date;
import java.util.List;


/**
 *
 * @author Chad
 */
public class Restaurant {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String description;
    private double lat;
    private double lng;
    private List<Object> tags;
    private List<Object> attributes;
    private List<Object> specials;
    private Seating seating;
    private WaitTime waitTime;
    private int numDevices;
    private boolean favourite;
    private Date lastUpdated;
    private boolean estimateOnly;
    private double rating;
    private int numReviews;
    private String placeId;
    private String image;
    private List<String> types;
    private String[] weekdayText;
          
    
    public Restaurant() {
        
    }

    public Restaurant(String name, String description, double lat, double lng) {
        this.name = name;
        this.description = description;
        this.lat = lat;
        this.lng = lng;
        this.seating = Seating.LOTSOFROOM;
        this.waitTime = WaitTime.NOWAIT;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    

    public Seating getSeating() {
        return seating;
    }

    public void setSeating(Seating seating) {
        this.seating = seating;
    }

    public WaitTime getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(WaitTime waitTime) {
        this.waitTime = waitTime;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the lat
     */
    public double getLat() {
        return lat;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat) {
        this.lat = lat;
    }

    /**
     * @return the lng
     */
    public double getLng() {
        return lng;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * @return the tags
     */
    public List<Object> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    /**
     * @return the attributes
     */
    public List<Object> getAttributes() {
        return attributes;
    }

    public List<Object> getSpecials() {
        return specials;
    }

    public void setSpecials(List<Object> specials) {
        this.specials = specials;
    }
    
    

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
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

    public int getNumDevices() {
        return numDevices;
    }

    public void setNumDevices(int numDevices) {
        this.numDevices = numDevices;
    }

    public boolean isEstimateOnly() {
        return estimateOnly;
    }

    public void setEstimateOnly(boolean estimateOnly) {
        this.estimateOnly = estimateOnly;
    }

    public String[] getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(String[] weeekdayText) {
        this.weekdayText = weeekdayText;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Restaurant) {
            return getId() == ((Restaurant) obj).getId();
        } else {
            return false;
        }
    }
    
    
    

    @Override
    public String toString() {
        return "Restaurant{" + "name=" + name + ", description=" + description + ", lat=" + lat + ", lng=" + lng + ", tags=" + tags + ", attributes=" + attributes + ", seating=" + seating + ", waitTime=" + waitTime + '}';
    }

    /**
     * @return the favourite
     */
    public boolean isFavourite() {
        return favourite;
    }

    /**
     * @param favourite the favourite to set
     */
    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    /**
     * @return the lastUpdated
     */
    public Date getLastUpdated() {
        return lastUpdated;
    }

    /**
     * @param lastUpdated the lastUpdated to set
     */
    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }
    
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
    
    
    
}
