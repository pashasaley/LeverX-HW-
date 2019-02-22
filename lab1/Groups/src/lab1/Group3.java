package lab1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;

public class Group3 extends GeneralGroup {
    public Group3() {
        super();

        group.setLocation(500, 50);
        group.setText("Третья группа");

        ArrayList<String> animals = new ArrayList<String>();

        Button pushButton = new Button(group, SWT.NONE);
        pushButton.setText("Нажми на меня");

        Button button1 = new Button(group, SWT.RADIO);
        button1.setText("Собачки");
        animals.add(button1.getText());

        Button button2 = new Button(group, SWT.RADIO);
        button2.setText("Кошечки");
        animals.add(button2.getText());

        Button button3 = new Button(group, SWT.RADIO);
        button3.setText("Енотики");
        animals.add(button3.getText());

        pushButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0) {//подумать здесь
                if(animals.contains(text.getText()) == true) {
                    if (text.getText().equals(button1.getText())) {
                        button1.setSelection(true);
                        button2.setSelection(false);
                        button3.setSelection(false);
                    } else if (text.getText().equals(button2.getText())) {
                        button1.setSelection(false);
                        button2.setSelection(true);
                        button3.setSelection(false);
                    } else if (text.getText().equals(button3.getText())){
                        button1.setSelection(false);
                        button2.setSelection(false);
                        button3.setSelection(true);
                    }
                } else {
                    messageBox.setText("Минуточку");
                    messageBox.setMessage("Такого элемента не существует");
                    messageBox.open();
                }
            }

        });

        group.pack();
    }
}
