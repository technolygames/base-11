package clases;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class metodosExternos{
    public Image getImage(byte[] bytes,boolean isThumbnail) throws IOException{
        ByteArrayInputStream bais=new ByteArrayInputStream(bytes);
        Iterator readers=ImageIO.getImageReadersByFormatName("");
        ImageReader reader=(ImageReader)readers.next();
        Object source=bais; // File or InputStream
        ImageInputStream iis=ImageIO.createImageInputStream(source);
        reader.setInput(iis,true);
        ImageReadParam param=reader.getDefaultReadParam();
        if(isThumbnail){
            param.setSourceSubsampling(4,4,0,0);
        }
        
        return reader.read(0,param);
    }
}