module com.lhbt.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.lhbt.quizapp to javafx.fxml;
    exports com.lhbt.quizapp;
}
