package controller;

import dao.InputData;
import dao.ReadData;
import model.AllInfo;

import org.eclipse.swt.widgets.Label;
import org.xml.sax.SAXException;
import view.Parameters;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static view.DeleteDisplay.*;

public class Controller {
    public AllInfo allItems = new AllInfo();
    private ReadData readData = new ReadData();

    public void add(AllInfo item) {
        allItems.addItem(item);
    }

    public void set(List<AllInfo> items) {
        for (AllInfo item : items){
            add(item);
        }
    }

    public int delete(AllInfo it, int criterion, Label num){
        List<AllInfo> listToDelete = new ArrayList<>();
        switch (criterion){
            case CRITERION_FOR_PRODUCT_AND_AMOUNT: {
                listToDelete = searchAmountOrProduct(it);
            }break;
            case CRITERION_FOR_MAKER_AND_UNP: {
                listToDelete = searchMakerOrUNP(it);
            }break;
            case CRITERION_FOR_ADDRESS: {
                listToDelete = searchAddress(it);
            }break;
        }
        for (AllInfo item : listToDelete){
            allItems.deleteRecord(item);
        }//removeAll
        num.setText(String.valueOf(allItems.getItems().size()));
        return listToDelete.size();
    }

    public void saveFile(File file) throws TransformerException {
        InputData inputData = new InputData((ArrayList<AllInfo>) allItems.getItems());
        inputData.setFile(file);
        try {
            inputData.write();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void openFile(File file) throws TransformerException, SAXException, ParserConfigurationException {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, readData);
            set(readData.getRecords());
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<AllInfo> searchAmountOrProduct(AllInfo r) {
        List<AllInfo> items = new ArrayList<>();
        for (AllInfo item : allItems.getItems())
            if ((item.productInfo.getProduct().equals(r.productInfo.getProduct()) &&
                    item.productInfo.getAmount().equals(r.productInfo.getAmount())) ||
                    (r.productInfo.getProduct().equals("") && item.productInfo.getAmount().
                            equals(r.productInfo.getAmount()))||(item.productInfo.getProduct().
                    equals(r.productInfo.getProduct()) && r.productInfo.getAmount().equals(""))) {
                items.add(item);
            }
        System.out.println(items.size());
        return items;
    }

    public List<AllInfo> searchAddress(AllInfo r){
        List<AllInfo> items = new ArrayList<>();
        for (AllInfo item : allItems.getItems()) {
            if (item.address.getAddress().equals(r.address.getAddress())){
                items.add(item);
            }
        }
        return items;
    }

    public List<AllInfo> searchMakerOrUNP(AllInfo r){
        List<AllInfo> items = new ArrayList<>();
        for (AllInfo item : allItems.getItems()) {
            if((item.makerInfo.getMaker().equals(r.makerInfo.getMaker()) && item.makerInfo.getUNP().
                    equals(r.makerInfo.getUNP())) || item.makerInfo.getMaker().equals(r.makerInfo.getMaker())
                    ||(r.makerInfo.getMaker().equals("") && item.makerInfo.getUNP().equals(r.makerInfo.getUNP()))) {
                items.add(item);
            }
        }
        return items;
    }
}