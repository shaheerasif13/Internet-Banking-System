module InternetBankingSystem.InternetBankingSystem {
    requires javafx.controls;
    requires javafx.fxml;
	requires java.persistence;
	requires org.hibernate.orm.core;
	requires java.sql;
	requires javafx.graphics;
	requires com.jfoenix;
	requires javafx.base;
	
    opens InternetBankingSystem.InternetBankingSystem to javafx.fxml, org.hibernate.orm.core;
    exports InternetBankingSystem.InternetBankingSystem;
}
