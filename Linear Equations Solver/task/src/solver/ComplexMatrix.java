package solver;

import java.util.ArrayList;
import java.util.List;

public class ComplexMatrix {

    ComplexNumber[][] array;
    List<PairsOfChangingColumns> pairsOfChangingColumnsList = new ArrayList<>();

    ComplexMatrix(ComplexNumber[][] array) {
        this.array = array;
    }

    public void toTryangleForm() {
        int n = 0;
        while (n != array.length) {
            for (int i = n + 1; i < array.length; i++) {
                if (!ComplexNumber.isZero(array[n][n])) {
                    rowSubtraction(array[i], array[n], n);
                } else {
                    findNotNullElement(n, n);
                }
            }
            n++;
        }
    }

    private void rowSubtraction(ComplexNumber[] row2, ComplexNumber[] row1, int n) {
        System.out.println("row1[n] = " + row1[n]);
        ComplexNumber coef = ComplexNumber.division(row2[n],  row1[n]);
        for (int i = 0; i < row1.length; i++) {
            row2[i] = ComplexNumber.subtraction(row2[i], ComplexNumber.multiplication(row1[i], coef));
        }
    }

    public void findNotNullElement(int row, int column) {
        int i = row;
        int j = column;
        if (ComplexNumber.isZero(array[i][column])) {
            while (i != array.length - 1) {
                i++;
                if (!ComplexNumber.isZero(array[i][column])) {
                    swapRows(i, row);
                    return;
                }
            }
            while (j != array[0].length - 1) {
                j++;
                if (!ComplexNumber.isZero(array[row][j])) {
                    swapColumn(column, j);
                    pairsOfChangingColumnsList.add(new PairsOfChangingColumns(column, j));
                }
            }
        }
    }

    public void swapRows(int a, int b) {
        ComplexNumber[] temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void swapColumn(int a, int b) {
        ComplexNumber[] temp = new ComplexNumber[array.length];
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

    public void toBack() {
        int n = array.length - 1;
        while (n != 0) {
            for (int i = n - 1; i >= 0; i--) {
                if (!isNullRow(array[n])) {
                    rowSubtraction(array[i], array[n], n);
                }
            }
            n--;
        }
    }

    public ComplexNumber[] solution() {
        if (pairsOfChangingColumnsList.size() != 0) {
            for (int i = pairsOfChangingColumnsList.size() - 1; i >= 0; i--) {
                swapColumn(pairsOfChangingColumnsList.get(i).column1, pairsOfChangingColumnsList.get(i).column2);
            }
        }
        return solutionRow();
    }

    public ComplexNumber[] solutionRow() {
        ComplexNumber[] answer = new ComplexNumber[array[0].length - 1];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = ComplexNumber.division(array[i][array[0].length - 1], array[i][i]);
        }
        return answer;
    }

    public boolean isNoSolution() {
        for (ComplexNumber[] row : array) {
            if (isNoSolutionRow(row)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNoSolutionRow(ComplexNumber[] row) {
        for (int i = 0; i < row.length - 1; i++) {
            if (!ComplexNumber.isZero(row[i])) {
                return false;
            }
        }
        if (!ComplexNumber.isZero(row[row.length - 1])) {
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
        for (ComplexNumber[] row : array) {
            if (isNullRow(row)) {
                countOfNullRows++;
            }
        }
        return  (array.length - countOfNullRows < array[0].length - 1);
    }

    public boolean isNullRow(ComplexNumber[] row) {
        for (ComplexNumber d : row) {
            if (!ComplexNumber.isZero(d)) {
                return false;
            }
        }
        return true;
    }

    public void printArray() {
        for (ComplexNumber[] e : array) {
            for (ComplexNumber d : e) {
                System.out.print(d + " ");
            }
            System.out.println();
        }
    }

}


/*










    public void print(double[] row) {
        for (double e : row) {
            System.out.print(e + " ");
        }
    }
















 */