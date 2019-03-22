package view;

import controller.Controller;
import model.AllInfo;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

public class AddDisplay {
    private Shell shell;
    private Parameters classToAvoidDuplicates;

    public AddDisplay(Controller controller, Display display, Label num) {
        classToAvoidDuplicates = new Parameters();
        shell = new Shell(display);
        shell.setText("Добавить запись");
        shell.setLayout(new GridLayout());
        addDisplay(controller, num);
        shell.open();
    }

    public void addDisplay(Controller controller, Label num){
        classToAvoidDuplicates.labelsAndTextProductAmount(shell);
        classToAvoidDuplicates.labelsAndTextMakerUnp(shell);
        classToAvoidDuplicates.labelsAndTextAddress(shell);

        Button buttonAdd = new Button(shell, SWT.PUSH);
        buttonAdd.setText("Добавить");

        buttonAdd.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e){

                if(classToAvoidDuplicates.checkIfFilled()){
                    MessageBox WAR = new MessageBox(shell);
                    WAR.setMessage("Остались незаполненные поля");
                    WAR.open();
                }
                else{
                    AllInfo record = new AllInfo();
                    classToAvoidDuplicates.setMakerAndUnpOnShell(record);
                    classToAvoidDuplicates.setProductAndAmountOnShell(record);
                    classToAvoidDuplicates.setAddressOnShell(record);
                    controller.add(record);
                    num.setText(String.valueOf(controller.allItems.getItems().size()));

                    MessageBox message = new MessageBox(shell);
                    message.setMessage("Запись добавлена");
                    message.open();
                    shell.close();
                }
            }
        });

        Button buttonAbort = new Button(shell, SWT.PUSH);
        buttonAbort.setBounds(530,575,110,45);
        buttonAbort.setText("Отмена");
        buttonAbort.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });

        shell.pack();
    }
}
