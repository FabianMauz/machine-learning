package de.fabianmauz.machinelearning.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.ejml.simple.SimpleMatrix;

/**
 *
 * @author Fabian
 */
public class TextImport {
    final private String fileLocation;
    final private int[] xRows, yRows;
    final private int skipLines;
    final private String valueSeperator = " ";

    public TextImport(String fileLocation, int[] xRows, int[] yRows, int skipLines, String valueSeperator ) {
        this.fileLocation = fileLocation;
        this.xRows = xRows;
        this.yRows = yRows;
        this.skipLines = skipLines;
    }

    public SimpleMatrix[] importData() throws Exception {
        double[][] xMatrix = null;
        double[][] yMatrix = null;

        int lines = getLines();

        xMatrix = new double[lines - skipLines][xRows.length];
        yMatrix = new double[lines - skipLines][yRows.length];
        try (BufferedReader br = new BufferedReader(new FileReader(fileLocation))) {
            String line = skipHeaderLines(br);
            readDataValues(line, xMatrix, yMatrix, br);
        }

        return new SimpleMatrix[]{
            new SimpleMatrix(xMatrix),
            new SimpleMatrix(yMatrix)
        };
    }

    private void readDataValues(String line, double[][] xMatrix, double[][] yMatrix, final BufferedReader br) throws NumberFormatException, IOException {
        int currentObservation = 0;
        while (line != null) {
            String[] stringValuesOfLine = line.split(valueSeperator);
            xMatrix[currentObservation] = getValuesFromLine(stringValuesOfLine, xRows);
            yMatrix[currentObservation] = getValuesFromLine(stringValuesOfLine, yRows);
            
            line = br.readLine();
            currentObservation++;
        }
    }

    private String skipHeaderLines(final BufferedReader br) throws IOException {
        String line = br.readLine();
        for (int i = 0; i < skipLines; i++) {
            line = br.readLine();
        }
        return line;
    }

    private double[] getValuesFromLine(String[] stringValuesOfLine, int[] indexOfSelectedValues) throws NumberFormatException {
        String[] selectedValuesOfLine = new String[indexOfSelectedValues.length];
        for (int i = 0; i < indexOfSelectedValues.length; i++) {
            selectedValuesOfLine[i] = stringValuesOfLine[indexOfSelectedValues[i]];
        }
        double[] valuesOfLine = new double[indexOfSelectedValues.length];
        for (int i = 0; i < selectedValuesOfLine.length; i++) {
            valuesOfLine[i] = Double.parseDouble(selectedValuesOfLine[i]);
        }
        return valuesOfLine;

    }

    private int getLines() throws IOException {
        long lineCount;
        try (Stream<String> stream = Files.lines(Paths.get(fileLocation), StandardCharsets.UTF_8)) {
            lineCount = stream.count();
        }
        return (int) lineCount;
    }
}
