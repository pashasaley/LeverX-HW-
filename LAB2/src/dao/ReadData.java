package dao;

import controller.Controller;
import model.AllInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadData extends DefaultHandler{
    private static final String RECORDS_TAG = "catalog";
    private static final String RECORD_TAG = "item";
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

    private List<AllInfo> records;
    private AllInfo record;
    private String currentElement;

    public List<AllInfo> getRecords() {
        return records;
    }

    public static void openFile(Shell shell, Controller controller){
        FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
        fileDialog.setText("Открыть");
        fileDialog.setFilterPath("C:\\");
        String[] fileExtension = {"*.xml"};
        fileDialog.setFilterExtensions(fileExtension);
        String file = fileDialog.open();
        if(file == null){
            MessageBox warning = new MessageBox(shell);
            warning.setMessage("Не выбран файл");
            warning.setText("Ошибка");
            warning.open();
        }
        else {
            try {
                controller.openFile(new File(file));
            } catch (ParserConfigurationException | SAXException | TransformerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        switch (currentElement){
            case RECORDS_TAG: {
                records = new ArrayList<>();
            }break;
            case RECORD_TAG: {
                record = new AllInfo();
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
                record.productInfo.setProduct(text);
            }break;

            case MAKER_TAG:{
                record.makerInfo.setMaker(text);
            }break;

            case UNP_TAG:{
                record.makerInfo.setUNP(Integer.valueOf(text));
            }break;

            case AMOUNT_TAG:{
                record.productInfo.setAmount(text);
            }break;

            case COUNTRY_TAG:{
                record.address.setCountry(text);
            }break;

            case REGION_TAG:{
                record.address.setRegion(text);
            }break;

            case CITY_TAG:{
                record.address.setCity(text);
            }break;

            case STREET_TAG:{
                record.address.setStreet(text);
            }break;

            case HOUSE_TAG:{
                record.address.setHouse(Integer.valueOf(text));
            }break;

            case FLAT_TAG:{
                record.address.setFlat(Integer.valueOf(text));
            }break;
        }
    }
}
