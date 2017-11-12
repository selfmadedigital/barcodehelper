package barcodehelper.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
 
public class Product {
   private SimpleStringProperty productEan = new SimpleStringProperty("");
   private SimpleStringProperty productName = new SimpleStringProperty("");
   private SimpleIntegerProperty productQuantity = new SimpleIntegerProperty(0);

    public Product(String productEan, String productName, Integer productQuantity) {
        setProductEan(productEan);
        setProductName(productName);
        setProductQuantity(productQuantity);
    }
    
    public Product() {
    		//empty product
    }

	public String getProductEan() {
		return productEan.get();
	}

	public void setProductEan(String ean) {
		productEan.set(ean);
	}

	public String getProductName() {
		return productName.get();
	}

	public void setProductName(String name) {
		productName.set(name);
	}
	
	public Integer getProductQuantity() {
		return productQuantity.get();
	}

	public void setProductQuantity(Integer quantity) {
		productQuantity.set(quantity);
	}
}
