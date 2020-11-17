package solver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ComplexFileHandler {
    FileWriter writer = null;
    String inputFileName;
    String outputFileName;

    ComplexFileHandler(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    public ComplexNumber[][] getMatrix() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFileName));
        String[] str = scanner.nextLine().split(" ");
        int numberOfVariables = Integer.parseInt(str[0]);
        int numberOfEquations = Integer.parseInt(str[1]);
        System.out.println("numberOfVariables = " + numberOfVariables);
        System.out.println("numberOfEquations = " + numberOfEquations);
        ComplexNumber[][] array = new ComplexNumber[numberOfEquations][numberOfVariables + 1];

        for (int i = 0; i < numberOfEquations; i++) {
            String[] s = scanner.nextLine().split(" ");
            for (int j = 0; j < s.length; j++) {
                System.out.println(s[j]);
                array[i][j] = getComplexNumber(s[j]);
            }
        }
        scanner.close();
        return array;
    }

    public ComplexNumber getComplexNumber(String str) {
        if (str.equals("i")) {
            return new ComplexNumber(0, 1);
        }
        if (str.equals("-i")) {
            return new ComplexNumber(0, -1);
        }
        if (!str.contains("i")) {
            return new ComplexNumber(Double.parseDouble(str), 0);
        }
        if (str.contains("+")) {
            String[] s = str.split("\\+");
            if (s[1].length() == 1) {
                return new ComplexNumber(Double.parseDouble(s[0]), 1);
            }
            return new ComplexNumber(Double.parseDouble(s[0]), Double.parseDouble(s[1].substring(0, s[1].length() - 1)));
        }
        int lastIndex = str.lastIndexOf("-");
        if (!str.contains("-")) {
            return new ComplexNumber(0, Double.parseDouble(str.substring(0, str.length() - 1)));
        }
        if (str.indexOf("-") != 0) {
            return new ComplexNumber(Double.parseDouble(str.substring(0, lastIndex)), Double.parseDouble(str.substring(lastIndex, str.length() - 1)));
        }
        if (str.indexOf("-") == lastIndex) {
            return new ComplexNumber(0, Double.parseDouble(str.substring(lastIndex, str.length() - 1)));
        }
        return new ComplexNumber(Double.parseDouble(str.substring(0, lastIndex)), Double.parseDouble(str.substring(lastIndex, str.length() - 1)));
    }

    public void saveFile(ComplexNumber[] answer) throws IOException {
        writer = new FileWriter(outputFileName);
        StringBuilder builder = new StringBuilder();
        for (ComplexNumber c : answer) {
            writer.write(c + "\n");
        }
        writer.close();
    }

    public void saveNoSolution(String str) throws IOException {
        writer = new FileWriter(outputFileName);
        writer.write(str);
        writer.close();
    }

}
