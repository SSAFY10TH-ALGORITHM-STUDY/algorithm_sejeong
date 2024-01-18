package WEEK7;


import java.util.*;

/**
 도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프
 그래프 1개 이상의 정점과 / 정점들을 연결하는 단방향 간선

 크기가 N인 도넛모양 그래

 */

public class PGMS_258711_도넛과막대그래프 {

    // 그래프의 방향 -> <-
    static int[] inEdges = new int[1000001]; // 엣지 최대값
    static int[] outEdges = new int[1000001];

    static int max;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4]; // 4개

        for (int idx = 0; idx < edges.length; idx++){
            inEdges[edges[idx][1]] ++; // 들어오는 케이스
            outEdges[edges[idx][0]] ++;

            max = Math.max(max, Math.max(edges[idx][0], edges[idx][1]));

        }


        for (int idx = 1; idx <= max; idx++){
            if (inEdges[idx] == 0 && outEdges[idx] >= 2){
                answer[0] = idx;
            } else if (outEdges[idx] == 0){
                answer[2] ++;
            } else if (inEdges[idx] >= 2 && outEdges[idx] == 2){
                answer[3] ++;
            }
        }

        answer[1] = outEdges[answer[0]] - answer[2] - answer[3];

        return answer;
    }

}
