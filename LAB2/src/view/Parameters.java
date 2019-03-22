package view;

import model.AllInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;

public class Parameters {
    private static final int HEIGHT = 30;
    public static final int X = 15;

    private Text textProduct;
    private Text textAmount;

    private Text textMaker;
    private Text textUNP;

    private Text textCountry;
    private Text textRegion;
    private Text textCity;
    private Text textHouse;
    private Text textStreet;
    private Text textFlat;

    private Button chooseProductOrAmount;
    private Button chooseMakerOrUNP;
    private Button chooseAddress;

    private Label numOnAddDisplay;


    public void setMessage(Shell shell, int count){
        MessageBox message = new MessageBox(shell);
        if (count == 0) {
            message.setText("Ошибка");
            message.setMessage("Таких записей нет");
        }else {
            message.setText("Успешно удалено");
            message.setMessage("Записи стерты (" + count + ")");
        }
        message.open();
    }

    public void setAllLabelsAndTexts(Shell shell, Display display) {
        labelsAndTextAddress(shell);
        labelsAndTextMakerUnp(shell);
        labelsAndTextProductAmount(shell);
        labelsCriterion(shell);
        labelsNumberOfItems(shell, display);
    }

    public void setNumberOfItems(ArrayList<AllInfo> items){
        numOnAddDisplay.setText(String.valueOf(items.size()));
    }

    public void labelsAndTextAddress(Shell shell){
        Label country = new Label(shell, SWT.NONE);
        country.setText("Страна:");
        country.setBounds(X, 15,70, HEIGHT);

        textCountry = new Text(shell, SWT.CENTER);
        textCountry.setBounds(X,45,70, HEIGHT);

        Label region = new Label(shell,SWT.NONE);
        region.setText("Область:");
        region.setBounds(X,75,80, HEIGHT);

        textRegion = new Text(shell, SWT.CENTER);
        textRegion.setBounds(X,105,80, HEIGHT);

        Label city = new Label(shell,SWT.NONE);
        city.setText("Город:");
        city.setBounds(X,135,70, HEIGHT);

        textCity = new Text(shell, SWT.CENTER);
        textCity.setBounds(X,165,70, HEIGHT);

        Label street = new Label(shell,SWT.NONE);
        street.setText("Улица:");
        street.setBounds(X,195,70, HEIGHT);

        textStreet = new Text(shell, SWT.CENTER);
        textStreet.setBounds(X,225,70, HEIGHT);

        Label house = new Label(shell,SWT.NONE);
        house.setText("Дом:");
        house.setBounds(X,255,60, HEIGHT);

        textHouse = new Text(shell, SWT.CENTER);
        textHouse.setBounds(X,285,60, HEIGHT);

        Label flat = new Label(shell,SWT.NONE);
        flat.setText("Квартира:");
        flat.setBounds(X,315,90, HEIGHT);

        textFlat = new Text(shell, SWT.CENTER);
        textFlat.setBounds(X,345,90, HEIGHT);
    }

    public void labelsAndTextProductAmount(Shell shell){
        Label product = new Label(shell,SWT.NONE);
        product.setText("Название продукта: ");
        product.setBounds(X,375,170, HEIGHT);

        textProduct = new Text(shell, SWT.CENTER);
        textProduct.setBounds(X,405,120, Parameters.HEIGHT);

        Label amount = new Label(shell,SWT.NONE);
        amount.setText("Количество товара на складе:");
        amount.setBounds(X,435,250, Parameters.HEIGHT);

        textAmount = new Text(shell, SWT.CENTER);
        textAmount.setBounds(X,465,160, Parameters.HEIGHT);
    }

    public void labelsAndTextMakerUnp(Shell shell){
        Label maker = new Label(shell,SWT.NONE);
        maker.setText("Название производителя: ");
        maker.setBounds(X,495,220, HEIGHT);

        textMaker = new Text(shell, SWT.CENTER);
        textMaker.setBounds(X,525,140, HEIGHT);

        Label UNP = new Label(shell,SWT.NONE);
        UNP.setText("УНП производителя: ");
        UNP.setBounds(X,555,180, HEIGHT);

        textUNP = new Text(shell, SWT.CENTER);
        textUNP.setBounds(X,585,140, HEIGHT);
    }

     public void labelsNumberOfItems(Shell shell, Display display){
         Label numOfItems = new Label(shell, SWT.CENTER);
         numOfItems.setText("Количество записей");
         numOfItems.setBounds(15,615,100, HEIGHT);

         numOnAddDisplay = new Label(shell, SWT.CENTER);
         numOnAddDisplay.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
         numOnAddDisplay.setBounds(15,645, 40, HEIGHT);
     }

    public void labelsCriterion(Shell shell){
        Label label = new Label(shell, SWT.CENTER);
        label.setText("Критерии: ");
        label.setBounds(15,675,80, HEIGHT);
    }

    public void setAddressOnShell(AllInfo record){
        Integer flat = (textFlat.getText().equals("") ? null : Integer.valueOf(textFlat.getText()));
        Integer house = (textHouse.getText().equals("") ? null : Integer.valueOf(textHouse.getText()));
        record.address.setCountry(textCountry.getText());
        record.address.setRegion(textRegion.getText());
        record.address.setCity(textCity.getText());
        record.address.setStreet(textStreet.getText());
        record.address.setHouse(house);
        record.address.setFlat(flat);
    }

    public void setProductAndAmountOnShell(AllInfo record){
        record.productInfo.setProduct(textProduct.getText());
        record.productInfo.setAmount(textAmount.getText());
    }

    public void setMakerAndUnpOnShell(AllInfo record){
        Integer UNP = (textUNP.getText().equals("") ? null : Integer.valueOf(textUNP.getText()));
        record.makerInfo.setMaker(textMaker.getText());
        record.makerInfo.setUNP(UNP);
    }

    public boolean checkIfFilled(){
        return textMaker.getText().isEmpty() || textUNP.getText().isEmpty()||
                textProduct.getText().isEmpty() || textAmount.getText().isEmpty() ||
                textCountry.getText().isEmpty() || textCity.getText().isEmpty() ||
                textHouse.getText().isEmpty() || textRegion.getText().isEmpty() ||
                textStreet.getText().isEmpty();
    }

    public void chooseByCriteria(Shell shell){
        chooseProductOrAmount = new Button(shell, SWT.RADIO);
        chooseProductOrAmount.setText("Названию или количеству продукта");
        chooseProductOrAmount.setBounds(X,705,350, HEIGHT);

        chooseMakerOrUNP = new Button(shell, SWT.RADIO);
        chooseMakerOrUNP.setText("Названию или УНП производителя");
        chooseMakerOrUNP.setBounds(X,735,350, HEIGHT);

        chooseAddress = new Button(shell, SWT.RADIO);
        chooseAddress.setText("Адресу");
        chooseAddress.setBounds(X,765,350, HEIGHT);

        chooseProductOrAmount.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                editAddress(false);
                editMakerAndUnp(false);
                editProductAndAmount(true);
            }
        });

        chooseMakerOrUNP.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                editMakerAndUnp(true);
                editAddress(false);
                editProductAndAmount(false);
            }
        });

        chooseAddress.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                editAddress(true);
                editProductAndAmount(false);
                editMakerAndUnp(false);
            }
        });
    }

    public boolean checkChoiceProductAmount(){
        return chooseProductOrAmount.getSelection();
    }

    public boolean checkChoiceMakerUNP(){
        return chooseMakerOrUNP.getSelection();
    }

    public boolean checkChoiceAddress(){
        return chooseAddress.getSelection();
    }

    public void editAddress(boolean bol){
        textCountry.setText("");
        textRegion.setText("");
        textCity.setText("");
        textStreet.setText("");
        textHouse.setText("");
        textFlat.setText("");
        textCountry.setEditable(bol);
        textRegion.setEditable(bol);
        textCity.setEditable(bol);
        textStreet.setEditable(bol);
        textFlat.setEditable(bol);
        textHouse.setEditable(bol);
    }

    public void editProductAndAmount(boolean bol){
        textProduct.setText("");
        textProduct.setText("");
        textProduct.setEditable(bol);
        textAmount.setEditable(bol);
    }

    public void editMakerAndUnp(boolean bol){
        textMaker.setText("");
        textUNP.setText("");
        textMaker.setEditable(bol);
        textUNP.setEditable(bol);
    }
}
