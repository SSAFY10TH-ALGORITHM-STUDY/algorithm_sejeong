package WEEK3;

import org.omg.CosNaming.NamingContextPackage.CannotProceedHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_1976_여행가자
 *
 * 행렬에서 1이면 union
 * 여행 계획에서 맨 처음도시 find -> 부모 찾기
 * 다음 여행 계획에서 도시 find -> 부모 찾기
 * 부모 다를 경우 NO
 * 부모 모두 같을 경우 YES
 * */

public class BOJ_1976_여행가자 {

    static BufferedReader br;
    static StringTokenizer st;

    static int cityCnt;
    static int tripCityCnt;
    static int [] parents;

    static final int CONNECT = 1;

    public static void init(){

        parents = new int[cityCnt + 1]; // 초기화
        for (int idx = 0; idx < cityCnt + 1; idx++){
            parents[idx] = idx;
        }
    }

    public static int find(int element){

        // 부모가 자기 자신이라면, element 리턴
        if (parents[element] == element)
            return element;
        // 부모가 따로 있으면, 부모에 자기 자신 다시 넣기
        return parents[element] = find(parents[element]);
    }

    public static void union(int a, int b){

        // 루트 구하기
        int parentA = find(a);
        int parentB = find(b);

        // 더 작은 것이 부모 그래프 하위로 들어간다.
        if (parentA < parentB){
            parents[parentB] = parentA;
        }else {
            parents[parentA] = parentB;
        }

    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        cityCnt = Integer.parseInt(br.readLine().trim());
        tripCityCnt = Integer.parseInt(br.readLine().trim());

        // 입력 받기
        init(); // parent init
        for (int rowIdx = 1; rowIdx <= cityCnt; rowIdx++){
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 1; colIdx <= cityCnt; colIdx++){
                int connectCheck = Integer.parseInt(st.nextToken());
                if (connectCheck == CONNECT){
                    union(rowIdx , colIdx ); // 연결 여부
                }
            }
        }

        boolean checkResult = true;
        st = new StringTokenizer(br.readLine().trim());
        int startCity = find(Integer.parseInt(st.nextToken()));
        for (int idx = 0; idx < tripCityCnt-1; idx++){
            int nextCity = Integer.parseInt(st.nextToken());
            if (startCity != find(nextCity)){
                checkResult = false;
                break;
            }
        }

        if (checkResult) System.out.println("YES");
        else System.out.println("NO");
    }

}
