package keys;

import AddEntriesToDB.DBConnection;

import java.sql.*;
import java.util.Random;

/**
 * Created by ajay on 4/20/2016.
 */
public class AuthKey implements Key {
    private char[] keyChars;
    int range = 122;
    private Random rand;
    /*
    * This method will generate a new unique key for the user.
    * */
    @Override
    public String generateKey() {
        keyChars = new char[20];
        rand = new Random();
        setRand(rand);
        for(int i = 0;i<keyChars.length;i++){
            int num = rand.nextInt(range);
            char c = ' ';
            if((num >=65 && num <= 90) || (num>=97 && num<=122)) {
                byte byteNum = new Integer(num).byteValue();
                c = ((char) byteNum);
            }
            else{
                c = convertToChar(num);
            }
            keyChars[i] = c;
        }
        String key = new String(keyChars);
        try {
            key = checkORConvertKey(key);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return key;
    }
    /*
    * Method convert the numbers to some specified characters that are allowed in the keys, but not alphabets.
    * */
    private char convertToChar(int num){
        char character;
        switch(num) {
            case 91:
                character = '@';
                break;
            case 92:
                character = '$';
                break;
            case 93:
                character = '!';
                break;
            case 94:
                character = '#';
                break;
            case 95:
                character = '%';
                break;
            case 96:
                character = '&';
                break;
            default:
                //creates a digit character.
                int num1 = 57 - getRand().nextInt(9);
                character = (char)num1;

        }
        return character;
    }

    @Override
    public void showKey() {

    }

    @Override
    public void transferKey() {

    }

    public char[] getKeyChars() {
        return keyChars;
    }

    private void setKeyChars(char[] keyChars) {
        this.keyChars = keyChars;
    }
    /*
      * Method will first check if key is Unique, if not then it will make key unique by shuffling few chars and will
      * return the unique Key.
    */
    private String checkORConvertKey(String key) throws SQLException {
        String copyKey = key;
        boolean loopTill = true;
        while(loopTill) {
            if (isKeyUnique(copyKey)) {
                System.out.println("True");
                break;
            }
            else{
                copyKey = makeAUniqueKey(copyKey);
            }
        }
        return copyKey;
    }
    /*
    * Check if key is unique by comparing database keys and if it is then add it to UserKeys Table.
    * */
    private boolean isKeyUnique(String key) throws SQLException {
        Connection con = DBConnection.getConnection();
        System.out.println(con.toString());
        CallableStatement statment = con.prepareCall("call add_Key_if_unique(?,?)");
        statment.setString(1,key);
        statment.registerOutParameter(2,Types.BOOLEAN);
        boolean hasresult =statment.execute();
        boolean value = true;
        while(hasresult){
            ResultSet set = statment.getResultSet();
            value = set.getBoolean(1);
            break;
        }
        return value;
    }
    /*
    * If key is already in database then this method will shuffle few characters of key and try to make it unique. Generated
    * key will be compared again for uniqueness.
    * */
    private String makeAUniqueKey(String key){
        char [] oldKey = key.toCharArray();
        int firstIndex = getRand().nextInt(oldKey.length);
        int secondIndex = 0;
        while(true) {
            secondIndex = getRand().nextInt(oldKey.length);
            if(firstIndex != secondIndex){
                break;
            }
            else{
                secondIndex = getRand().nextInt(oldKey.length);
            }
        }
        char firstChar = oldKey[firstIndex];
        char secondChar = oldKey[secondIndex];
        oldKey[firstIndex]=secondChar;
        oldKey[secondIndex]=firstChar;
        return String.valueOf(oldKey);
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }
}

