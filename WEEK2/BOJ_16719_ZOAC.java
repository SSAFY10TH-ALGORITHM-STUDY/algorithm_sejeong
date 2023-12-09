package WEEK2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * BOJ_16719_ZOAC
 *
 * -> 처음 접근할 때는, BAC가 있으면, A -> BA -> BAC로 이해 -> 근데 https://www.acmicpc.net/board/view/49769 참고
 * 문제가 이해가 안되서 https://degurii.tistory.com/50 참고해서 문제 이해 ,,
 * */
public class BOJ_16719_ZOAC {

    static BufferedReader br;
    static StringBuilder sb;

    static String input;
    static boolean[] visited;

    public static void zoac(int left, int right) {

        if (left > right){
            // 두 포인터가 종료되는 조건
            return;
        }

        int standard = left;

        // left와 right 사이에 있는 글자 중 사전식 순서가 가장 낮은 글자
        for (int idx = left; idx <= right ; idx ++){
            // 알파벳에서 앞쪽글자를 기준치로 찾는다.
            if (input.charAt(standard) > input.charAt(idx)){
                standard = idx;
            }
        }

        // 방문처리
        visited[standard] = true;

        // 기준이 되는 지점 넣기
        for (int idx = 0; idx < input.length(); idx++){
            if (visited[idx]) sb.append(input.charAt(idx));
        }
        sb.append("\n");

        zoac(standard + 1, right);
        zoac(left, standard - 1);
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        input = br.readLine().trim();
        visited = new boolean[input.length()];

        zoac(0, input.length() - 1);

        System.out.println(sb);
    }

}
