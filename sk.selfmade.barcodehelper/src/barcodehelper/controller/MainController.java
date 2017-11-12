package barcodehelper.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import barcodehelper.model.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainController {
	
	private boolean print;
	private int previousCount;
	private int count;
	private String ean;
	private String productsCsv;
	private HashMap<String, String> productsList = new HashMap<String, String>();
	private ArrayList<String> scannedProducts = new ArrayList<String>(); 

    @FXML
    private TextField textCode;
    
    @FXML
    private TableView<Product> productTable = new TableView<Product>();
    
    @FXML
    protected void updateTable(Product product) {
    		boolean update = false;
        ObservableList<Product> data = productTable.getItems();
        for (Product prod : data) {
			if(prod.getProductEan().equals(product.getProductEan())) {
				prod.setProductQuantity(prod.getProductQuantity() + product.getProductQuantity());
				update = true;
				productTable.getColumns().get(0).setVisible(false);
				productTable.getColumns().get(0).setVisible(true);
				break;
			}
		}
        if(!update) {
        		data.add(product);
        }
        textCode.setText("");
    }

    @FXML
    private void initialize() {
    		print = false;
    }
    
    public void addProduct() throws Exception {    	
    		if(productsList.isEmpty()) {
    			assignProducts();
    		}
    	
    		String code = textCode.getText(); 
    		if(code != null && !code.isEmpty()) {
    			Product product = new Product();
    			
    			if(code.startsWith("-")) {
    				//vratene
    				code = code.substring(1, code.length());
    			}
    			
    			if(code.indexOf('/') >= 0) {
    				print = true;
    				String[] codeParts = code.split("/");
    				previousCount = Integer.parseInt(codeParts[0]);
    				code = codeParts[1];
    			}
    			
    			if(code.indexOf('*') >= 0) {
    				String[] codeParts = code.split("\\*");
    				count = Integer.parseInt(codeParts[0]);
    				code = codeParts[1];
    			}else {
    				count = 1;
    			}
    			
    			ean = code;
    			
    			String productName = productsList.get(ean);
        		if(productName != null) {
        			if(print) {
            			//previous count print
            		}
        			
        			product.setProductEan(ean);
        			product.setProductName(productName);
        			product.setProductQuantity(count);
        		}else {
        			throw new Exception("missing product");
        		}
        		
        		scannedProducts.add(ean);
        		updateTable(product);
        		
        		if(print) {
        			System.out.println("previous" + previousCount);
        		}
    		}else {
    			throw new Exception("invalid input");
    		}
    }
    
    public void clearTable() {
    		System.out.println("clear");
    }
    
    public void setProductsCsv(String productsCsv) {
		this.productsCsv = productsCsv;
	}
    
    public void assignProducts() throws Exception{
    		if(productsCsv != null) {
    			BufferedReader br = null;
    			String line = "";
    			try {
    				br = new BufferedReader(new FileReader(productsCsv));
    	            while ((line = br.readLine()) != null) {
    	                String[] product = line.split(";");
    	                productsList.put(product[0], product[1]);
    	            }
    	        } catch (FileNotFoundException e) {
    	            e.printStackTrace();
    	        } catch (IOException e) {
    	            e.printStackTrace();
    	        } finally {
    	            if (br != null) {
    	                try {
    	                    br.close();
    	                } catch (IOException e) {
    	                    e.printStackTrace();
    	                }
    	            }
    	        }
    		}else {
    			throw new Exception("missing csv");
    		}
    }
}