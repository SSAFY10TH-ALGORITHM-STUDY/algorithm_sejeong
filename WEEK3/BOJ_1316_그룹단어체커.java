package WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ_1316_그룹단어체커
 * - 알파벳 소문자 / 중복 없음
 * */
public class BOJ_1316_그룹단어체커 {

    static BufferedReader br;
    static int groupKeywordCnt = 0; // 그룹 단어 개수
    static int ASCII_START = 97;

    public static void checkGroupKeyword(String str){

        boolean[] visited = new boolean[26]; // 영어 확인
        int prevIdx = -1; // 이전 문자 인덱스
        for (int idx = 0; idx < str.length(); idx++){
            int nowIdx = str.charAt(idx); // 현재 문자의 아스키 코드

            // 이전 문자와 현재 문자가 같지 않으면,
            if (prevIdx != nowIdx) {
                // 만약 처음 나오는 문자라면 갱신
                if (visited[nowIdx - ASCII_START] == false){
                    visited[nowIdx-ASCII_START] = true;
                    prevIdx = nowIdx;
                } else return;
            }
            // 같을 경우는 연속된 문자
            else continue;
        }

        // 모든 과정이 통과되면
        groupKeywordCnt += 1;
    }


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int keywordCnt = Integer.parseInt(br.readLine().trim());
        for (int idx = 0; idx < keywordCnt; idx++){
            checkGroupKeyword(br.readLine().trim());
        }

        System.out.println(groupKeywordCnt);

    }
}
