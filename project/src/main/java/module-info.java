module trello.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens treulo.src to javafx.fxml;
    exports treulo.src;
}