package dao;

import controller.Controller;
import model.AllInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InputData {
    private List<AllInfo> records;
    private File file;

    public InputData(ArrayList<AllInfo> records) throws TransformerException {
        this.records = records;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static void saveFile(Shell shell, Controller controller){//dao
        FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
        fileDialog.setText("Сохранить");
        fileDialog.setFilterPath("C:\\");
        String[] fileExtension = {"*.xml"};
        String[] fileName = {"NewRecords"};
        fileDialog.setFilterNames(fileName);
        fileDialog.setFilterExtensions(fileExtension);
        String file = fileDialog.open();
        if (file == null){
            MessageBox messageBox = new MessageBox(shell);
            messageBox.setMessage("Выберите файл");
            messageBox.setText("Ошибка");
            messageBox.open();
        }
        else{
            try {
                controller.saveFile(new File(file));
            } catch (TransformerException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void write() throws TransformerException, ParserConfigurationException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element elementRecords = document.createElement("catalog");
        document.appendChild(elementRecords);

        for (AllInfo record : records) {
            Element elementRecord = document.createElement("item");
            elementRecords.appendChild(elementRecord);

            Element elementProduct = document.createElement("product");
            elementRecord.appendChild(elementProduct);
            elementProduct.setTextContent(record.productInfo.getProduct());

            Element elementMaker = document.createElement("maker");
            elementRecord.appendChild(elementMaker);
            elementMaker.setTextContent(record.makerInfo.getMaker());

            Element elementUNP = document.createElement("UNP");
            elementRecord.appendChild(elementUNP);
            elementUNP.setTextContent(record.makerInfo.getUNP().toString());

            Element elementAmount = document.createElement("amount");
            elementRecord.appendChild(elementAmount);
            elementAmount.setTextContent(record.productInfo.getAmount());

            Element elementAddress = document.createElement("address");
            elementRecord.appendChild(elementAddress);

            Element elementCountry = document.createElement("country");
            elementAddress.appendChild(elementCountry);
            elementCountry.setTextContent(record.address.getCountry());

            Element elementRegion = document.createElement("region");
            elementAddress.appendChild(elementRegion);
            elementRegion.setTextContent(record.address.getRegion());

            Element elementCity = document.createElement("city");
            elementAddress.appendChild(elementCity);
            elementCity.setTextContent(record.address.getCity());

            Element elementStreet = document.createElement("street");
            elementAddress.appendChild(elementStreet);
            elementStreet.setTextContent(record.address.getStreet());

            Element elementHouse = document.createElement("house");
            elementAddress.appendChild(elementHouse);
            elementHouse.setTextContent(String.valueOf(record.address.getHouse()));

            Element elementFlat = document.createElement("flat");
            elementAddress.appendChild(elementFlat);
            elementFlat.setTextContent(String.valueOf(record.address.getFlat()));
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        transformer.transform(domSource, streamResult);
    }
}
