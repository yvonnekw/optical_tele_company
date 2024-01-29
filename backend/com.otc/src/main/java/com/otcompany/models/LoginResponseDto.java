package com.otcompany.models;

public class LoginResponseDto {
    private CallerUser user;
    private String token;

    public LoginResponseDto(){
        super();
    }

    public LoginResponseDto(CallerUser user, String token){
        this.user = user;
        this.token = token;
    }

    public CallerUser getUser(){
        return this.user;
    }

    public void setUser(CallerUser user){
        this.user = user;
    }

    public String getJwt(){
        return this.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

   
    @Override
    public String toString() {
        return "LoginResponseDTO [user=" + user + ", jwt=" + token + "]";
    }


}
