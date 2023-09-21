package com.ems.persistence.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Locations")
public class Location {
    @Id
    @Column(name = "LocationID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;

    @Column(name = "LocationName")
    private String locationName;

    @Column(name = "LocationDescription")
    private String locationDescription;

    @Column(name = "LocationAddress")
    private String locationAddress;

    @OneToMany(mappedBy = "location")
    private List<Project> projects;

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
}
