package view;

import controller.Controller;
import model.AllInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;

import static view.SearchDisplayNew.HEIGHT;

public class DeleteDisplay {
    public static final int CRITERION_FOR_ADDRESS = 3;
    public static final int CRITERION_FOR_MAKER_AND_UNP = 2;
    public static final int CRITERION_FOR_PRODUCT_AND_AMOUNT = 1;
    private Shell shell;
    private Parameters parameters;
    public List<AllInfo> items;

    public DeleteDisplay(Controller controller, Display display, Label num) {
        items = new ArrayList<>();
        shell = new Shell(display);
        parameters = new Parameters();
        shell.setText("Удаление");
        delete(controller,display, num);
        shell.pack();
        shell.open();
    }

    public void delete(Controller controller,Display display, Label num){
        parameters.labelsAndTextAddress(shell);
        parameters.labelsAndTextMakerUnp(shell);
        parameters.labelsAndTextProductAmount(shell);
        parameters.labelsCriterion(shell);

        parameters.chooseByCriteria(shell);

        Button delete = new Button(shell, SWT.PUSH);
        delete.setText("Удалить");
        delete.setBounds(15, 825,70,HEIGHT);

        delete.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                AllInfo item = new AllInfo();
                int count = 0;
                if(parameters.checkChoiceProductAmount()){
                    parameters.setProductAndAmountOnShell(item);
                    count = controller.delete(item, CRITERION_FOR_PRODUCT_AND_AMOUNT, num);
                } else if(parameters.checkChoiceMakerUNP()) {
                    parameters.setMakerAndUnpOnShell(item);
                    count = controller.delete(item, CRITERION_FOR_MAKER_AND_UNP, num);
                } else if(parameters.checkChoiceAddress()) {
                    parameters.setAddressOnShell(item);
                    count = controller.delete(item, CRITERION_FOR_ADDRESS, num);
                }
                parameters.setMessage(shell, count);
                shell.close();
            }
        });
    }
}