package lab1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;

public class Group4 {
    private Text text;
    private MessageBox messageBox;

    public Group4 (Shell shell) {
        int style = SWT.APPLICATION_MODAL | SWT.OK;
        messageBox = new MessageBox(shell, style);
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

        group.setLocation(900, 50);
        group.setText("Четвертая группа");

        ArrayList<String> animals = new ArrayList<String>();

        Button pushButton = new Button(group, SWT.NONE);
        pushButton.setText("Нажми на меня");

        Button button1 = new Button(group, SWT.CHECK);
        button1.setText("Собачки");
        animals.add(button1.getText());

        Button button2 = new Button(group, SWT.CHECK);
        button2.setText("Кошечки");
        animals.add(button2.getText());

        Button button3 = new Button(group, SWT.CHECK);
        button3.setText("Енотики");
        animals.add(button3.getText());

        pushButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                if (animals.contains(text.getText())) {
                    if (text.getText().equals(button1.getText())) {
                        button1.setSelection(true);
                    } else if (text.getText().equals(button2.getText())) {
                        button2.setSelection(true);
                    } else if (text.getText().equals(button3.getText())){
                        button3.setSelection(true);
                    }
                } else {
                    messageBox.setText("Минуточку");
                    messageBox.setMessage("Такой элемент не существует");
                    messageBox.open();
                }
            }
        });

        group.pack();
    }
}
