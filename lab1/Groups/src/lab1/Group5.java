package lab1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

public class Group5 extends GeneralGroup {
    public Group5() {
        super();

        group.setLocation(700, 300);
        group.setText("Пятая группа");

        Button button1 = new Button(group, SWT.NONE);
        button1.setText("Занести в первый столбец");

        Button button2 = new Button(group, SWT.NONE);
        button2.setText("Перенести во второй столбец");

        Button button3 = new Button(group, SWT.NONE);
        button3.setText("Перенести в первый столбец");

        Table t = new Table(group, SWT.BORDER);

        TableColumn tc1 = new TableColumn(t, SWT.CENTER);
        TableColumn tc2 = new TableColumn(t, SWT.CENTER);
        tc1.setWidth(70);
        tc2.setWidth(70);
        t.setHeaderVisible(true);

        button1.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                tc1.setText(text.getText());
            }

        });

        button2.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                tc2.setText(tc1.getText());
                tc1.setText("");
            }

        });

        button3.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                tc1.setText(tc2.getText());
                tc2.setText("");
            }
        });

        group.pack();
    }
}
