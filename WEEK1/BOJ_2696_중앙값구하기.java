package WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ_2696_중앙값 구하기
 * - 수열 홀수번째 수를 읽을 때마다, 지금까지 입력 받은 값의 중앙값 출력
 * - 결과 : 1. 중앙값의 개수 / 홀수번째 수를 읽을 때 마다 구한 중앙값
 * - 중앙값이란 ? -> 데이터 작은 수부터 큰 수 순서대로 정렬 -> 가운데 수
 * - 중앙값 구하기 -> 자료구조 이용 (max 우선순위 / min 우선순위 큐)
 * 참고 : https://steady-coding.tistory.com/88
 *
 * 풀이
 * 1. 홀수 번째 leftQueue / 짝수번째 right Queue에 순차적으로 넣는다.
 * 2. 만약, right Queue에 값이 존재한다면, 마지막에 넣어준 값들을 꺼내서 크기를 비교한다.
 * 3. 만약 right Queue의 peek 값이 left Queue의 peek한 값보다 클 경우, 두 값은 서로 바군다.
 *
 * */
public class BOJ_2696_중앙값구하기 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;


    public static void findMedian(int arraySize) throws IOException{
        // 1. 중앙값의 개수
        sb.append((arraySize + 1) / 2).append("\n");
        int cnt = 0; // 10개 기준으로 줄바꿈
        PriorityQueue<Integer> leftQueue = new PriorityQueue<>(Collections.reverseOrder()); // 오름차순
        PriorityQueue<Integer> rightQueue = new PriorityQueue<>(); // 내림차순
        for (int idx = 0; idx < arraySize; idx++){
            // 조건 : 원소는 한 줄에 10개씩 나누어져 있다.
            if (idx % 10 == 0){
                st = new StringTokenizer(br.readLine().trim());
            }
            // 원소 입력
            int num = Integer.parseInt(st.nextToken());

            // 두 큐의 크기가 같다면? -> num이 홀수번째라는 의미 -> 왼쪽 큐에 넣는다.
            // 두 큐의 크기가 다르다면? -> num은 짝수번째 -> 오른쪽 큐에 넣는다.
            if (leftQueue.size() == rightQueue.size()){
                leftQueue.add(num); // num 은 홀수번째
            } else{
                rightQueue.add(num); // num은 짝수번째
            }

            // left는 중앙값 기준으로 작은값이 위치할 것 / right는 중앙값 기준으로 큰 값이 위치할 것
            if (!rightQueue.isEmpty()){
                if (leftQueue.peek() > rightQueue.peek()){
                    // 만약 홀수번째 때 넣은 값이 더 크다면, -> 두 숫자를 바꿔준다.
                    int num1 = leftQueue.poll(); // 현재 num1이 더 큰 값
                    int num2 = rightQueue.poll();

                    rightQueue.add(num1); // 오른쪽에 더 큰값이 위치하도록 변경
                    leftQueue.add(num2); // 왼쪽에 더 작은 값이 위치하도록 변경
                }
            }

            // 인덱스는 0부터 시작 = 짝수번째가 곧 홀수일 때를 의미한다.
            // left가 왔다갔다 큐에 넣을 때 한개 앞서므로
            if (idx % 2 == 0){
                // 한줄의 최대 10개만 가능
                sb.append(leftQueue.peek() + " ");
                cnt ++;
                if (cnt % 10 == 0){
                    sb.append("\n");
                }
            }

        } // end for input arrayList
        sb.append("\n"); // testCase가 종료되고, 한줄 띄기
   }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++){

            // 수열 정보 입력
            int arraySize = Integer.parseInt(br.readLine().trim());

            // 중앙값 구하기
            findMedian(arraySize);

        }
        // 결과 출력
        System.out.println(sb);
    }

}
