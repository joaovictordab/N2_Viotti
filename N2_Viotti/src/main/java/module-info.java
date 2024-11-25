module com.mycompany.n2_viotti {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    
    opens com.mycompany.n2_viotti to javafx.fxml;
    exports com.mycompany.n2_viotti;
}
