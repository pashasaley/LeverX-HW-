package lab1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.*;
import java.util.ArrayList;

public class Group6 {
    private volatile boolean isRunning = true;
    private int row;
    private int col;
    private int step;
    private TableColumn tableColumn;
    private Button pushButton1;
    private Button pushButton2;
    private ArrayList<String> items;
    private Button createTable;
    private Listener listener;
    private Table table;
    private TableColumn[] columns;
    private TableItem[] tableItems;
    private Text textForColumns;
    private Text textForRows;
    private Thread thread1;
    private Thread thread2;
    private Thread thread3;
    private Group group;

    public Group6(Shell shell, Display display) {
        group = new Group(shell, SWT.SHADOW_ETCHED_IN);

        GridLayout gridLayout = new GridLayout();
        gridLayout.marginRight = 10;
        gridLayout.marginLeft = 10;
        gridLayout.marginTop = 10;
        gridLayout.marginBottom = 10;

        group.setLayout(gridLayout);
        group.setLocation(500, 300);
        group.setText("Шестая группа");

        Label labelColumns = new Label(group, SWT.NONE);
        labelColumns.setText("Введите количество столбцов: ");

        textForColumns = new Text(group, SWT.BORDER);

        RowData layoutForColumns = new RowData();
        layoutForColumns.width = 60;

        Label labelRows = new Label(group, SWT.NONE);
        labelRows.setText("Введите количество строк: ");

        textForRows = new Text(group, SWT.BORDER);

        RowData layoutForRows = new RowData();
        layoutForRows.width = 60;

        createTable = new Button(group, SWT.PUSH);
        createTable.setText("Создать таблицу");

        pushButton1 = new Button(group, SWT.NONE);
        pushButton1.setText("Старт");

        pushButton2 = new Button(group, SWT.NONE);
        pushButton2.setText("Стоп");

        items = new ArrayList<>();

        thread1 = createThread1(display);
        thread2 = createThread1(display);
        thread3 = createThread2(display);

        table = new Table(group, SWT.BORDER | SWT.MULTI);

        group.pack();
    }

    void buttons(Display display){

        createTable.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                table.setLinesVisible(true);
                table.setHeaderVisible (true);
                row = Integer.parseInt(textForRows.getText());
                col = Integer.parseInt(textForColumns.getText());

                for (int i = 0; i < col; i ++){
                    tableColumn = new TableColumn(table, SWT.NONE, i);
                    tableColumn.setText("Column" + i);
                    tableColumn.pack();
                    items.add(tableColumn.getText());
                }

                for (int i = 0; i<row; i++) {
                    TableItem tableItem = new TableItem(table, SWT.NONE);
                }

                listener = e -> {};
                columns = table.getColumns();
                tableItems = table.getItems();

                for (TableColumn column : columns) {
                    column.pack();
                    column.setMoveable(true);
                    column.addListener(SWT.Move, listener);
                }

                table.setSize(col*100,row*40 + 30);

                group.pack();
            }
        });

        pushButton1.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0){
                isRunning = true;
                thread1.start();
                thread2.start();
                thread3.start();
            }

        });

        pushButton2.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0) {
                isRunning = false;
                thread1 = createThread1(display);
                thread2 = createThread1(display);
                thread3 = createThread2(display);
            }

        });
    }

    private Thread createThread1(Display display){
        return new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                while (isRunning){
                    display.syncExec(() -> {
                        step1and2();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });
    }

    private Thread createThread2(Display display){
        return new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()){
                while (isRunning) {
                    display.syncExec(() -> {
                        step3();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        });
    }

    private void step1and2() {
        int[] order = table.getColumnOrder();
        int temp = order[order.length - 1];
        if (order.length - 1 >= 0) System.arraycopy(order, 0, order, 1, order.length - 1);
        order[0] = temp;
        table.setColumnOrder(order);
        if (!columns[0].getText().equals("")){
            step = 0;
        }
        else {
            int i = 0;
            while (tableItems[i].getText().equals("")){
                i++;
            }
            step = i + 1;
        }
    }

    private void step3() {
        if(step==0) {
            for(int i = 0; i < columns.length; i++){
                columns[i].setText("");
                tableItems[step].setText(i, items.get(i));
            }
        } else if (step == tableItems.length) {
            for(int i = 0; i < columns.length; i++){
                columns[i].setText(items.get(i));
                tableItems[step - 1].setText(i, "");
            }
        } else {
            for(int i = 0; i < columns.length; i++) {
                tableItems[step].setText(i, items.get(i));
                tableItems[step - 1].setText(i, "");
            }
        }
    }
}

