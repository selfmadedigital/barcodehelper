package barcodehelper.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
 
public class Product {
   private SimpleStringProperty productEan = new SimpleStringProperty("");
   private SimpleStringProperty productName = new SimpleStringProperty("");
   private SimpleIntegerProperty productQuantity = new SimpleIntegerProperty(0);
   private SimpleBooleanProperty isReturningProduct = new SimpleBooleanProperty(false);

    public Product(String productEan, String productName, Integer productQuantity, Boolean isReturning) {
        setProductEan(productEan);
        setProductName(productName);
        setProductQuantity(productQuantity);
        setIsReturningProduct(isReturning);
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
	
	public Boolean getIsReturningProduct() {
		return isReturningProduct.get();
	}
	
	public void setIsReturningProduct(Boolean isReturning) {
		isReturningProduct.set(isReturning);
	}
}
