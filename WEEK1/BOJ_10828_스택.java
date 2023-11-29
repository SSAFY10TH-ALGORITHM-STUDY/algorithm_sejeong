package WEEK1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_10828_스택 {
    /*
     * BOJ_10828_스택
     * 간단한 스택 구현
     * Stack을 직접 구현하라는 의도 같으나, 나는 구현되어있는 스택 쓰겠음 ㅎㅎ
     * */

    static StringBuilder sb;
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        // 입력받기
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        Stack<Integer> stack = new Stack<Integer>(); // stack

        int N = Integer.parseInt(br.readLine().trim());
        for (int cnt = 0; cnt < N; cnt++) {
            // 각 입력
            st = new StringTokenizer(br.readLine().trim());
            // 첫번째
            String command = st.nextToken();

            if (command.equals("push")) {
                int value = Integer.parseInt(st.nextToken());
                stack.add(value); // push
            }
            else if (command.equals("top")){
                if (!stack.empty()) sb.append(stack.peek()).append("\n");
                else sb.append(-1).append("\n");
            }
            else if (command.equals("size")) {
                sb.append(stack.size()).append("\n");
            }
            else if (command.equals("empty")){
                if (!stack.empty()) sb.append(0).append("\n");
                else sb.append(1).append("\n");
            }
            else if (command.equals("pop")){
                if (!stack.empty()) sb.append(stack.pop()).append("\n");
                else sb.append(-1).append("\n");
            }
        }

        System.out.println(sb);
    }


}
