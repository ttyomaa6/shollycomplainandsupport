package com.example.shollycomplainandsupport.helpClasses;

public class ComplainResponse {
    private String response;
    Complain complain;
    String uid;

    public ComplainResponse(String response,Complain complain,String uid){
        this.response=response;
        this.complain=complain;
        this.uid=uid;

    }
    public ComplainResponse(){
    }

    public String  getResponsen(){
        return this.response;
    }

    public void setResponse(String  response){
        this.response=response;
    }

    public Complain  getComplain(){
        return this.complain;
    }

    public void setComplain(Complain  complain){
        this.complain=complain;
    }

    public String  getUid(){
        return this.uid;
    }

    public void setUid(String  uid){
        this.uid=uid;
    }
}
