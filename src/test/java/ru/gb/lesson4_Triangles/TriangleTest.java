package ru.gb.lesson4_Triangles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TriangleTest {
    @DisplayName("Проверка исключения отрицательной или нулевой стороны")
    @Test
    public void testVerifyMethodThrowsBadSideException() {
        InvalidSideLengthException thrown = Assertions.assertThrows(InvalidSideLengthException.class, () ->  {
            new Triangle().Area(-1, 2, 3);
        });

    }
    @DisplayName("Проверка исключения невалидной фигуры (не соединяются точки)")
    @Test
    public void testVerifyMethodThrowsBadFigureException(){
        InvalidFigureException thrown = Assertions.assertThrows(InvalidFigureException.class, () -> {
            new Triangle().Area(4,5,11);
        });
    }
    @DisplayName("Проверка исключения невалидной фигуры (все точки на одной линии)")
    @Test
    public void testVerifyMethodThrowsBadFigureException2(){
        InvalidFigureException thrown = Assertions.assertThrows(InvalidFigureException.class, () -> {
            new Triangle().Area(1,2,3);
        });
    }
    @DisplayName("Проверка вычисления площади валидного треугольника")
    @Test
    public void testVerifyCalculations() throws InvalidSideLengthException, InvalidFigureException { //Проверяем вычисление площади валидного треугольника
        Assertions.assertEquals(14.832396974191326, Triangle.Area(6,7,10));
    }
}
