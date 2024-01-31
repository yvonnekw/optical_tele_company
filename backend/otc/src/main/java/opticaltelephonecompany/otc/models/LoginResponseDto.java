package opticaltelephonecompany.otc.models;

public class LoginResponseDto {
    private CallUser user;
    private String token;

    public LoginResponseDto(){
        super();
    }

    public LoginResponseDto(CallUser user, String token){
        this.user = user;
        this.token = token;
    }

    public CallUser getUser(){
        return this.user;
    }

    public void setUser(CallUser user){
        this.user = user;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginResponseDto [user=" + user + ", token=" + token + "]";
    }


}
