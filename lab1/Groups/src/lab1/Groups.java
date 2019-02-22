package lab1;

public class Groups implements MainForGroups {
    public static void main(String ... args) {
        GeneralGroup group1 = new Group1();
        GeneralGroup group2 = new Group2();
        GeneralGroup group3 = new Group3();
        GeneralGroup group4 = new Group4();
        GeneralGroup group5 = new Group5();

        shell.pack();
        shell.open();

        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        display.dispose ();
    }
}
