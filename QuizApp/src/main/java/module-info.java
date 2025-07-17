module com.lhbt.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires mysql.connector.j;

    opens com.lhbt.quizapp to javafx.fxml;
    exports com.lhbt.quizapp;
    exports com.lhbt.pojo;
}
