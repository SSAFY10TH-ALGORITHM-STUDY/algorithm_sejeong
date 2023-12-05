package WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_21943_연산최대로
 *
 * N개의 양의 정수 X와 곱하기 연산자, 더하기 연산자 N-1개 존재
 * 괄호 무수히 많이 사용 가능
 * -> 곱하기 연산자와 더하기 연산자의 우선순위 동일
 *
 * 모르겠어서 블로그 참고 :
 *  (1) https://velog.io/@primrose_/Python-BOJ-%EB%B0%B1%EC%A4%80-21943-%EC%97%B0%EC%82%B0-%EC%B5%9C%EB%8C%80%EB%A1%9C
 *  (2) https://khu98.tistory.com/323
 * 1. 덧셈을 이용해 큰 수를 만들기
 * 2. 큰수 * 큰수의 꼴로 곱하기
 * 3. 만약 곱셈이 2개 있다면, 3개 그룹 생성 후 곱해주는 방식 -> 백트래킹
 * */
public class BOJ_21943_연산최대로 {

    static BufferedReader br;
    static StringTokenizer st;

    // 연산
    static int [] array; // 숫자 배열
    static int plusCnt; // 더하기 연산자 수
    static int multiCnt; // 곱하기 연산자 수
    static int [] multiGroup;

    static int answer; // 최종 최대 결과

    public static void dfs(int currentIdx){

        // 기저 조건
        if (currentIdx == array.length){
            // multi Group을 각 곱하기
            int result = 1;
            for (int multiIdx = 0; multiIdx < multiGroup.length; multiIdx ++ ){
                result *= multiGroup[multiIdx];
            }
            answer = Math.max(answer, result);
            return; // 종료
        }

        // 진행 조건
        for (int multiIdx = 0; multiIdx < multiGroup.length; multiIdx ++){
            multiGroup[multiIdx] += array[currentIdx]; // 백트래킹용
            dfs(currentIdx + 1); // DFS 탐색
            multiGroup[multiIdx] -= array[currentIdx]; // 백트래킹을 위해 지워주기
        }

    }

    public static void main(String [] args) throws IOException {

        // 입출력
        br = new BufferedReader(new InputStreamReader(System.in));

        // 전체 배열
        int N = Integer.parseInt(br.readLine().trim());
        array = new int[N]; // 전체 array
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < N; idx ++ ){
            array[idx] = Integer.parseInt(st.nextToken());
        }

        // 연산자
        st = new StringTokenizer(br.readLine().trim());
        plusCnt = Integer.parseInt(st.nextToken());
        multiCnt = Integer.parseInt(st.nextToken());

        multiGroup = new int[multiCnt + 1]; // 곱하기 그룹 만들어주기

        // 연산
        dfs(0);

        // 최종 결과 출력
        System.out.println(answer);
    }


}
