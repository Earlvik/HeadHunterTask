package MinimalDistance;

/**
 * Created by Earlviktor on 17.09.2014.
 * Тестовое задание для Школы Программистов HeadHunter. Виктор Лопатин
 * Simple Point class representing two numbers
 */
public class Point {
    int x;
    int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double GetDistance(Point another){
        int xDiff = this.x - another.x;
        int yDiff = this.y - another.y;
        return Math.sqrt(xDiff*xDiff+yDiff*yDiff);
    }


}
