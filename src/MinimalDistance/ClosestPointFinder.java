package MinimalDistance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Earlviktor on 17.09.2014.
 * Тестовое задание для Школы Программистов HeadHunter. Виктор Лопатин
 *
 *
 * Class that contents methods for finding the closest distance between points in list
 * using brute force or divide&conquer method
 */
public class ClosestPointFinder {
    /**
     * Main public method for D&C solution. Gets the points list and sorted numbers of points into
     * private recursive method
     * @param points list of points
     * @return closest distance between points
     */
    public double GetClosestDistanceDivideConquer(List<Point> points){
        int[] xOrder = getOrders(points, false);
        int[] yOrder = getOrders(points,true);
        return GetClosestPairRecursive(points,xOrder,yOrder);
    }

    /**
     *
     * @param points list of points
     * @param compareParameter false for X, true for Y
     * @return array of points numbers sorted by chosen coordinate
     */
    int[] getOrders(final List<Point> points, final boolean compareParameter){
        int length = points.size();
        Integer[] order = new Integer[length];

        for(int i = 0; i< length; i++){
            order[i] = i;
        }

        Arrays.sort(order,new Comparator<Integer>(){

            @Override
            public int compare(Integer o1, Integer o2) {
                int first,second;

                if(compareParameter){
                    first = points.get(o1).y;
                    second = points.get(o2).y;
                }else {
                    first = points.get(o1).x;
                    second = points.get(o2).x;
                }

                if(first == second) return 0;
                return (first > second)?1:-1;
            }
        });

        int[] result = new int[length];

        for(int i = 0; i< length; i++){
            result[i] = order[i];
        }
        return result;
    }

    /**
     * Recursive method for finding closest points by dividing points array recursively to
     * parts of only 2-3 points, finding closest pair from them and then merging results checking
     * for closer points from different parts
     * @param points list of points
     * @param xOrder array of point numbers if sorted by its X coordinate
     * @param yOrder array of point numbers if sorted by its Y coordinate
     * @return minimal distance between points in list
     */
    private double GetClosestPairRecursive(List<Point> points, int[] xOrder, int[] yOrder){
        double closestDistance = -1;
        int length = xOrder.length;

        if(length <= 3) return GetClosestDistanceBruteForce(points);

        int[] xLeft = Arrays.copyOfRange(xOrder,0,length/2);
        int[] xRight = Arrays.copyOfRange(xOrder,length/2,length);

        int middleLine = points.get(xOrder[length/2]).getX();

        int[] yLeft = new int[xLeft.length];
        int[] yRight = new int[xRight.length];
        int leftInd = 0, rightInd = 0;

        for(int i = 0; i < length; i++){
            if(points.get(yOrder[i]).x < middleLine){
                yLeft[leftInd] = yOrder[i];
                leftInd++;
            }else{
                yRight[rightInd] = yOrder[i];
                rightInd++;
            }
        }

        closestDistance = Math.min(GetClosestPairRecursive(points,xLeft,yLeft),GetClosestPairRecursive(points,xRight,yRight));

        List<Integer> yToCheck = new ArrayList<Integer>();
        for(int i = 0; i < length; i++){
            if(Math.abs(points.get(yOrder[i]).x - middleLine) < closestDistance){
                yToCheck.add(yOrder[i]);
            }
        }
        int checkLength = yToCheck.size();

        for(int i = 0; i < checkLength -1; i++){
            Point iPoint = points.get(yToCheck.get(i));
            int k = i+1;

            while(k < checkLength && points.get(yToCheck.get(k)).y - iPoint.y < closestDistance){
                double newDistance = iPoint.GetDistance(points.get(yToCheck.get(k)));
                if(newDistance < closestDistance){
                   closestDistance = newDistance;
                }
                k++;
            }
        }
        return closestDistance;
    }

    /**
     * Naive brute force solution mostly for checking the result
     * @param points list of points
     * @return closest distance between points
     */
    public double GetClosestDistanceBruteForce(List<Point> points){
        int length = points.size();
        if(length < 2) throw new IllegalArgumentException("There should be more than one point to find pairs");

        double minDistance = points.get(0).GetDistance(points.get(1));

        for(int i = 0; i< length-1;i++){
            for(int j = i+1; j<length; j++){
                double distance = points.get(i).GetDistance(points.get(j));
                if(distance < minDistance) minDistance = distance;
            }
        }
        return minDistance;
    }
}
