package solver;

public class ComplexNumber {

    double real;
    double imaginary;

    public ComplexNumber() {

    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {
        if (real == 0 && imaginary == 0) {
            return "0";
        }
        if (real == 0) {
            return imaginary + "i";
        }
        if (imaginary == 0) {
            return real + "";
        }
        if (imaginary < 0) {
            return real + "" + imaginary + "i";
        }
        return real + "+" + imaginary + "i";
    }

    public static ComplexNumber addition(ComplexNumber c1, ComplexNumber c2) {
        return new ComplexNumber(c1.real + c2.real, c1.imaginary + c2.imaginary);
    }

    public static ComplexNumber subtraction(ComplexNumber c1, ComplexNumber c2) {
        return new ComplexNumber(c1.real - c2.real, c1.imaginary - c2.imaginary);
    }

    public static ComplexNumber multiplication(ComplexNumber c1, ComplexNumber c2) {
        return new ComplexNumber(c1.real * c2.real - c1.imaginary * c2.imaginary, c1.imaginary * c2.real + c2.imaginary * c1.real);
    }

    public static ComplexNumber division(ComplexNumber c1, ComplexNumber c2) {
        ComplexNumber answer = new ComplexNumber();
        answer.real = (c1.real * c2.real + c1.imaginary * c2.imaginary) / (c2.real * c2.real + c2.imaginary * c2.imaginary);
        answer.imaginary = (c1.imaginary * c2.real - c1.real * c2.imaginary) / (c2.real * c2.real + c2.imaginary * c2.imaginary);
        return answer;
    }

    public static boolean isZero(ComplexNumber c) {
        return c.real == 0 && c.imaginary ==0;
    }

}
