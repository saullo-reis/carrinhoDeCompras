package com.cart.carrinhoDeCompras;
import java.util.List;


public class Response<T>{
    private String message;
    private String status;
    private T data;
    private List<T> arrayData;


    public Response (String  message, String status, T data, List<T> arrayData){
        this.message = message;
        this.status = status;
        this.data = data;
        this.arrayData = arrayData;
    }
    public void setArrayData(List<T> arrayData){
        this.arrayData = arrayData;
    }
    public List<T> getArrayData(){
        return arrayData;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}

