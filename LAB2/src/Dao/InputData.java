package Model;

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
    private List<CreateData> records;
    private File file;

    public InputData(ArrayList<CreateData> records) throws TransformerException {
        this.records = records;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void write() throws TransformerException, ParserConfigurationException {
        //работа с файлами в отдельный слой или в контроллер, dao
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element elementRecords = document.createElement("records");
        document.appendChild(elementRecords);

        for (CreateData record : records) {
            Element elementRecord = document.createElement("record");
            elementRecords.appendChild(elementRecord);

            Element elementProduct = document.createElement("product");
            elementRecord.appendChild(elementProduct);
            elementProduct.setTextContent(record.getProduct());

            Element elementMaker = document.createElement("maker");
            elementRecord.appendChild(elementMaker);
            elementMaker.setTextContent(record.getMaker());

            Element elementUNP = document.createElement("UNP");
            elementRecord.appendChild(elementUNP);
            elementUNP.setTextContent(record.getUNP().toString());

            Element elementAmount = document.createElement("amount");
            elementRecord.appendChild(elementAmount);
            elementAmount.setTextContent(record.getAmount().toString());

            Element elementAddress = document.createElement("address");
            elementRecord.appendChild(elementAddress);

            Element elementCountry = document.createElement("country");
            elementAddress.appendChild(elementCountry);
            elementCountry.setTextContent(record.getCountry());

            Element elementRegion = document.createElement("region");
            elementAddress.appendChild(elementRegion);
            elementRegion.setTextContent(record.getRegion());

            Element elementCity = document.createElement("city");
            elementAddress.appendChild(elementCity);
            elementCity.setTextContent(record.getCity());

            Element elementStreet = document.createElement("street");
            elementAddress.appendChild(elementStreet);
            elementStreet.setTextContent(record.getStreet());

            Element elementHouse = document.createElement("house");
            elementAddress.appendChild(elementHouse);
            elementHouse.setTextContent(String.valueOf(record.getHouse()));

            Element elementFlat = document.createElement("flat");
            elementAddress.appendChild(elementFlat);
            elementFlat.setTextContent(String.valueOf(record.getFlat()));
        }

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(file);
        transformer.transform(domSource, streamResult);
    }
}
