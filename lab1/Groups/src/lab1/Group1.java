package lab1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;

public class Group1 {
    private Text text;
    private MessageBox messageBox;
    private ArrayList<String> items;
    private Combo combo;

    public Group1(Shell shell) {
        int style = SWT.APPLICATION_MODAL | SWT.OK;
        messageBox = new MessageBox(shell, style);

        Group group = new Group(shell, SWT.SHADOW_ETCHED_IN);
        group.setLocation(50, 50);
        group.setText("Первая группа");

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

        Button button1 = new Button(group, SWT.NONE);
        button1.setText("Добавить в ComboBox");

        combo = new Combo(group, SWT.DROP_DOWN);
        items = new ArrayList<String>();

        button1.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                if(items.contains(text.getText())){
                    messageBox.setText("Минуточку");
                    messageBox.setMessage("Такой элемент уже существует");
                    messageBox.open();
                } else {
                    combo.add(text.getText());
                    items.add(text.getText());
                }
            }
        });
        group.pack();
    }
}
