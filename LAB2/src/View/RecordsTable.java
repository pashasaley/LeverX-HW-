package View;

import Model.CreateData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;

public class RecordsTable {

    private Display display = MainDisplay.display;
    private ArrayList<CreateData> records;
    private static final int Y = 15;

    public RecordsTable(ArrayList<CreateData> studs) {
        records = new ArrayList<>();
        records = studs;
    }

    public void setTable(Shell shell) {

        org.eclipse.swt.widgets.Table table = new org.eclipse.swt.widgets.Table
                (shell, SWT.SINGLE | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);

        Button nextPage = new Button(shell, SWT.PUSH);
        nextPage.setBounds(330,Y,24,24);
        nextPage.setImage(new Image(display, "images/next.png"));

        Label page = new Label(shell, SWT.NONE);
        page.setText("Страница");
        page.setBounds(140,Y,70,40);

        Label currentPage = new Label(shell, SWT.CENTER);
        currentPage.setBounds(210,Y,40,40);

        Label of = new Label(shell, SWT.NONE);
        of.setText("из");
        of.setBounds(250,Y,40,40);

        Label pages = new Label(shell, SWT.NONE);
        pages.setBounds(290,Y,40,40);

        Button prevPage = new Button(shell, SWT.PUSH);
        prevPage.setBounds(110, Y, 24, 24);
        prevPage.setImage(new Image(display, "images/previous.png"));

        Label showNotes = new Label(shell, SWT.NONE);
        showNotes.setText("Количество записей на странице: ");
        showNotes.setBounds(600,Y,300,40);

        Text numCurrNotes = new Text(shell, SWT.CENTER);
        numCurrNotes.setBounds(900,Y,40,40);

        Button updateTable = new Button(shell, SWT.PUSH);
        updateTable.setBounds(1300,Y,170,40);
        updateTable.setText("Записать в таблицу");
        updateTable.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                if (numCurrNotes.getText().isEmpty()) {
                    MessageBox message = new MessageBox(shell);
                    message.setMessage("Введите количество записей на странице");
                    message.setText("Ошибка");
                    message.open();
                } else {
                    if (Integer.parseInt(numCurrNotes.getText()) <= records.size() && Integer.parseInt(numCurrNotes.getText()) > 0) {
                        currentPage.setText("1");
                        showNotes(table, records, Integer.parseInt(numCurrNotes.getText()) * (Integer.parseInt(currentPage.getText()) - 1), Integer.parseInt(numCurrNotes.getText()) * Integer.parseInt(currentPage.getText()));
                        int page = (int) Math.ceil((double) records.size() / Double.parseDouble(numCurrNotes.getText()));
                        pages.setText(String.valueOf(page));
                    }
                }
            }
        });

        prevPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if(Integer.parseInt(currentPage.getText()) >= 2) {
                    currentPage.setText(String.valueOf(Integer.parseInt(currentPage.getText()) - 1));
                }
                if(Integer.parseInt(currentPage.getText()) < Integer.parseInt(pages.getText())) {
                    showNotes(table, records, Integer.parseInt(numCurrNotes.getText())*(Integer.parseInt(currentPage.getText())-1),
                            Integer.parseInt(numCurrNotes.getText())*(Integer.parseInt(currentPage.getText())));
                }
                else if(Integer.parseInt(currentPage.getText()) == Integer.parseInt(pages.getText())){
                    showNotes(table, records, Integer.parseInt(numCurrNotes.getText())*(Integer.parseInt(currentPage.getText())-1),
                            records.size());
                }
            }

        });

        nextPage.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                if(Integer.parseInt(currentPage.getText()) < Integer.parseInt(pages.getText())) {
                    currentPage.setText(String.valueOf(Integer.parseInt(currentPage.getText()) + 1));
                    showNotes(table, records,
                            Integer.parseInt(numCurrNotes.getText())*(Integer.parseInt(currentPage.getText())-1),
                            Integer.parseInt(numCurrNotes.getText())*(Integer.parseInt(currentPage.getText())));
                }
                else if(Integer.parseInt(currentPage.getText()) == Integer.parseInt(pages.getText())){
                    showNotes(table, records, Integer.parseInt(numCurrNotes.getText())*(Integer.parseInt(currentPage.getText())-1),
                            records.size());
                }
            }

        });

        table.setBounds(160,60,1330,400);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        TableColumn n = new TableColumn(table, SWT.CENTER);
        n.setText("Номер");
        n.setWidth(90);

        TableColumn product = new TableColumn(table, SWT.CENTER);
        product.setText("Название продукта");
        product.setWidth(200);

        TableColumn maker = new TableColumn(table, SWT.CENTER);
        maker.setText("Название производителя");
        maker.setWidth(250);

        TableColumn UNP = new TableColumn(table, SWT.CENTER);
        UNP.setText("УНП производителя");
        UNP.setWidth(230);

        TableColumn amount = new TableColumn(table, SWT.CENTER);
        amount.setText("Количество товара на складе");
        amount.setWidth(260);

        TableColumn address = new TableColumn(table, SWT.CENTER);
        address.setText("Адрес склада");
        address.setWidth(300);

        shell.pack();
    }

    public void showNotes(Table table, ArrayList<CreateData> records, int startElement, int endElement) {
        table.removeAll();
        table.clearAll();
        int counter = startElement+1;
        for (CreateData record : records.subList(startElement, endElement)){
            TableItem tableItem = new TableItem(table, SWT.PUSH);
            tableItem.setText(0, String.valueOf(counter++));
            tableItem.setText(1, record.getProduct());
            tableItem.setText(2, record.getMaker());
            tableItem.setText(3, String.valueOf(record.getUNP()));
            tableItem.setText(4, String.valueOf(record.getAmount()));
            tableItem.setText(5, record.getAddress());
        }
    }
}