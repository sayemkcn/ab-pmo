package com.aimslabs.domains.pojo;

import javax.persistence.Embeddable;

/**
 * Created by sayemkcn on 11/11/16.
 */
@Embeddable
public class Address {
    private String street;
    private String thana;
    private String district;
    private int postCode;
    private String division;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getThana() {
        return thana;
    }

    public void setThana(String thana) {
        this.thana = thana;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
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
                "street='" + street + '\'' +
                ", thana='" + thana + '\'' +
                ", district='" + district + '\'' +
                ", postCode=" + postCode +
                ", division='" + division + '\'' +
                '}';
    }
}
