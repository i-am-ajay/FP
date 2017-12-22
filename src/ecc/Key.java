package ecc;
import java.io.*;
/** */
public interface Key extends Serializable {
    public Key readKey(InputStream in) throws IOException;
    public void writeKey(OutputStream out) throws IOException;
    public Key getPublic();
    public boolean isPublic();
}
