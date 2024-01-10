module trello.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;

    opens treulo.src to javafx.fxml;
    exports treulo.src;
}