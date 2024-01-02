package WEEK5;

public class PGMS_43165_타겟넘버 {
    static int [] numbers;
    static int target;
    static int answer = 0;

    public void dfs(int index, int result){
        // 1. 기저조건 - index가 number의 마지막까지 탐색
        if (index == numbers.length) {
            if (target == result){
                answer += 1;
            }
            return;
        }

        // 2. 진행조건
        dfs(index + 1, result + numbers[index]);
        dfs(index + 1, result - numbers[index]);
    }

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;

        dfs(0, 0); // index, cnt

        return answer;
    }

}
