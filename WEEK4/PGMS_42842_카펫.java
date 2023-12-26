package WEEK4;

public class PGMS_42842_카펫 {

    public int[] solution(int brown, int yellow) {
        int[] answer = {};

        int totalCarpetCnt = brown + yellow;
        for (int rowIdx = 2; rowIdx <= totalCarpetCnt; rowIdx++){
            for (int colIdx = 2; colIdx <= rowIdx; colIdx++){
                // 가로 행 나누어떨어지지 않는다
                if (totalCarpetCnt % rowIdx != 0) continue;
                // 넓이 수량 일치 & 엘로우 수량 일치
                if (rowIdx * colIdx == totalCarpetCnt && (rowIdx - 2) * (colIdx - 2) == yellow){
                    return new int[]{rowIdx, colIdx};
                }
            }
        }

        return new int[]{};
    }
}
