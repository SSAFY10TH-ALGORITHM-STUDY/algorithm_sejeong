package WEEK5;

import java.util.*;

/**
 여행 경로 - java
 여행 경로 -> ICN에서 무조건 출발
 1. 주어진 항공권은 모두 사용해야함
 2. 만일 가능한 경로가 2개 이상일 경우, 알파벳 순서가 앞서는 경로를 return
 3. 모든 도시를 ㄷ방문할 수 없는 경우 주어지지 않는다.

 첫번째 ICN 다음 도착 장소부터
 */


public class PGMS_43162_여행경로 {

    static ArrayList<String> routeList; // 최종 루트
    static boolean[] visited;
    static String[][] tickets;

    public void dfs(int depthCnt, String current, String routeStr) {

        // 1. 기저조건
        // 만약 모든 도시를 방문했다면, 종료
        if(depthCnt == tickets.length){
            routeList.add(routeStr);
            return; // 종료
        }

        // 2. 진행조건
        for (int idx = 0; idx < tickets.length; idx++){
            // 조건 : 현재 다음 목적지와 일치하는 출발지 찾기
            if (!visited[idx] && current.equals(tickets[idx][0])){
                visited[idx] = true;
                dfs(depthCnt+1, tickets[idx][1] , routeStr + " " + tickets[idx][1]); // 다음으로 넘어가기
                visited[idx] = false; // 다른 경로도 있을 수 있음
            }
        }
    }

    public String[] solution(String[][] tickets) {

        this.tickets = tickets;
        routeList = new ArrayList<>(); // route 경로를 나타내는 ArrayList
        visited = new boolean[tickets.length];

        // 1. 리스트 순차적으로 넣기
        dfs(0, "ICN", "ICN");
        // 2. 만일 가능한 경로가 2개 이상이라면 알파벳 순서가 앞서는 경로를 return
        Collections.sort(routeList); // 알파벳 순서
        // System.out.println(routeList.toString());

        return routeList.get(0).split(" "); // String으로 경로 받았던 것을 List로 변환
    }


}
