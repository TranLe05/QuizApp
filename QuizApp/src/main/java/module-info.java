module com.lhbt.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires lombok;
    requires mysql.connector.j;

    opens com.lhbt.quizapp to javafx.fxml;
    exports com.lhbt.quizapp;
}
