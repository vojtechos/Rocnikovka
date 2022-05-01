module cz.spsmb.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens cz.spsmb to javafx.fxml;
    exports cz.spsmb;
}