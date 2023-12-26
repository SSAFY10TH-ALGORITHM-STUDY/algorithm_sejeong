package WEEK4;
import java.util.*;

/**
 스택/큐  - 프로세스
 운영체제의 역할 중 하나 컴퓨터 시스템 자원 효율적 관리
 1. 실행 대기 큐 프로세스 하나 꺼내
 2. 큐에 대기중 프로세스 중 우선순위 더 높다면, 방금 꺼낸거 다시 넣어
 3. 그런거 없으면, 방금 꺼낸 프로세스 실행 -> 한번 실행한 프로세스는 다시 큐에 넣지 않고 종료
 */

class PGMS_42587_프로세스 {

    static Deque<Process> queue;
    static int[] priorities;

    static class Process{
        int data;
        int priority;

        public Process(int data ,int priority){
            this.data = data;
            this.priority = priority;
        }

        public String toString(){
            return "data : " + this.data + " priority : " + this.priority;
        }
    }

    public static void init(){
        // 초기화
        for (int idx = 0; idx < priorities.length; idx++){
            queue.add(new Process(idx, priorities[idx]));
        }
    }

    public int solution(int[] priorities, int location) {
        int answer = 1; // 자연수이기 때문에 1부터 시작
        this.priorities = priorities; // 멤버변수에 넣기

        queue = new ArrayDeque<>();
        init(); // 초기화

        while(!queue.isEmpty()){
            Process current = queue.poll(); // 프로세스 하나 꺼내기
            for (Process next : queue){
                // 우선순위가 더 높은 프로젝트가 있다면,
                if (current.priority < next.priority){
                    queue.add(current); // 다시  Queue에 넣기
                    current = null;
                    break;
                }
            }
            // 만약 현재 프로세스가 큐에 들어가지 않았다면,
            if (current != null){
                if (current.data == location){
                    return answer;
                }else{
                    answer++;
                }
            }


        }

        return answer;
    }
}