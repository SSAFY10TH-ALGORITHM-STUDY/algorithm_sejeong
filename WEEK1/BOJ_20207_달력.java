package WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* BOJ_20207_달력
* - 일정은 시작날짜와 종료 날짜를 포함
* - 시작일이 가장 앞선 일정부터 차례대로 채워진다.
* - 시작일이 같을 경우, 일정의 기간이 긴 것 먼저
* - 일정은 최상단 배치
* 풀이방법
* 1. calendar를 반영할 배열을 만든다.
*  - calendar는 365일이지만, 배열에 날짜 자연수를 반영할 것이고, 하루의 폭은 1개 이므로 365 + 1 까지 존재해야한다.
* 2. 위 가정을 하고 돌린 결과 -> 테스트 케이스는 맞는데, 오답 케이스가 생겼음
* 원인 : end가 365~366 (끝까지 존재할 때) 그에 대한 합산은 result에 반영되지 않음
* 해결 : 마지막에 result += height * width를 해서 통과
*   -> 하지만, 가독성이 좋지 않다고 생각해서
*   -> 배열을 367까지 증가 시켜놓고 마지막에 0으로 result값에 반영되도록 수정하였다.
* */

public class BOJ_20207_달력 {

    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] calendar = new int[367];

        for (int idx = 0; idx < N; idx++){
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // 캘린더를 나타내는 배열에 길이만큼 더해주기
            for (int day = start; day <= end; day++){
                calendar[day] += 1;
            }
        }

        int result = 0; // 결과
        int width = 0; // 너비 찾기
        int height = 0; // 높이 찾기
        for (int day = 0; day < calendar.length; day++){

            if (calendar[day] == 0){
                result += width * height; // 넓이 더해주기
                // 가로 세로 초기화
                width = 0;
                height = 0;
            }else {
                // 만약 캘린더에 일정이 존재한다면, width & height 누적
                width += 1; // 길이는 0 이 아닐 때까지 계속 증가
                height = Math.max(height, calendar[day]); // 최대 높이를 찾는다.
            }
        }

        System.out.println(result);
    }

}
