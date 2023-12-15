package WEEK3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_18222_투에모스 문자열
 * 0, 1로 이루어진 길이가 무한한 문자열 X 존재
 * 1. X는 맨 처음 "0"으로 시작
 * 2. X에서 0->1, 1->0으로 뒤바꾼 문자열 ㅌ 만emfrl
 * 3. X의 뒤에 X를 붙인 문장을 다시 X로 정의
 *
 * 실패
 * - 예시 코드를 진행했으나, NumberFormat 에러가 계속 떴음
 * - Long 타입으로 진행해야 할 것
 * - str.charAt(idx) 방법으로 풀어갔는데, -> long 타입에서는 charAt이 지원되지 않음
 *
 * 다시
 * - 0 -> 01 -> 0110 -> 01101001 -> 0110100110010110 -> 증가 폭 모든 케이스 일치
 * - function(0) = 0 -> 주어진 조건 -> 바로 return
 * - function(2n) = function(n)
 * - function(2n+1) = 1 - function(n)
 * - 투에모스 점화식 자체는 : https://sojeong-lululala.tistory.com/66 여기에 풀이 보고 이해
 * */
public class BOJ_18222_투에모스문자열 {

    static BufferedReader br;
    static long [] map;

    public static long findChar(long k){

        // 간단
        if (k == 0) return 0;

        if (k % 2 == 0){
            return findChar(k / 2);
        } else{
            return 1 - findChar(k / 2);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        // k번째 수 -> index에 맞추기 위해 -1을 하여 String의 index의 시작과 동일하게 맞춘다.
        long k = Long.parseLong(br.readLine().trim()) - 1;
        System.out.println(findChar(k));
    }

}
