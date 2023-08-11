package de.fabianmauz.machinelearning.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class IdxReader {
    public SimpleMatrix importData(String fileLocation,int observations,int heightInPixel,boolean addBias) throws IOException{      
        byte[] tempBuffer = new byte[16];
        InputStream targetStream = new FileInputStream(fileLocation);
       
        targetStream.read(tempBuffer, 0, 16);
        byte[] dataBuffer = new byte[1];
  
        float[][] images;
        if(addBias){
               images =  new float[observations][heightInPixel*heightInPixel+1];
        }else{
               images =  new float[observations][heightInPixel*heightInPixel];
        }

        for (int i = 0; i < observations; i++){
            
             for (int j = 0; j < heightInPixel*heightInPixel; j++){
                 targetStream.read(dataBuffer, 0, 1);
                 float pixelVal = (dataBuffer[0] & 0xFF);
                 images[i][j] = pixelVal;
             }
        }
        return new SimpleMatrix(images);
    }
}
