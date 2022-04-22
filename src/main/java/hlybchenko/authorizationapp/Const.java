package hlybchenko.authorizationapp;

public enum Const {
    USER_TABLE("users"), USERS_ID("idusers"), USERS_FIRSTNAME("firstname"), USERS_LASTNAME("lastname"),
    USERS_USERNAME("username"), USERS_PASSWORD("password"), USERS_LOCATION("location"),USERS_GENDER("gender");
    private final String value;
    Const(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }
}