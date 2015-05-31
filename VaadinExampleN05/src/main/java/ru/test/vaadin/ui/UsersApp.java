package ru.test.vaadin.ui;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.vaadin.dao.UserDao;
import ru.test.vaadin.entity.User;
import ru.test.vaadin.ui.validator.EmailValidator;
import ru.test.vaadin.ui.validator.NotEmptyValidator;

import java.util.List;

/**
 * Author: Georgy Gobozov
 * Date: 20.05.13
 */
@Component
public class UsersApp extends Application {

    private Window mainWindow;
    private Table table;
    private VerticalLayout vLayout;


    protected UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void init() {
        mainWindow = new Window("Test Vaadin application");

        vLayout = new VerticalLayout();
        final Label label = new Label();

        table = new Table("The Brightest Stars");
        table.setWidth("500");

        table.addContainerProperty("ID", Long.class, null);
        table.addContainerProperty("Login", String.class, null);
        table.addContainerProperty("Password", String.class, null);
        table.addContainerProperty("Email", String.class, null);
        // Allow selecting items from the table.
        table.setSelectable(true);
        // Send changes in selection immediately to server.
        table.setImmediate(true);



        table.addListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                if (itemClickEvent.isDoubleClick()) {
                    Object source = itemClickEvent.getItemId();
                    System.out.println(source);
                }
            }
        });

        Button button = new Button("DELETE");
        button.setImmediate(true);
        button.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                if (table.getValue() != null){
                    Integer rowId  = (Integer)table.getValue();
                    Long id = (Long)table.getContainerProperty(rowId, "ID").getValue();
                    userDao.delete(userDao.getById(id));
                    table.removeItem(rowId);


                }
            }
        });


        // Handle selection change.
        table.addListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                label.setValue("Selected: " + table.getValue());
            }
        });

        final User u = new User("", "", "");
        final Form form = new Form();
        form.setCaption("Form with some fields");
        form.setItemDataSource(new BeanItem(u));
        form.setVisibleItemProperties(new Object[]{"login", "password", "email"});
        form.setImmediate(true);
        form.setValidationVisible(true);

        form.setFormFieldFactory(new FormFieldFactory() {
            @Override
            public Field createField(Item item, Object propertyId, com.vaadin.ui.Component component) {
                String pid = (String) propertyId;
                if (pid.equals("login") || pid.equals("password")) {
                    TextField textField = new TextField();
                    textField.setRequired(true);
                    textField.setValidationVisible(true);
//                    textField.setDescription("Field must be not empty");
//                    textField.addValidator(new NotEmptyValidator());
                    return textField;
                }
                if (pid.equals("email")) {
                    TextField textField = new TextField();
                   // textField.setRequired(true);
                    textField.setValidationVisible(true);
                    textField.addValidator(new EmailValidator());
                    return textField;

                }

                return null;
            }
        });



        Button save = new Button("Save");

        save.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                form.validate();
                form.commit();
                userDao.create(u);
                populateTable();
            }
        });
        form.getFooter().addComponent(save);

        vLayout.addComponent(label);
        vLayout.addComponent(table);
        vLayout.addComponent(button);
        vLayout.addComponent(form);


        mainWindow.addComponent(vLayout);
        populateTable();

        setMainWindow(mainWindow);

    }

    private void populateTable() {
        System.out.println("Populate table");
        table.removeAllItems();
        List<User> users = userDao.getAll();
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            table.addItem(new Object[]{u.getUserId(), u.getLogin(), u.getPassword(), u.getEmail()}, new Integer(i + 1));
        }
    }


}
