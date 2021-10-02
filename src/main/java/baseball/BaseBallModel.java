package baseball;

import baseball.exception.BaseBallException;
import nextstep.utils.Randoms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By bohyun on 2021/09/30
 */
public class BaseBallModel {

    public void inputCheck(String input) {
        convertToNumber(input);
        String value = checkDuplicate(input);
        if (value.length() < 3) throw new BaseBallException("서로 다른 세자리 수를 입력해 주세요.");
    }

    private void convertToNumber(String value) {
        try {
            Integer.valueOf(value);
        }
        catch (Exception e) {
            throw new BaseBallException("ERROR");
        }
    }

    public Map<BaseBallResult, Integer> compare(String input) {
        //TODO: 랜덤값과 입력값 비교
        String randomNum = randomNumber();
        Map<BaseBallResult, Integer> map = new HashMap<>();
        for (int i = 0; i < randomNum.length(); i++) {
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
        int random = Randoms.pickNumberInRange(111, 999);
        String str_random = String.valueOf(random);
        System.out.println("random : " + str_random);

        String result = checkDuplicate(str_random);
        if (result.length() < 3) {
            System.out.println("recursive : " + str_random);
            randomNumber();
        }

        return result;
    }

    private String checkDuplicate(String str_random) {
        String result = "";
        for (int i = 0; i < str_random.length(); i++) {
            if (str_random.indexOf(str_random.charAt(i)) == i) result += str_random.charAt(i);
        }
        return result;
    }
}
