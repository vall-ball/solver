package solver;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    double[][] array;
    List<PairsOfChangingColumns> pairsOfChangingColumnsList = new ArrayList<>();

    Matrix(double[][] array) {
        this.array = array;
    }

    public void toTryangleForm() {
        int n = 0;
        while (n != array.length) {
            for (int i = n + 1; i < array.length; i++) {
                if (array[n][n] != 0) {
                    rowSubtraction(array[i], array[n], n);
                } else {
                    findNotNullElement(n, n);
                }
            }
            n++;
        }
    }

    public void toBack() {
        int n = array.length - 1;
        while (n != 0) {
            for (int i = n - 1; i >= 0; i--) {
                rowSubtraction(array[i], array[n], n);
            }
            n--;
        }
    }

    public double[] solution() {
        if (pairsOfChangingColumnsList.size() != 0) {
            for (int i = pairsOfChangingColumnsList.size() - 1; i >= 0; i--) {
                swapColumn(pairsOfChangingColumnsList.get(i).column1, pairsOfChangingColumnsList.get(i).column2);
            }
        }
        return solutionRow();
    }

    public void printArray() {
        for (double[] e : array) {
            for (double d : e) {
                System.out.print(d + " ");
            }
            System.out.println();
        }
    }

    public void print(double[] row) {
        for (double e : row) {
            System.out.print(e + " ");
        }
    }

    private void rowSubtraction(double[] row2, double[] row1, int n) {
        double coef = row2[n] / row1[n];
        for (int i = 0; i < row1.length; i++) {
            row2[i] = row2[i] - row1[i] * coef;
        }
    }

    public double[] solutionRow() {
        double[] answer = new double[array[0].length - 1];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = array[i][array[0].length - 1] / array[i][i];
        }
        return answer;
    }

    public void findNotNullElement(int row, int column) {
        int i = row;
        int j = column;
        if (array[i][column] == 0) {
            while (i != array.length - 1) {
                i++;
                if (array[i][column] != 0) {
                    swapRows(i, row);
                    return;
                }
            }
            while (j != array[0].length - 1) {
                j++;
                if (array[row][j] != 0) {
                    swapColumn(column, j);
                    pairsOfChangingColumnsList.add(new PairsOfChangingColumns(column, j));
                }
            }
        }
    }

    public void swapRows(int a, int b) {
        double[] temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void swapColumn(int a, int b) {
        double[] temp = new double[array.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = array[a][i];
        }
        for (int i = 0; i < temp.length; i++) {
            array[a][i] = array[b][i];
        }
        for (int i = 0; i < temp.length; i++) {
            array[b][i] = temp[i];
        }
    }

    public boolean isNoSolution() {
        for (double[] row : array) {
            if (isNoSolutionRow(row)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNoSolutionRow(double[] row) {
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] != 0) {
                return false;
            }
        }
        if (row[row.length - 1] != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isInfinity() {
        if (array.length < array[0].length - 1) {
            return true;
        }
        int countOfNullRows = 0;
        for (double[] row : array) {
            if (isNullRow(row)) {
                countOfNullRows++;
            }
        }
        System.out.println("array.length - countOfNullRows = "  + (array.length - countOfNullRows));
        System.out.println("array[0].length - 1 = "  + (array[0].length - 1));

       return  (array.length - countOfNullRows < array[0].length - 1);
    }

    public boolean isNullRow(double[] row) {
        for (double d : row) {
            if (d != 0) {
                return false;
            }
        }
        return true;
    }
}
