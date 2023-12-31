/*
 * Cloud Resource & Information Management System (CRIMSy)
 * Copyright 2021 Leibniz-Institut f. Pflanzenbiochemie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.fabianmauz.machinelearning.data.exporter;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Test;

/**
 *
 * @author fmauz
 */
public class CsvExporterTest {
    
    @Test
    public void exportMatrix_to_csv() throws FileNotFoundException, UnsupportedEncodingException{
        SimpleMatrix M =new SimpleMatrix(new double[][]{
            new double[]{1,2,4,5},
            new double[]{6,7,8,9},
            new double[]{10,11,12,13},
        });
        
        CsvExporter exporter =new CsvExporter(M);
        exporter.exportToCsvFile("target/exportMatrix_to_csv.csv");
    }
}
