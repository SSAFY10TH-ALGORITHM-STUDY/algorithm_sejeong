package WEEK3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * BOJ_1874_스택수열
 * - 1부터 n까지의 수를 스택에 넣었다가 늘어놓음
 * 문제 -> input으로 받은 수들은 대상 수열
 * 현재 1,2,3,4, ... N 까지의 수열에서
 * input으로 입력 받은 대상 수열을 만들기 위해,
 * 수열을 어떻게 하면 좋을 지를 묻는 문제.
 * 4, 3, 6, 8, 7, 5, 2, 1 대상 배열을 만들기 위해
 * stack 1 (push) 2 (push) 3 (push) 4 (push) 4 (pop) 3 (pop)
 *       5 (push) 6 (push) 6 (pop)
 *       7 (push) 8 (push) 8 (pop) 7 (pop)
 *       5 (pop) 2 (pop) 1 (pop)
 * */

public class BOJ_1874_스택수열 {

    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder(); // 결과
        Stack<Integer> stack = new Stack<>();


        int arrayCnt = Integer.parseInt(br.readLine().trim());
        int arrayIdx = 1; // 1부터 N까지 수열을 나타내는 index -> 1부터 시작
        boolean possible = true;
        for (int idx = 0 ; idx < arrayCnt; idx++){
            int target = Integer.parseInt(br.readLine().trim());

            // Case 1 : target이 나올때까지 스택에 수열 만들어주기
            while (arrayIdx <= target){
                stack.add(arrayIdx); // stack에 추가되고 있다.
                sb.append("+").append("\n");
                arrayIdx++; // 증가
            }

            // Case 2 : 만약 스택의 최상위가 target과 동일한 경우
            if (stack.peek() == target){
                stack.pop(); // stack 최상위 뽑기
                sb.append("-").append("\n");
            }
            // Case 3 : 만약 target이 일치하는 경우를 순차적으로 못한다면,
            else {
                possible = false;
                break; // 종료
            }
        } // end for input

        if (possible) {
            System.out.println(sb);
        }else{
            System.out.println("NO");
        }

    }


}
