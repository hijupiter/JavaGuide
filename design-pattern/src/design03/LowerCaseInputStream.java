package design03;


import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LowerCaseInputStream extends FilterInputStream {

    public LowerCaseInputStream(InputStream in){
        super(in);
    }

    public int read() throws IOException{
        int c = super.read();
        return(c==-1?c:Character.toLowerCase((char)c));
    }


}
