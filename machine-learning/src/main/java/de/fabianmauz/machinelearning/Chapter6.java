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
package de.fabianmauz.machinelearning;

import de.fabianmauz.machinelearning.data.IdxReader;
import java.io.IOException;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author fmauz
 */
public class Chapter6 {
     static protected String dataLocation = "src/main/resources/chapter_6/train-images.idx3-ubyte";
    public static void main(String[] args) throws IOException {
       IdxReader reader = new IdxReader();
       SimpleMatrix X =  reader.importData(dataLocation,10,28,true);
       
    }
}
