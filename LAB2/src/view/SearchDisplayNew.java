package view;

import controller.Controller;
import model.AllInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;

import java.util.ArrayList;
import java.util.List;

public class SearchDisplayNew {
    public static final int HEIGHT = 30;
    private Shell shell;
    private Parameters parameters;
    public List<AllInfo> items;

    public SearchDisplayNew(Controller control, Display display) {
        items = new ArrayList<>();
        shell = new Shell(display);
        parameters = new Parameters();
        shell.setText("Поиск");
        search(control, display);
        shell.pack();
        shell.open();
    }

    public void search(Controller controller, Display display){
        parameters.setAllLabelsAndTexts(shell, display);

        parameters.chooseByCriteria(shell);

        Button search = new Button(shell, SWT.PUSH);
        search.setText("Найти");
        search.setBounds(15,795,70, HEIGHT);

        search.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                AllInfo item = new AllInfo();
                if(parameters.checkChoiceProductAmount()){
                    parameters.setProductAndAmountOnShell(item);
                    items = controller.searchAmountOrProduct(item);
                } else if(parameters.checkChoiceAddress()){
                    parameters.setAddressOnShell(item);
                    items = controller.searchAddress(item);
                } else if(parameters.checkChoiceMakerUNP()) {
                    parameters.setMakerAndUnpOnShell(item);
                    items = controller.searchMakerOrUNP(item);
                }
                ItemsTable itemsTable = new ItemsTable((ArrayList<AllInfo>) items);
                itemsTable.setTable(shell,display);

                parameters.setNumberOfItems((ArrayList<AllInfo>) items);
            }
        });
    }
}
