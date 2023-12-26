package WEEK4;

import java.util.*;

/**
 이중우선순위큐
 */

class PGMS_42628_이중우선순위큐 {

    static StringTokenizer st;
    static PriorityQueue<Integer> minQueue;
    static PriorityQueue<Integer> maxQueue;


    public int[] solution(String[] operations) {
        int[] answer = {};

        minQueue = new PriorityQueue<>();
        maxQueue = new PriorityQueue<>(Collections.reverseOrder());

        // 입력 받기
        for (int idx = 0; idx < operations.length; idx++){
            st = new StringTokenizer(operations[idx]);
            char command = st.nextToken().charAt(0);
            int num = Integer.parseInt(st.nextToken());

            switch(command){
                case 'I' :
                    minQueue.add(num);
                    maxQueue.add(num);
                    break;
                case 'D' :
                    if (maxQueue.isEmpty())
                        break;
                    if (num == 1){
                        int delNum = maxQueue.poll();
                        minQueue.remove(delNum);
                    }
                    if (num == -1){
                        int delNum = minQueue.poll();
                        maxQueue.remove(delNum);
                    }
            }
        }

        if (maxQueue.isEmpty()){
            return new int[]{0,0};
        }

        return new int[]{maxQueue.peek(), minQueue.peek()};
    }
}