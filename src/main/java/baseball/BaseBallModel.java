package baseball;

import baseball.exception.BaseBallException;
import nextstep.utils.Randoms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By bohyun on 2021/09/30
 */
public class BaseBallModel {

    public void inputCheck(String value) {
        try {
            Integer.valueOf(value);
        }
        catch (Exception e) {
            throw new BaseBallException("ERROR");
        }
    }

    public Map<BaseBallResult, Integer> compare(String input, String randomNum) {
        Map<BaseBallResult, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char input_char = input.charAt(i);          // 입력값 문자
            int idx = randomNum.indexOf(input_char);    // 랜덤값에 입력값 문자가 존재하는지 인덱스 출력
            judgement(map, idx, i);
        }
        return map;
    }

    private Map<BaseBallResult, Integer> judgement(Map<BaseBallResult, Integer> map, int idx, int i) {
        BaseBallResult result = getResult(idx, i);
        for (BaseBallResult r : BaseBallResult.values()) {
            putResultMap(map, result, r);
        }
        return map;
    }

    private void putResultMap(Map<BaseBallResult, Integer> map, BaseBallResult result, BaseBallResult r) {
        int cnt = map.get(r) != null ? map.get(r) : 0;
        if (r.equals(result)) {
            map.put(r, ++cnt);
        }
    }

    private BaseBallResult getResult(int idx, int i) {
        if (idx > -1) return (idx == i) ? BaseBallResult.STRIKE : BaseBallResult.BALL;
        return null;
    }

    private String randomNumber() {
        int random = Randoms.pickNumberInRange(1, 9);
        String str_random = String.valueOf(random);
        return str_random;
    }

    /**
     * 종료
     * @return
     */
    public String finish(Map<BaseBallResult, Integer> result, int strikeCnt, int ballCnt) {
        if (result.isEmpty()) return BaseBallResult.NOTHING.getValue();
        if (strikeCnt >= 3) return "3스트라이크 \n3개의 숫자를 모두 맞히셨습니다! 게임 끝";

        StringBuilder sb = new StringBuilder();
        if (strikeCnt > 0) sb.append(strikeCnt).append(BaseBallResult.STRIKE.getValue()).append(" ");
        if (ballCnt > 0) sb.append(ballCnt).append(BaseBallResult.BALL.getValue()).append(" ");

        return sb.toString();
    }

    public String getRandomNumber() {
        String randomNum = "";
        for (int i = 0; i < 3; i++) {
            randomNum += randomNumber();
        }
        return randomNum;
    }
}
