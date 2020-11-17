package solver;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ArgsParser parser = new ArgsParser(args);
        ComplexFileHandler fileHandler = new ComplexFileHandler(parser.inputFileName(), parser.outputFileName());
        ComplexMatrix matrix = new ComplexMatrix(fileHandler.getMatrix());
        System.out.println("Initial matrix");
        matrix.printArray();
        matrix.toTryangleForm();
        if (matrix.isNoSolution()) {
            fileHandler.saveNoSolution("No solutions");
            return;
        }
        if (matrix.isInfinity()) {
            fileHandler.saveNoSolution("Infinitely many solutions");
            return;
        }
        System.out.println("Tryangle form");
        matrix.printArray();
        matrix.toBack();
        System.out.println("Result matrix");
        matrix.printArray();
        if (matrix.isNoSolution()) {
            fileHandler.saveNoSolution("No solutions");
        } else {
            fileHandler.saveFile(matrix.solution());
        }


    }
}

/*

 */