package WEEK4;


import java.util.*;
class Solution {

    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        int [] dayList = new int[100]; // 작업 100
        int day = -1;

        for (int idx = 0; idx < progresses.length; idx++){
            while (progresses[idx] + (day * speeds[idx]) < 100){
                day++;
            }
            dayList[day]++;
        }

        ArrayList<Integer> answerList = new ArrayList<>();
        for (int idx = 0; idx < 100; idx++){
            if (dayList[idx] != 0){
                answerList.add(dayList[idx]);
            }
        }
        answer = new int[answerList.size()];
        for (int idx = 0; idx < answerList.size(); idx++){
            answer[idx] = answerList.get(idx);
        }

        return answer;
    }
}