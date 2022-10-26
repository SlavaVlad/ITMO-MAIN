package progalab0;

import java.text.DecimalFormat;
import java.util.Random;

public class Hello {

    public static void main(String[] args) {
        short[] a = createFirstArray(); // создаем первый массив
        System.out.print(a[0] + "\n");

        float[] x = createSecondArray(); // создаем второй массив
        System.out.print(x[12] + "\n");

        math(a, x);
    }

    private static short[] createFirstArray() {
        short[] firstArray = new short[17]; // создаем массив на 17 элементов
        int count = 0;
        for (short i = 5; i < 22; i++) { // заполняем массив числами от 5 до 21 включительно
            firstArray[count] = i;
            count++;
        }
        return firstArray; // возвращаем первый массив <short>
    }

    private static float[] createSecondArray() {
        float[] secondArray = new float[13];
        Random random = new Random(); // создаем объект Random для заполнения массива числами, следуя ТЗ
        for (int i = 0; i < 13; i++) {
            /*
             Случайное число от 0 до 1
             увеличиваем диапазон в 15 раз
             смещаем на 11 единиц.
             */
            secondArray[i] = random.nextFloat() * 15 - 11;
        }
        return secondArray; // возвращаем второй массив <float>
    }

    /*
    Массивы в тз названы одинаково, поэтому
    Итоговый массив именован как а
    Второй массив именован как thin a
    Так как в задании результирующий массив был выделен жирным, а предыдущий просто как "а"
    * */
    private static void math(short[] thina, float[] d) {
        double[][] a = new double[9][13]; // создаем массив на 9 строк и 13 столбцов (двухмерный)
        for (int i = 0; i < 9; i++) { // X
            for (int j = 0; j < 13; j++) { // Y
                float x = d[j];
                if (thina[i] == 9) {
                    a[i][j] = Math.log(Math.sqrt(Math.pow((Math.sin(x)), 2)));
                } else {
                    switch ((int) thina[i]) { // если а[i] принадлежит (5, 15, 7, 19), то
                        case 5:
                        case 15:
                        case 7:
                        case 19:
                            a[i][j] = Math.pow((Math.tan(Math.pow(Math.sqrt(x), 3))),
                                    (4 - Math.sin(Math.pow(Math.E, x))) / 2);
                            break;
                        default:
                            a[i][j] = 4 * (Math.asin(Math.cos(Math.tan(x / (x + 0.25f)))) + 2.0 / 3.0);
                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 13; j++) {
                if (((Double) (a[i][j])).isNaN()) { // проверка на Not a Number
                    System.out.print("NaN  "); // если значение "не число", то выводим "NaN", чтобы было красиво :)
                } else {
                    /*
                    Вывод чисел с плавающей точкой в формате 0.000 (3 знака после запятой)
                    Также для удобства чтения добавлены пробелы между столбцами
                    * */
                    System.out.print(new DecimalFormat("#.###").format(a[i][j]) + "    ");
                }
            }
            System.out.print("\n"); // переход на новую строку (y++ по сути)
        }
    }
}