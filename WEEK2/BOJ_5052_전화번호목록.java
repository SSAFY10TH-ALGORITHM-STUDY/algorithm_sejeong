package WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_5052_전화번호 목록
 * -> 자바에서 정렬 !! -> Arrays.sort(list)
 * -> str1.startsWith(str2)
 *  자바 문법들 좀 기억하기 ,,
 * */

public class BOJ_5052_전화번호목록 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    // 전화번호 일관성 검사
    public static boolean isConsistent(String [] telList) {
        // 접두어 관계가 있다면, startsWith
        for (int telIdx = 0; telIdx < telList.length- 1; telIdx++){
            // 오름차순에 따라 다음 것을 이전것과 앞자리 비교
            if (telList[telIdx + 1].startsWith(telList[telIdx])) {
                return false;
            }
        }
        return true;
    }

    public static void run() throws IOException{

        int telCnt = Integer.parseInt(br.readLine().trim());
        String[] telList = new String[telCnt]; // 테스트 케이스에 넣을 번호 초기화
        for (int telIdx = 0; telIdx < telCnt; telIdx++){
            telList[telIdx] = br.readLine().trim(); // 전화번호 입력
        }
        Arrays.sort(telList); // 순자척으로 정렬

        if (isConsistent(telList)) sb.append("YES").append("\n");
        else sb.append("NO").append("\n");
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= testCase; tc++){
            run();
        }
        System.out.println(sb);
    }

}
