package NumberOfDecompositions;

import java.util.Scanner;

/**
 * Created by Earlviktor on 16.09.2014.
 * Тестовое задание для Школы Программистов HeadHunter. Виктор Лопатин
 * Определить количество способов представить число n в виде суммы k натуральных слагаемых
 * без учёта порядка. n и k не более 150.
 *
 * Рекурсивная формула для числа разложений числа N на K слагаемых выводится из слудующих утверждений:
 * Предположим, имеется некоторое разложение числа N на K слагаемых без учёта порядка.
 * Если в этом разложении присутствует слагаемое равное 1, то ему можно поставить в соответствие
 * разложение числа N-1 на K-1 слагаемых, убрав слагаемое, равное 1.
 * Если же в данном разложении нет ни одной единицы, ему можно поставить в соответствие разложение
 * числа N-K на K слагаемых, отняв от каждого слагаемого по 1. Таким образом общее число разложений
 * равно D(N,K) = D(N-1, K-1) + D(N-K, K)
 * Условия выхода из рекурсии следующие:
 * K > N => D(N, K) = 0
 * K == N || K == 1 => D(N,K) = 1
 *
 */
public class NumberDecomposer {
    public static int GetDecompositionsCount(int n, int k){
        if(n<=0 || k<=0) throw new IllegalArgumentException("Arguments should be greater than zero");
        if(k==n || k == 1) return 1;
        if(k>n) return 0;
        return GetDecompositionsCount(n-1, k-1) + GetDecompositionsCount(n-k,k);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        System.out.println(NumberDecomposer.GetDecompositionsCount(n,k));
    }
}
