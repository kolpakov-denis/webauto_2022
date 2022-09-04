package ru.gb.lesson4_Triangles;

public class Triangle {

    public static double Area (int a, int b, int c) throws InvalidSideLengthException, InvalidFigureException {

        if ((a <= 0) || (b <= 0) || (c <= 0)) {
            System.out.println("Сторона треугольника не может быть меньше или равна 0!");
            throw new InvalidSideLengthException();
        }
        if
        (((a + b) < c) || ((a + c) < b) || ((b + c) < a)) {
            System.out.println("Сумма двух сторон треугольника не может быть меньше третьей!");
            throw new InvalidFigureException();

        } else {
            double s = (a+b+c)/2;
            double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
            if (area == 0) {
                System.out.println("Ваш треугольник – прямая линия!");
                throw new InvalidFigureException();
            } else {
                System.out.println("Площадь треугольника равна: " + area);
                return area;
            }
        }


    }

   /*public static void main(String[] args) throws InvalidSideLengthException, InvalidFigureException {
        System.out.println(Triangle.Area(6,7,10));
    }

    */




}


