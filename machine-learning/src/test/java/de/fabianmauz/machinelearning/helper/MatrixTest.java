package de.fabianmauz.machinelearning.helper;

import de.fabianmauz.machinelearning.TestBase;
import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Fabian
 */
public class MatrixTest extends TestBase {

    @Test
    public void addBias() throws Exception {
        SimpleMatrix M = new SimpleMatrix(new double[][]{new double[]{1, 2}, new double[]{3, 4}});
        M.concatColumns(M);
        SimpleMatrix M_with_bias = Matrix.addBias(M);

        Assertions.assertTrue(M.numCols() == 2);
        Assertions.assertTrue(M_with_bias.numCols() == 3);
    }

}
