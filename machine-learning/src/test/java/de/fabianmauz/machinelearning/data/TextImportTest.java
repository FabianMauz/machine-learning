/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package de.fabianmauz.machinelearning.data;

import de.fabianmauz.machinelearning.TestBase;
import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Fabian
 */
public class TextImportTest extends TestBase {

    TextImport importer;

    @Test
    public void importDataFromTextFile() throws Exception {
        SimpleMatrix expectedXMatrix = new SimpleMatrix(new double[][]{
            new double[]{1, 2, 3, 4, 1},
            new double[]{6, 7, 8, 9, 1},
            new double[]{11, 12, 13, 14, 1},
            new double[]{16, 17, 18, 19, 1}
        });
        SimpleMatrix expectedYMatrix = new SimpleMatrix(new double[][]{
            new double[]{5},
            new double[]{10},
            new double[]{15},
            new double[]{20}
        });

        importer = new TextImport(rootFolder + "demo_data.txt", new int[]{0, 1, 2, 3}, new int[]{4}, 0, " ",true);
        SimpleMatrix[] loadedMatrices = importer.importData();
        Assertions.assertTrue(expectedXMatrix.isIdentical(loadedMatrices[0], 0.01d));
        Assertions.assertTrue(expectedYMatrix.isIdentical(loadedMatrices[1], 0.01d));
    }

}
