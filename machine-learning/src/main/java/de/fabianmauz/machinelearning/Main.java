
package de.fabianmauz.machinelearning;

import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Fabian
 */
public class Main {

    public static void main(String[] args){
       double[][] array2D = new double[][]{
           new double[]{1,2,3},
           new double[]{4,5,6},
           new double[]{7,8,9},
       };
       SimpleMatrix matrix = new SimpleMatrix(array2D);
       System.out.println(matrix);
    }
}
