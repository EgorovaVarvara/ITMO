import java.util.Arrays;
import java.util.Random;
import java.util.List; // импортируем библиотеку для проверки на наличие числа в списке

class Main {
    public static void main(String[] args) {
        // Пункт 1
        short[] c = new short[15];
        for (int i = 0; i <= 14; i++) {
            c[i] = (short) ((short) i + 4); // создаем массив типа short и заполняем его числами
        }

        // Пункт 2
        float[] x = new float[14];
        Random random = new Random();
        for (int i = 0; i < x.length; i++) {
            x[i] = random.nextFloat() * 22 - 8; // создаем массив со случайнымми числами от -8 до 14
        }

        // Пункт 3
        double[][] mass = new double[15][14]; // создаем двумерный массив
        List<Integer> List = Arrays.asList(5, 6, 8, 9, 11, 14, 17); // создаем массив для проверки на наличие в нем числа
        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 14; j++){
                if (c[i] == 12){ // условие 1
                    mass[i][j] = Math.pow((Math.PI) / (2 - Math.atan(Math.pow(Math.E, Math.abs(x[j]) * (-1)))), 2);
                }else if (List.contains(c[i])){ // условие 2
                    mass[i][j] = (Math.cos(Math.pow(x[j], 1.0 / 3.0)) - 1) / 4;
                }else{ // иначе
                    mass[i][j] = Math.pow((Math.log(Math.acos(Math.pow(Math.E, -1 * Math.abs(x[j]))))) / (Math.asin(Math.pow(Math.pow(Math.E, -1 * Math.abs(x[j])), 2)) - 1), 3);
                }
            }
        }
        for (int i = 0; i < 15; i++){ // выводим получившийся массив в виде таблицы
            for (int j = 0; j < 14; j++){
                System.out.printf("| %8.3f |", mass[i][j]);
            }
            System.out.println();
        }
	}
}