package WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * BOJ_17413_단어뒤집기2
 * 1. 문자열 조건 : 알파벳 소문자, 숫자, 공백, 특수문자로만 이루어짐
 * 2. 문자열의 시작과 끝이 공백이 아님
 * 3. <> 가 문자열에 있는 경우 번갈아 등장 (< -> >)
 * - 구해야할 것 : <>로 감싸져 있는 것은 태그 / 그렇지 않은 것 단어 -> 단어를 뒤집어서 출력한다.
 * - 태그가 나와있을 때 풀이 유형 : 스택
 * // String 에서 char 값 확인할 때, String / Character 자료구조 다르므로, str.charAt(index) 사용한다 -> 맨날까먹음
 * */

public class BOJ_17413_단어뒤집기2 {

    // 입출력
    static BufferedReader br;
    static StringBuilder sb;

    // 문자열 관리하는 스택
    static Stack<Character> stack;
    static String input;

    static void reverseWord(){
        while (!stack.empty()) {
            sb.append(stack.peek());
            stack.pop();
        }
    }

    static void run(){

        boolean tag = false; // tag 임을 표시해줌
        // 문자열 전체 탐색
        for (int idx = 0 ; idx < input.length(); idx ++) {

            // 태그가 있는 지 여부 확인
            if (input.charAt(idx) == '<'){
                reverseWord(); // 쌓았던 단어를 스택의 위쪽부터 반대로 출력
                tag = true;
                sb.append(input.charAt(idx));
            }
            // 태그가 닫힘 태그 일 경우
            else if (input.charAt(idx) == '>'){
                tag = false; // tag값 풀어주기
                sb.append(input.charAt(idx));
            }
            // 만약 태그 안의 문자열일 경우
            else if (tag) {
                sb.append(input.charAt(idx)); // 거꾸로 출력하지 않는다.
            }
            // 위 조건이 아닌 단어인 경우 -> 스택을 쌓는다.
            else {
                // 1. 공백일 경우
                if (input.charAt(idx) == ' ') {
                    reverseWord(); // 현재까지 쌓아둔 단어를 뒤집는다.
                    sb.append(input.charAt(idx));
                }
                // 2. 공백이 아닌 문자열일 경우 -> 스택을 쌓는 다.
                else {
                    stack.push(input.charAt(idx));
                }
            }
        }
        // 현재까지 남은 스택의 문자 뒤집기 (마지막까지 단어인 경우 출력되지 않았다.)
        reverseWord();
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        stack = new Stack<>();
        input = br.readLine().trim(); // 문자열 S 입력

        run(); // 탐색 함수
        System.out.println(sb);
    }


}
