package AddEntriesToDB;

import ecc.Key;
import ecc.elliptic.*;
import ecc.io.CryptoInputStream;
import ecc.io.CryptoOutputStream;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;

/**
 * Created by ajay on 2/7/2016.
 */
public class CryptRules{
    EllipticCurve ec;
    ECCryptoSystem cs;
    Key sk;
    Key pk;
    transient byte [] inputByteArray;
    ByteArrayInputStream byteInput;
    ByteArrayOutputStream byteOutput;
    public CryptRules(){
        try {
            ec = new EllipticCurve(new secp256r1());
            cs = new ECCryptoSystem(ec);
            sk = generateKey(cs);                                 //cs.generateKey();
            pk = sk.getPublic();
        } catch (InsecureCurveException e) {
            e.printStackTrace();
        }

    }
    public String decrypt(byte[] byteArray) throws IOException {
        return decrypt(cs,sk,pk,byteArray);
    }
    public String decrypt(ECCryptoSystem cs,Key sk, Key pk,byte[] byteArray) throws IOException {

        byteInput = new ByteArrayInputStream(byteArray);
        inputByteArray = new byte[byteArray.length];
        CryptoInputStream in = new CryptoInputStream(byteInput,cs,sk);
        in.read(inputByteArray);
        /*PrintWriter writer = new PrintWriter(System.out);
        int read=0;*/
        String stri = new String(inputByteArray);
        return stri;
    }
    public byte[] encrypt(String rule) throws IOException {
        return encrypt(cs,sk,pk,rule);
    }
    public byte[] encrypt(ECCryptoSystem cs,Key sk, Key pk,String rule) throws IOException {
        byte [] strByteArray = rule.getBytes();
        byteOutput = new ByteArrayOutputStream();
        CryptoOutputStream out = new CryptoOutputStream(byteOutput, cs, pk,rule.length());
        for (byte b: strByteArray){
            out.write(b);
        }
        out.flush();

        return byteOutput.toByteArray();
    }

    byte [] array = null;
    public  void main(EncryptionFlag val,String rule,byte [] ar){
        if (val == EncryptionFlag.ENCRYPT ){
            try {
                array = encrypt(cs,sk,pk,rule);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if ( val == EncryptionFlag.DECRYPT){
            ar = array;
            try {
                System.out.println(decrypt(cs, sk, pk, ar));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String [] args){
        CryptRules rules = new CryptRules();
        rules.main(EncryptionFlag.ENCRYPT,"Hello World",new byte[1000]);
        rules.main(EncryptionFlag.DECRYPT,"Hello World",new byte[10]);
    }
    private Key generateKey(ECCryptoSystem cs){
        Connection con = null;
        Key key = null;
        boolean resultSetEmpty = true;
        try {
            con = DBConnection.connection("rulesdb");
            System.out.println(con);
            PreparedStatement stmt = con.prepareStatement("select * from eccKeys");
            ResultSet set = stmt.executeQuery();
            while(set.next()){
                resultSetEmpty = false;
                Blob b = set.getBlob(1);
                InputStream stream = b.getBinaryStream();
                Key gotKey = new ECKey(ec).readKey(stream);
                key = gotKey;
            }
            if(resultSetEmpty){
                key = cs.generateKey();
                PreparedStatement insertStmt = con.prepareStatement("insert into eccKeys values (?)");
                Blob blob = con.createBlob();
                OutputStream outStream = blob.setBinaryStream(1);
                new ECKey(ec).writeKey(outStream);
                outStream.close();
                insertStmt.setObject(1, blob);
                insertStmt.executeUpdate();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return key;
    }
}

enum EncryptionFlag{
    ENCRYPT,DECRYPT;
}
