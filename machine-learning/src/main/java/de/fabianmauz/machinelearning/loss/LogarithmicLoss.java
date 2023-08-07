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
package de.fabianmauz.machinelearning.loss;

import static de.fabianmauz.machinelearning.helper.Matrix.average;
import static de.fabianmauz.machinelearning.helper.Matrix.crossProduct;
import de.fabianmauz.machinelearning.predict.Predict;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class LogarithmicLoss implements Loss {

    private final SimpleMatrix X;
    private final SimpleMatrix Y;
    private final SimpleMatrix w;
    Predict predictor;

    public LogarithmicLoss(SimpleMatrix X, SimpleMatrix Y, SimpleMatrix w, Predict p) {
        this.X = X;
        this.Y = Y;
        this.w = w;
        this.predictor = p;
    }

    @Override
    public double loss() {
        SimpleMatrix y_hat = predictor.predict(X, w);
        SimpleMatrix left_term=crossProduct(Y,y_hat.elementLog());
        SimpleMatrix right_term=crossProduct(oneMinusValue(Y),oneMinusValue(y_hat).elementLog());
        return -average(left_term.plus(right_term));
    }
    
    private SimpleMatrix oneMinusValue(SimpleMatrix M){
        for(int i=0;i<M.getNumElements();i++){
            M.set(i,1-M.get(i));
        }
        return M;
    }

}
