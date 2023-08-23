package de.fabianmauz.machinelearning.helper;

import de.fabianmauz.machinelearning.TestBase;
import org.ejml.simple.SimpleMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Fabian
 */
public class FunctionsTest extends TestBase {

    @Test
    public void softMax() throws Exception {
        SimpleMatrix M = new SimpleMatrix(new double[][]{new double[]{0.3d, 0.8d, 0.2d}, new double[]{0.1d, 0.9d, 0.1d}});
        SimpleMatrix M_out = Functions.softMax(M);

        Assertions.assertEquals(0.28140804d, M_out.get(0), 0.0001d);
        Assertions.assertEquals(0.46396343d, M_out.get(1), 0.0001d);
        Assertions.assertEquals(0.25462853d, M_out.get(2), 0.0001d);

        Assertions.assertEquals(0.23665609d, M_out.get(3), 0.0001d);
        Assertions.assertEquals(0.52668782d, M_out.get(4), 0.0001d);
        Assertions.assertEquals(0.23665609, M_out.get(5), 0.0001d);

    }

}
