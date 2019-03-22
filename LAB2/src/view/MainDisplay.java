package view;

import controller.Controller;
import model.AllInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.*;

//модельная структура - есть
//переименовка - есть
//

import java.util.ArrayList;

import static dao.InputData.saveFile;
import static dao.ReadData.openFile;

public class MainDisplay {

    public Display display;
    public Shell shell;
    private Controller controller = new Controller();
    private Label num;

    public MainDisplay(){
        display = new Display();
        shell = new Shell(display);
        shell.setText("Lab2");

        mainWindow();

        shell.open();
        while(!shell.isDisposed()){
            if(!display.readAndDispatch()){
                display.sleep();
            }
        }
        display.dispose();
    }

    public void mainWindow() {
        Label numOfNotes = new Label(shell, SWT.NONE);
        numOfNotes.setText("Всего записей: ");
        numOfNotes.setBounds(15, 400, 140, 40);

        num = new Label(shell, SWT.CENTER);
        num.setBackground(display.getSystemColor(SWT.COLOR_WHITE));
        num.setBounds(15, 440, 40, 40);

        popupMenu();

        Button buttonOpenFile = new Button(shell, SWT.PUSH);
        buttonOpenFile.setBounds(15,15,48,48);
        buttonOpenFile.setImage(new Image(display, "images/open.png" ));
        buttonOpenFile.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                openFile(shell,controller);
                num.setText(String.valueOf(controller.allItems.getItems().size()));
            }
        });

        Button buttonSaveFile = new Button(shell, SWT.PUSH);
        buttonSaveFile.setBounds(15,80,48,48);
        buttonSaveFile.setImage(new Image(display, "images/save.png" ));
        buttonSaveFile.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                saveFile(shell, controller);
            }
        });

        Button buttonAddNewStudent = new Button(shell, SWT.PUSH);
        buttonAddNewStudent.setBounds(15,145,48,48);
        buttonAddNewStudent.setImage(new Image(display, "images/add.png" ));
        buttonAddNewStudent.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new AddDisplay(controller, display, num);
            }
        });

        Button buttonDelete = new Button(shell, SWT.PUSH);
        buttonDelete.setBounds(15,210,48,48);
        buttonDelete.setImage(new Image(display, "images/delete.png" ));
        buttonDelete.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new DeleteDisplay(controller, display, num);
            }
        });

        Button buttonSearch = new Button(shell, SWT.PUSH);
        buttonSearch.setBounds(15,275,48,48);
        buttonSearch.setImage(new Image(display, "images/search.png" ));
        buttonSearch.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new SearchDisplayNew(controller, display);
            }
        });

        ItemsTable studentsTable = new ItemsTable((ArrayList<AllInfo>)
                controller.allItems.getItems());
        studentsTable.setTable(shell, display);
    }

    public void popupMenu(){
        Menu menubar = new Menu(shell, SWT.BAR);
        Menu menuForFiles = new Menu(shell, SWT.DROP_DOWN);
        Menu menuForEditFile = new Menu(shell, SWT.DROP_DOWN);
        MenuItem file = new MenuItem(menubar, SWT.CASCADE);
        MenuItem edit = new MenuItem(menubar, SWT.CASCADE);
        MenuItem open = new MenuItem(menuForFiles, SWT.PUSH);
        MenuItem save = new MenuItem(menuForFiles, SWT.PUSH);
        MenuItem separator = new MenuItem(menuForFiles,SWT.SEPARATOR);
        MenuItem exit = new MenuItem(menuForFiles, SWT.PUSH);
        MenuItem add = new MenuItem(menuForEditFile, SWT.PUSH);
        MenuItem find = new MenuItem(menuForEditFile, SWT.PUSH);
        MenuItem remove = new MenuItem(menuForEditFile, SWT.PUSH);
        shell.setMenuBar(menubar);

        file.setText("Файл");
        file.setMenu(menuForFiles);
        open.setText("Открыть");
        open.addListener(SWT.Selection, event -> openFile(shell, controller));
        save.setText("Сохранить");
        save.addListener(SWT.Selection, event -> saveFile(shell, controller));
        exit.setText("Выход");
        exit.addListener(SWT.Selection, event -> System.exit(0));

        edit.setText("Редактировать");
        edit.setMenu(menuForEditFile);
        add.setText("Добавить");
        add.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new AddDisplay(controller, display, num);
            }
        });
        find.setText("Найти");
        find.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new SearchDisplayNew(controller, display);
            }
        });
        remove.setText("Удалить");
        remove.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent e) {
                new DeleteDisplay(controller, display, num);
            }
        });
    }
}
