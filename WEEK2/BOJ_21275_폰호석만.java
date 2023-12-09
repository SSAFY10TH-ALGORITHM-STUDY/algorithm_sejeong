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
    
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine().trim());

        String A = st.nextToken(); // A진법으로 표현한 값
        String B = st.nextToken(); // B진법으로 표현한 값



    }

}
