package lab1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.*;

abstract class GeneralGroup implements MainForGroups{
    protected Group group;
    private GridLayout gridLayout;
    private Label label;
    private int style;
    private RowData layoutData;
    protected Text text;
    protected MessageBox messageBox;

    public GeneralGroup() {
        shell.setText("Lab1");
        style = SWT.APPLICATION_MODAL | SWT.OK;
        messageBox = new MessageBox(shell, style);
        group = new Group(shell, SWT.SHADOW_ETCHED_IN);

        gridLayout = new GridLayout();
        gridLayout.marginLeft = 10;
        gridLayout.marginTop = 10;
        group.setLayout(gridLayout);

        label = new Label(group, SWT.NONE);
        label.setText("Введите что угодно, пожалуйста: ");

        text = new Text(group, SWT.BORDER);

        layoutData = new RowData();
        layoutData.width = 150;
    }
}
