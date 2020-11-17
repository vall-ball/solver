package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    FileWriter writer = null;
    String inputFileName;
    String outputFileName;

    FileHandler(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public double[][] getMatrix() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFileName));
        String[] str = scanner.nextLine().split(" ");
        int numberOfVariables = Integer.parseInt(str[0]);
        int numberOfEquations = Integer.parseInt(str[1]);
        System.out.println("numberOfVariables = " + numberOfVariables);
        System.out.println("numberOfEquations = " + numberOfEquations);
        double[][] array = new double[numberOfEquations][numberOfVariables + 1];
        for (int i = 0; i < numberOfEquations; i++) {
            String[] s = scanner.nextLine().split(" ");
            for (int j = 0; j < s.length; j++) {
                array[i][j] = Double.parseDouble(s[j]);
            }
        }
        scanner.close();
        return array;
    }

    public void saveFile(double[] answer) throws IOException {
        writer = new FileWriter(outputFileName);
        StringBuilder builder = new StringBuilder();
        for (double d : answer) {
            writer.write(Double.toString(d) + "\n");
        }
        writer.close();
    }

    public void saveNoSolution(String str) throws IOException {
        writer = new FileWriter(outputFileName);
        writer.write(str);
        writer.close();
    }
}
