package de.fabianmauz.machinelearning.chapter.chapter_6;

import de.fabianmauz.machinelearning.data.IdxReader;
import de.fabianmauz.machinelearning.data.exporter.CsvExporter;
import java.io.IOException;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class Chapter_6_export_test_x {
     static protected String dataLocation = "src/main/resources/chapter_6/t10k-images.idx3-ubyte";
    public static void main(String[] args) throws IOException {
       IdxReader reader = new IdxReader();
       SimpleMatrix X =  reader.importData(dataLocation,10_000,28,false);
        CsvExporter exporter=new CsvExporter(X);
        exporter.exportToCsvFile("mnist_data_X_test.csv");
       
    }
}
