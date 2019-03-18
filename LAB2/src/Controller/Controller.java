package Control;

import Model.InputData;
import Model.ReadData;
import Model.CreateData;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Control {

    public CreateData createData = new CreateData();
    private ReadData readData = new ReadData();

    public void add(CreateData record) {
        createData.addRecord(record);
    }

    public void set(List<CreateData> records) {
        for (CreateData record : records){
            add(record);
        }
    }

    public int delete(CreateData rec, int criterion){
        List<CreateData> listToDelete = new ArrayList<>();
        switch (criterion){
            case 1: {
                listToDelete = searchAmountOrProduct(rec);
            }break;
            case 2: {
                listToDelete = searchMakerOrUNP(rec);
            }break;
            case 3: {
                listToDelete = searchAddress(rec);
            }break;
        }
        for (CreateData record : listToDelete){
            createData.deleteRecord(record);
        }
        return listToDelete.size();
    }

    public void saveFile(File file) throws TransformerException {
        InputData inputData = new InputData((ArrayList<CreateData>) createData.getRecords());
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

    public List<CreateData> searchAmountOrProduct(CreateData r) {
        List<CreateData> records = new ArrayList<>();
        for (CreateData record : createData.getRecords())
            if (record.getProduct().equals(r.getProduct()) || record.getAmount().equals(r.getAmount())) {
                records.add(record);
            }
        return records;
    }

    public List<CreateData> searchAddress(CreateData r){
        List<CreateData> records = new ArrayList<>();
        for (CreateData record : createData.getRecords()) {
            if (record.getAddress().equals(r.getAddress())){
                records.add(record);
            }
        }
        return records;
    }

    public List<CreateData> searchMakerOrUNP(CreateData r){
        List<CreateData> records = new ArrayList<>();
        for (CreateData record : createData.getRecords()) {
            if(record.getMaker().equals(r.getMaker()) || record.getUNP().equals(r.getUNP())) {
                records.add(record);
            }
        }
        return records;
    }
}