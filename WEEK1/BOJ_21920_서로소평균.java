package WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* BOJ_21920_서로소평균
* 길이가 N인 수 중에서 x와 서로소인 수들을 골라 평균을 구한다.
* 서로소 : 두 수의 최대 공약수 1 / 두 수의 최대 공배수 a*b
* 유클리드 호제
*
* 메모
* 백준에서 계속 틀렸다고 나오길래 ?? 했는데
* "절대/상대 오차는 10-6까지 허용한다. " 이런 조건도 있고,
* / 나누기 할 때 int로 수행하면, 결과도 int여서 소수점 이하의 값 잘린다고 한다.
* 따라서 double 형태로 이용해서 소수점 이하를 구해줘야한다고,,
* -> 마지막 연산에서 double 형태로 바꾸니까 정답처리 되었다.
* */

public class BOJ_21920_서로소평균 {

    static BufferedReader br;
    static StringTokenizer st;

    public static int gcd(int num1, int num2){
        // 유클리드 호제 (a > b)일 때
        // GCD(270,192) -> GCD(192, 78) -> GCD(78, 36) -> GCD(36, 5) -> GCD(6,0) = 6
        if (num2 > num1) { // swap
            int tmp = num1;
            num1 = num2;
            num2 = tmp;
        }

        if (num1 % num2 == 0){
            return num2;
        }
        return gcd(num2, num1 % num2);
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] array = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx++){
            array[idx] = Integer.parseInt(st.nextToken());
        }
        int X = Integer.parseInt(br.readLine().trim());


        // 최대 공약수 1 구하기
        double result = 0, count = 0;
        for (int idx = 0; idx < N; idx++){
            if (gcd(array[idx],X) == 1){
                // 서로소 1 = 최대공약수 1
                result += array[idx];
                count++;
            }
        }

        System.out.println(result / count); // 결과 출력
    }

}
