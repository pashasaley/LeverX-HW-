/*
 * Галай Александра Дмитриевна, гр.721702
 * Лабораторная работа 1
 * библиотека SWT
 */

package lab1;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Groups {
    public static void main(String ... args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Lab1");

        Group1 group1 = new Group1(shell);
        Group2 group2 = new Group2(shell);
        Group3 group3 = new Group3(shell);
        Group4 group4 = new Group4(shell);
        Group5 group5 = new Group5(shell);
        Group6 group6 = new Group6(shell, display);

        group6.buttons(display);

        shell.pack();
        shell.open();

        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
    }
}
