package com.aimslabs.domains.pojo;

import javax.persistence.Embeddable;

/**
 * Created by sayemkcn on 11/11/16.
 */
@Embeddable
public class Address {

    private String homeAddress;
    private String streetOrVillage;
    private String unionOrWard;
    private String upazila;
    private String district;
    private String division;

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getStreetOrVillage() {
        return streetOrVillage;
    }

    public void setStreetOrVillage(String streetOrVillage) {
        this.streetOrVillage = streetOrVillage;
    }

    public String getUnionOrWard() {
        return unionOrWard;
    }

    public void setUnionOrWard(String unionOrWard) {
        this.unionOrWard = unionOrWard;
    }

    public String getUpazila() {
        return upazila;
    }

    public void setUpazila(String upazila) {
        this.upazila = upazila;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "Address{" +
                "homeAddress='" + homeAddress + '\'' +
                ", streetOrVillage='" + streetOrVillage + '\'' +
                ", unionOrWard='" + unionOrWard + '\'' +
                ", upazila='" + upazila + '\'' +
                ", district='" + district + '\'' +
                ", division='" + division + '\'' +
                '}';
    }
}
