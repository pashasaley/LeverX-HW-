package Model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ReadData extends DefaultHandler{
    private static final String RECORDS_TAG = "records";
    private static final String RECORD_TAG = "record";
    private static final String PRODUCT_TAG = "product";
    private static final String MAKER_TAG = "maker";
    private static final String UNP_TAG = "UNP";
    private static final String AMOUNT_TAG = "amount";
    private static final String COUNTRY_TAG = "country";
    private static final String REGION_TAG = "region";
    private static final String CITY_TAG = "city";
    private static final String STREET_TAG = "street";
    private static final String HOUSE_TAG = "house";
    private static final String FLAT_TAG = "flat";

    private List<CreateData> records;
    private CreateData record;
    private String currentElement;

    public List<CreateData> getRecords() {
        return records;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        switch (currentElement){
            case RECORDS_TAG: {
                records = new ArrayList<>();
            }break;
            case RECORD_TAG: {
                record = new CreateData();
            }break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case RECORD_TAG: {
                records.add(record);
            }break;
            case PRODUCT_TAG: {
                currentElement = null;
            }break;
            case MAKER_TAG: {
                currentElement = null;
            }break;
            case UNP_TAG: {
                currentElement = null;
            }break;
            case AMOUNT_TAG: {
                currentElement = null;
            }break;
            case COUNTRY_TAG: {
                currentElement = null;
            }break;
            case REGION_TAG: {
                currentElement = null;
            }break;
            case CITY_TAG: {
                currentElement = null;
            }break;
            case STREET_TAG: {
                currentElement = null;
            }break;
            case HOUSE_TAG: {
                currentElement = null;
            }break;
            case FLAT_TAG: {
                currentElement = null;
            }break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException, NumberFormatException{
        String text = new String(ch, start, length);
        if(text.contains("<") || currentElement == null){
            return;
        }
        switch (currentElement) {
            case PRODUCT_TAG:{
                record.setProduct(text);
            }break;

            case MAKER_TAG:{
                record.setMaker(text);
            }break;

            case UNP_TAG:{
                record.setUNP(Integer.valueOf(text));
            }break;

            case AMOUNT_TAG:{
                record.setAmount(text);
            }break;

            case COUNTRY_TAG:{
                record.setCountry(text);
            }break;

            case REGION_TAG:{
                record.setRegion(text);
            }break;

            case CITY_TAG:{
                record.setCity(text);
            }break;

            case STREET_TAG:{
                record.setStreet(text);
            }break;

            case HOUSE_TAG:{
                record.setHouse(Integer.valueOf(text));
            }break;

            case FLAT_TAG:{
                record.setFlat(Integer.valueOf(text));
            }break;
        }
    }
}
