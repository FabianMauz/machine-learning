package de.fabianmauz.machinelearning.data.exporter;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class CsvExporter {
    private final String valueSeperator = ";";
    private final SimpleMatrix matrixToExport;
    
    
    public CsvExporter(SimpleMatrix matrix){
        this.matrixToExport=matrix;
        
    }
    
    public void exportToCsvFile(String fileLocation) throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter(fileLocation, "UTF-8");
        for(int i=0;i<matrixToExport.numRows();i++){
           String [] valuesOfRow=new String[matrixToExport.numCols()];
            for(int j = 0;j<matrixToExport.numCols();j++){
                valuesOfRow[j]=String.valueOf(matrixToExport.get(i, j));
            }
            writer.println(String.join(valueSeperator, valuesOfRow));
            writer.flush();
        }
    }
}
