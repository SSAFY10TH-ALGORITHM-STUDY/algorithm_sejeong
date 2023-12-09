package WEEK2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * BOJ_2164_카드2
 * - N장의 카드 -> 1~N까지의 번호
 * - 제일 위에 있는 카드 바닥에 버려 -> 제일 위에 있는 카드 제일 아래에 있는 카드로 -> 마지막에 남는 카드는?
 *
 * */
public class BOJ_2164_카드2 {

    static BufferedReader br;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        int maxCardNum = Integer.parseInt(br.readLine().trim());
        Deque<Integer> queue = new ArrayDeque<>();

        // 기본으로 큐에 카드를 넣는다.
        for (int cardNum = 1; cardNum <= maxCardNum; cardNum++){
            queue.add(cardNum);
        }

        while (queue.size()!=1){
            int top = queue.poll(); // 제일 위에 있는 카드를 바닥에 버린다.
            queue.add(queue.poll()); // 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
        }
        System.out.println(queue.poll()); // 마지막 남은 queue
    }

}
