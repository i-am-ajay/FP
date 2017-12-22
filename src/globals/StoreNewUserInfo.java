package globals;

import java.util.UUID;

/**
 * Created by ajay on 6/6/2016.
 */
public class StoreNewUserInfo {
    private String userName;
    private String password;
    private String key;
    private String permission;
    private UUID id;
    private String masterKey;
    long userId;
    private String email;

    public StoreNewUserInfo(){
        setID();
    }
    public void setUserName(String name){
        userName = name;
    }
    public String getUserName(){
        return userName;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }

    public void setMasterKey (String masterKey){
        this.masterKey = masterKey;
    }
    public String getMasterKey(){
        return masterKey;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }

    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return key;
    }

    public void setPermission(String rule){
        this.permission = rule;
    }
    public String getPermission(){
        return permission;
    }

    private void setID(){
        id = UUID.randomUUID();
    }
    public UUID getID(){
        return id;
    }
}
