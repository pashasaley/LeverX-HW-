package model;

import java.util.ArrayList;
import java.util.List;

public class AllInfo {
    private List<AllInfo> records = new ArrayList<>();

    public ProductInfo productInfo;
    public MakerInfo makerInfo;
    public Address address;

    public AllInfo(){
        productInfo = new ProductInfo();
        makerInfo = new MakerInfo();
        address = new Address();
    }

    public List<AllInfo> getItems() {
        return records;
    }

    public void addItem(AllInfo record) {
        records.add(record);
    }

    public void deleteRecord(AllInfo record){
        records.remove(record);
    }


}
