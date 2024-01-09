package WEEK6;

import java.util.*;
import java.awt.*;

/*
턴에서 발생할 수 있는 모든 경우의 수를 고려해야한다.
상하좌우 * 상하좌우 = 16가지
불가능 경우
1. 기본적인 탐색
2. 두 수레가 동시에 같은 위치로 이동했을 때
3. 두 수레의 위치가 스위치 되었을 때
주의
1. 자신의 도착지점에 들어온 수레는 더 이상 움직일 수 없다.

**/

class Solution {


    static int rowSize;
    static int colSize;
    static int[][] maze;

    static boolean[][][] visited; // 빨간수레, 파란수레의 방문 여부

    // 고정
    static final int EMPTY = 0;
    static final int RED_START = 1;
    static final int BLUE_START = 2;
    static final int RED_END = 3;
    static final int BLUE_END = 4;
    static final int WALL = 5;

    // 상하좌우
    static final int[] deltaRow = {-1, 0, 1, 0};
    static final int[] deltaCol = {0, -1, 0, 1};

    static boolean redEnd; // 빨강 종료
    static boolean blueEnd; // 파랑 종료


    // 좌표 이동을 리턴해준다. -> 16가지 케이스가 많으므로
    public Point getNext(int row, int col, int direction){
        int nextRow = row + deltaRow[direction];
        int nextCol = col + deltaCol[direction];
        return new Point(nextRow, nextCol);
    }

    // 불가능을 확인한다.
    // 불가능에 필요한 정보 : 현재 수레 / 다음 수레
    public boolean check(Point red, Point nextRed, Point blue, Point nextBlue){


        // 1. 기본 탐색
        if (red.x < 0 || red.y < 0 || red.x >= rowSize || red.y >= colSize
                || blue.x < 0 || blue.y < 0 || blue.x >= rowSize || blue.y >= colSize)
            return false;
        if (maze[red.x][red.y] == WALL || maze[blue.x][blue.y] == WALL)
            return false;

        // 두 수레의 위치 확인
        if (((nextRed.x == blue.x) && (nextRed.y == blue.y))
                && (nextBlue.x == red.x) && (nextBlue.y == blue.y))
            return false;

        // 도착지점에 도착하지 않고, 중복 방문이면 false
        if((!redEnd && visited[red.x][red.y][0])
                || (!blueEnd && visited[blue.x][blue.y][1]))
            return false;

        // 두 수레가 동일한 지점에서 위치할 경우
        if (red.x == blue.x && red.y == blue.y)
            return false;

        return true;


    }


    // 백트래킹 (빨간 지점 / 파란지점 / 결과 카운팅)
    // 한 depth 마다 적게 걸리는 턴을 찾는다.
    public int dfs(Point redPoint, Point bluePoint, int result){


        // 1. 기저 조건
        // 두 수레가 모두 도착한다면, 결과값을 반환한다.
        if (redEnd && blueEnd){
            return result; // 누적 결과 반환
        }

        int min = Integer.MAX_VALUE;

        // 16가지의 경우의 수로 모두 탐색
        for (int redDirection = 0 ; redDirection < 4; redDirection++){
            for (int blueDirection = 0; blueDirection < 4; blueDirection++){

                // 다음 지점
                // 만약 종료되지 않았다면, 다음 좌표로 이동 / 그렇지 않다면, 유지
                Point nextRed = (!redEnd) ? getNext(redPoint.x, redPoint.y, redDirection) : redPoint;
                Point nextBlue = (!blueEnd) ? getNext(bluePoint.x, bluePoint.y, blueDirection) : bluePoint;


                // 불가능 체크
                if (!check(redPoint, nextRed, bluePoint, nextBlue))
                    continue;
                // 백트래킹 방문 처리
                visited[nextRed.x][nextRed.y][0] = true;
                visited[nextBlue.x][nextBlue.y][1] = true;

                // 완주 여부 확인
                if(maze[nextRed.x][nextRed.y] == RED_END)
                    redEnd = true;
                if (maze[nextBlue.x][nextBlue.y] == BLUE_END)
                    blueEnd = true;

                // 가장 적게 걸리는 턴 찾기
                min = Math.min(min, dfs(nextRed, nextBlue, result+1)); // 수레의 한번 이동

                // 백트래킹 방문 -> 초기화
                // 나머지 반복을 다시 초기화해주기 위해
                redEnd = false;
                blueEnd = false;

                visited[nextRed.x][nextRed.y][0] = false;
                visited[nextBlue.x][nextBlue.y][1] = false;



            }
        }

        return min;

    }



    public int solution(int[][] maze) {


        this.rowSize = maze.length;
        this.colSize = maze[0].length;
        this.maze = maze;

        // 방문
        this.visited = new boolean[maze.length][maze[0].length][2];

        Point redPoint = null;
        Point bluePoint = null;

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++){
            for (int colIdx = 0; colIdx < colSize; colIdx++){

                // red & blue Poinit 찾기
                if (maze[rowIdx][colIdx] == RED_START){
                    redPoint = new Point(rowIdx, colIdx);
                }

                if (maze[rowIdx][colIdx] == BLUE_START) {
                    bluePoint = new Point(rowIdx, colIdx);
                }
            }
        }


        // 시작 위치의 방문 처리
        visited[redPoint.x][redPoint.y][0] = true;
        visited[bluePoint.x][bluePoint.y][1] = true;

        int answer = dfs(redPoint, bluePoint, 0);

        // 결과
        if (answer == Integer.MAX_VALUE){
            return 0;
        }else{
            return answer;
        }

    }
}