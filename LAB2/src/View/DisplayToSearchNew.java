package View;

import Control.Control;
import Model.CreateData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class DisplayToSearch {

    private Display display = MainDisplay.display;
    private Shell shell = new Shell(display);
    public static List<CreateData> records = new ArrayList<>();

    public DisplayToSearch(Control controller) {
        shell.setText("Найти запись");
        shell.setLayout(new RowLayout());
        deleteDisplay(controller);
        shell.pack();
        shell.open();
    }

    public void deleteDisplay(Control controller) {

        shell.setText("Поиск по:  ");
        shell.setLayout(new RowLayout());

        Button searchProductAmount = new Button(shell, SWT.RADIO);
        searchProductAmount.setText("Названию или количеству продукта");

        Button searchMakerUNP = new Button(shell, SWT.RADIO);
        searchMakerUNP.setText("Названию или УНП производителя");

        Button searchByAddress = new Button(shell, SWT.RADIO);
        searchByAddress.setText("Адресу");

        Button next = new Button(shell, SWT.PUSH);
        next.setText("Далее");

        next.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if(searchProductAmount.getSelection()){
                    searchProductOrAmount(controller);
                }
                else if(searchByAddress.getSelection()){
                    searchAddress(controller);
                }
                else if(searchMakerUNP.getSelection()){
                    searchMakerOrUNP(controller);
                }
            }
        });

        shell.pack();
    }

    public void searchProductOrAmount(Control controller){

        Shell shell = new Shell(display);
        shell.setText("Поиск по названию или количеству продукта");
        shell.setLayout(new RowLayout());
        shell.open();

        Label product = new Label(shell,SWT.NONE);
        product.setText("Название продукта:");

        Text textProduct = new Text(shell, SWT.CENTER);

        Label amount = new Label(shell,SWT.NONE);
        amount.setText("Количество продукта:");

        Text textAmount = new Text(shell, SWT.CENTER);

        Button search = new Button(shell, SWT.PUSH);
        search.setText("Найти");

        search.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if(textProduct.getText().isEmpty() && textAmount.getText().isEmpty()){
                    MessageBox messageBox = new MessageBox(shell);
                    messageBox.setMessage("Заполните хотя бы одно поле");
                    messageBox.setText("Ошибка");
                    messageBox.open();
                }else {
                    CreateData record = new CreateData();
                    record.setAmount(Integer.valueOf(textAmount.getText()));
                    record.setProduct(textProduct.getText());
                    records = controller.searchAmountOrProduct(record);
                    shell.close();
                }
            }
        });

        shell.pack();
    }

    public void searchAddress(Control controller){

        Shell shell = new Shell(display);
        shell.setLayout(new RowLayout());
        shell.setText("Поиск по адресу склада");
        shell.open();

        Label country = new Label(shell,SWT.NONE);
        country.setText("Страна:");

        Text textCountry = new Text(shell, SWT.CENTER);

        Label region = new Label(shell,SWT.NONE);
        region.setText("Область:");

        Text textRegion = new Text(shell, SWT.CENTER);

        Label city = new Label(shell,SWT.NONE);
        city.setText("Город:");

        Text textCity = new Text(shell, SWT.CENTER);

        Label street = new Label(shell,SWT.NONE);
        street.setText("Улица:");

        Text textStreet = new Text(shell, SWT.CENTER);

        Label house = new Label(shell,SWT.NONE);
        house.setText("Дом:");

        Text textHouse = new Text(shell, SWT.CENTER);

        Label flat = new Label(shell,SWT.NONE);
        flat.setText("Квартира:");

        Text textFlat = new Text(shell, SWT.CENTER);

        Button search = new Button(shell, SWT.PUSH);
        search.setText("Найти");

        search.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if(textCountry.getText().isEmpty() || textCity.getText().isEmpty() || textFlat.getText().isEmpty() ||
                        textHouse.getText().isEmpty() || textRegion.getText().isEmpty() ||
                        textStreet.getText().isEmpty()){
                    MessageBox messageBox = new MessageBox(shell);
                    messageBox.setMessage("Остались незаполненные поля");
                    messageBox.setText("Ошибка");
                    messageBox.open();
                }else {
                    CreateData record = new CreateData();
                    record.setCountry(textCountry.getText());
                    record.setRegion(textRegion.getText());
                    record.setCity(textCity.getText());
                    record.setStreet(textStreet.getText());
                    record.setHouse(textHouse.getText());
                    record.setFlat(textFlat.getText());
                    records = controller.searchMakerOrUNP(record);
                    shell.close();
                }
            }
        });

        shell.pack();
    }

    public void searchMakerOrUNP(Control controller){
        Shell shell = new Shell(display);
        shell.setText("Поиск по названию или УНП продукта");
        shell.setLayout(new RowLayout());
        shell.open();

        Label maker = new Label(shell,SWT.NONE);
        maker.setText("Название производителя:");

        Text textMaker = new Text(shell, SWT.CENTER);

        Label UNP = new Label(shell,SWT.NONE);
        UNP.setText("УНП производителя:");

        Text textUNP = new Text(shell, SWT.CENTER);

        Button search = new Button(shell, SWT.PUSH);
        search.setText("Удалить");

        search.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if(textUNP.getText().isEmpty() && textMaker.getText().isEmpty()){
                    MessageBox messageBox = new MessageBox(shell);
                    messageBox.setMessage("Заполните хотя бы одно поле");
                    messageBox.setText("Ошибка");
                    messageBox.open();
                }else {
                    CreateData record = new CreateData();
                    record.setMaker(textMaker.getText());
                    record.setUNP(Integer.valueOf(textUNP.getText()));
                    records = controller.searchAddress(record);
                    shell.close();
                }
            }
        });

        shell.pack();
    }
}