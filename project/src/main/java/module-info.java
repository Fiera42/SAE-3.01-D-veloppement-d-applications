module trello.project {
    requires javafx.controls;
    requires javafx.fxml;

    opens trello.src to javafx.fxml;
    exports trello.src;
}