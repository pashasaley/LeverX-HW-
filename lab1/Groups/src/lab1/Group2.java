package lab1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.*;

public class Group2{
    private Text text;

    public Group2(Shell shell) {
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

        group.setLocation(50, 250);
        group.setText("Вторая группа");

        Button button1 = new Button(group, SWT.NONE);
        button1.setText("Нажми на меня");

        Button button2 = new Button(group, SWT.NONE);
        button2.setText("Скоро я изменюсь");

        button1.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                button2.setText(text.getText());
            }

        });

        button2.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                String temp = button1.getText();
                button1.setText(button2.getText());
                button2.setText(temp);
            }

        });

        group.pack();
    }
}
