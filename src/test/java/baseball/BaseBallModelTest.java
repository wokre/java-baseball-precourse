package baseball;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By bohyun on 2021/09/30
 */
class BaseBallModelTest {

    @Test
    void inputCheck() {
        try {
            String a = "23a";
            Integer.valueOf(a);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void compare() {
        //1스트라이크 1볼
        String a = "345";   // 입력값
        String b = "1";   // 랜덤값
        Map<BaseBallResult, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            char a_char = a.charAt(i);
            System.out.println(a_char);
            int idx = b.indexOf(a_char);
            judgement(map, idx, i);
        }
        System.out.println(map + " / " + map.isEmpty());
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

    @Test
    void cnt_plus() {
        int cnt1 = 0;
        int cnt2 = 0;

        for (int i = 0; i < 5; i++) {
            if (i%2 == 0) {
                cnt1++;
            }
            else {
                cnt2++;
            }
        }

        Assertions.assertEquals(3, cnt1);
        Assertions.assertEquals(2, cnt2);
    }
}