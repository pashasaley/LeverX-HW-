package Model;

import java.util.ArrayList;
import java.util.List;

public class CreateData {//rename
    private String product;
    private String maker;
    private Integer UNP;
    private String amount;

    private String country;
    private String region;
    private String city;
    private String street;
    private Integer house;
    private Integer flat;

    private List<CreateData> records = new ArrayList<>();

    public List<CreateData> getRecords() {
        return records;
    }

    public void addRecord(CreateData record) {
        records.add(record);
    }

    public void deleteRecord(CreateData record){
        records.remove(record);
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getUNP() {
        return UNP;
    }

    public void setUNP(Integer UNP) {
        this.UNP = UNP;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String state) {
        this.region = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public String getAddress() {
        return country + ", " + region + ", " + city + ", ул. " + street + ", д. " + house + ", кв. " + flat;
    }
}
