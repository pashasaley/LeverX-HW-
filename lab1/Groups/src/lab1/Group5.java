package lab1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.*;

public class Group5 {
    private Text text;
    private TableColumn col1;
    private TableColumn col2;

    public Group5(Shell shell) {
        Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);

        GridLayout gridLayout = new GridLayout();
        gridLayout.marginRight = 10;
        gridLayout.marginLeft = 10;
        gridLayout.marginTop = 10;
        gridLayout.marginBottom = 10;
        group.setLayout(gridLayout);

        Label label = new Label(group, SWT.NONE);
        label.setText("Введите что угодно, пожалуйста: ");

        text = new Text(group, SWT.BORDER);

        RowData layoutData = new RowData();
        layoutData.width = 150;

        group.setLocation(1100, 300);
        group.setText("Пятая группа");

        Button button1 = new Button(group, SWT.NONE);
        button1.setText("Занести в первый столбец");

        Button button2 = new Button(group, SWT.NONE);
        button2.setText("Перенести во второй столбец");

        Button button3 = new Button(group, SWT.NONE);
        button3.setText("Перенести в первый столбец");

        Table table = new Table(group, SWT.BORDER);

        col1 = new TableColumn(table, SWT.CENTER);
        col2 = new TableColumn(table, SWT.CENTER);
        col1.setWidth(70);
        col2.setWidth(70);
        table.setHeaderVisible(true);

        button1.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                col1.setText(text.getText());
            }

        });

        button2.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                col2.setText(!col1.getText().equals("") ? col1.getText() : col2.getText());
                if (!col2.getText().equals("")) {
                    col1.setText("");
                }
            }

        });

        button3.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                col1.setText(!col2.getText().equals("") ? col2.getText() : col1.getText());
                if (!col1.getText().equals("")) {
                    col2.setText("");
                }
            }
        });

        group.pack();
    }
}
