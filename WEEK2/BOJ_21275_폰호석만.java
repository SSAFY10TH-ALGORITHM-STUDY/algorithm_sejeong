package WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ_21275_폰호석만
 *
 * 폰호석만은 진법 변환의 달인 -> 진법의 수가 주어져도 다른 진법 변환 가능?
 * 수 3개 -> X, A, B -> X는 10진법 -> A진법으로 표현한 수와 B진법으로 표현한 수 종이에 씀
 *
 * 
 * */
public class BOJ_21275_폰호석만 {

    static BufferedReader br;
    static StringTokenizer st;

    static String A;
    static String B;
    static String max;
    static int[] array;
    static String x;
    static long a,b;
    static int count;

    public static boolean check(int a, String str){
        for (int idx = 0; idx < str.length(); idx++){
            if (a <= array[str.charAt(idx)]){
                return false;
            }
        }
        return true;
    }

    public static String find(int a, String str){
        int tmp = 0;
        double result = 0;
        for (int idx = str.length() - 1; idx >= 0 ; idx--){
            int t = array[str.charAt(idx)];
            result += Math.pow(a, tmp) * t;
            tmp ++;
        }
        return String.valueOf(result);
    }

    public static void run(){
        for (int idx = 1; idx <= 36; idx ++){
            for (int jdx = 1; jdx <= 36; jdx++){
                if (idx == jdx) continue;
                if (check(idx, A) && check(jdx, B)){
                    if (find(idx, A).equals(find(jdx, B))){
                        if (find(idx, A).compareTo(max) >= 1) continue;
                        count++;
                        x = find(idx, A);
                        a = idx;
                        b = jdx;
                    }
                }
            }
        }

    }
    
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        A = st.nextToken(); // A진법으로 표현한 값
        B = st.nextToken(); // B진법으로 표현한 값

        max = String.valueOf(Math.pow(2, 63));
        array = new int[200];

        for (int idx = 0; idx < 9; idx++){
            array[idx + '1'] = idx+1;
        }
        for (int idx = 0; idx < 26; idx++){
            array['a' + idx] = idx + 10;
        }
        run();

        if (count == 0) System.out.println("Impossible");
        else if (count == 1) {
            String toInteger = x.substring(0, x.length() - 2);
            System.out.println(toInteger + " " + a + " " + b);
        }
        else System.out.println("Multiple");
    }

}
