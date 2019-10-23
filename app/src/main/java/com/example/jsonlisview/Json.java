package com.example.jsonlisview;

import org.json.JSONException;
import org.json.JSONObject;

public class Json {
    private String name;
    private String username;
    private String email;
    private String phone ;
    private String addrres;
    private String website;
    private String company;
    private String id;

    public Json(String id,String name, String username, String email, String phone,  String website, String company, String addrres) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.addrres = addrres;
        this.company = company;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddrres() {
        JSONObject address1 = null;
        try {
            address1 = new JSONObject(addrres);
            String street  = address1.getString("street");
            String suite  = address1.getString("suite");
            String city  = address1.getString("city");
            String zipcode  = address1.getString("zipcode");
            this.addrres = street+","+ suite+"," + city;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return addrres;
    }

    public void setAddrres(String addrres) {

    }

    public String getCompany() {
        JSONObject company1 = null;
        try {
            company1 = new JSONObject(company);
            String name  = company1.getString("name");
            this.company = name;
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return company;
    }

    public void setCompany(String company)  {

    }


}
