package WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* BOJ_22945_팀빌딩
*
* N명이 팀빌딩을 위해 한 줄로 서있다.
* 2명의 개발자는 반드시 모여야 한다.
* (A와 B 사이에 존재하는 다른 개발자 수 ) * min(A의 능력 치, B의 능력치)
* 팀빌딩에서 나올 수 ㅇㅆ는 팀 중 능력치 최대 ?
* */

public class BOJ_22945_팀빌딩 {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        int [] array = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx++){
            array[idx] = Integer.parseInt(st.nextToken());
        }

        int start = 0, end = N-1;
        int max = Integer.MIN_VALUE; // 최소값 부터 최대 갱신
        while (true) {
            // 종료 조건
            if (end <= start){
                break;
            }
            int ability = (end - start -1) * Math.min(array[start], array[end]); // 능력 구하는 공식
            max = Math.max(ability, max); // 최대 능력 구하기

            // 증감 조건 : 능력치가 더 작은 것이 클수록 좋으니 작은 쪽의 인덱스를 옮기며 최대값을 찾는다.
            if (array[start] < array[end]){
                start ++;
            }else{
                end --;
            }
        }
        System.out.println(max);

    }



}
