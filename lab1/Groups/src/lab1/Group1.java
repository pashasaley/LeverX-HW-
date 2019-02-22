package lab1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;

public class Group1 extends GeneralGroup {
    public Group1() {
        super();

        group.setLocation(50, 50);
        group.setText("Первая группа");

        Button button1 = new Button(group, SWT.NONE);
        button1.setText("Добавить в ComboBox");

        Combo combo = new Combo(group, SWT.DROP_DOWN);
        ArrayList<String> items = new ArrayList<String>();

        button1.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                if(items.contains(text.getText()) == true){
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
