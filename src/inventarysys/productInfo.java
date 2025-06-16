/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventarysys;

/**
 *
 * @author circo
 */
public class productInfo {
    
    //Atributos
    private int code;
    private String description;
    private int quantity;
    private String brand;
    private String type;
    private float buyPrice;
    private float sellPrice;
    
    //Constructor por defecto
    public productInfo(){
        
    }
    
    //Constructor
    public productInfo(productInfo productInfo){
        this.code = productInfo.code;
        this.description = productInfo.description;
        this.quantity = productInfo.quantity;
        this.brand = productInfo.brand;
        this.type = productInfo.type;
        this.buyPrice = productInfo.buyPrice;
        this.sellPrice = productInfo.sellPrice;
    }
    
    //métodos getter
    public int getCode(){
        return code;
    }
    
    public String getDescription(){
        return description;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public String getBrand(){
        return brand;
    }
    
    public String getType(){
        return type;
    }
    
    public float getSellPrice(){
        return sellPrice;
    }
    
    public float getBuyPrice(){
        return buyPrice;
    }
    
    
    //Métodos setter
    public void setCode(int code){
        this.code = code;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    
    public void setBrand(String brand){
        this.brand = brand;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public void setBuyPrice(float buyPrice){
        this.buyPrice = buyPrice;
    }
    
    public void setSellPrice(float sellPrice){
        this.sellPrice = sellPrice;
    }
}
