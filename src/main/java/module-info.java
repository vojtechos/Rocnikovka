module cz.spsmb.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;


    opens cz.spsmb to javafx.fxml;
    exports cz.spsmb;
}