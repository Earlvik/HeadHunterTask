package MinimalDistance;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Earlviktor on 17.09.2014.
 * Тестовое задание для Школы Программистов HeadHunter. Виктор Лопатин
 * Для набора из точек с целочисленными координатами найти минимальное расстояние между точками.
 */
public class ClosestPointMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Point> points = new ArrayList<Point>();

        while(true){
            String line = scanner.nextLine();
            String[] numbers = line.split(" ");
            if(line.equals("")) break;
            try {
                int x = Integer.parseInt(numbers[0]);
                int y = Integer.parseInt(numbers[1]);
                Point newPoint = new Point(x, y);
                points.add(newPoint);
            }catch(NumberFormatException e){
                System.out.println("Not a valid number!");
            }
        }


        ClosestPointFinder finder = new ClosestPointFinder();
        System.out.println(finder.GetClosestDistanceDivideConquer(points));

    }

}
