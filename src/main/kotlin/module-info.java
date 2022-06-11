module edu.mailman.kotlinjavafxpendulum2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens edu.mailman.kotlinjavafxpendulum2 to javafx.fxml;
    exports edu.mailman.kotlinjavafxpendulum2;
}