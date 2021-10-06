package baseball;

import java.util.Map;

/**
 * Created By bohyun on 2021/09/30
 */
public class BaseBallController {

    private BaseBallView view;
    private BaseBallModel model;

    public BaseBallController(BaseBallView view, BaseBallModel model) {
        this.view = view;
        this.model = model;
    }

    public String input() {
        return view.input();
    }

    public void start(String value) {
        int strikeCnt = 0;
        int ballCnt = 0;
        String randomNumber = "";
        play(value, strikeCnt, ballCnt, randomNumber);
    }

    private void play(String value, int strikeCnt, int ballCnt, String randomNumber) {
        try {
            model.inputCheck(value);

            randomNumber = (!randomNumber.isEmpty()) ? randomNumber : getRandomNumber();
            Map<BaseBallResult, Integer> result = compareNumber(value, randomNumber);

            int[] score = checkScore(result, strikeCnt, ballCnt);
            strikeCnt = score[0];
            ballCnt = score[1];

            String message = finish(result, score[0], score[1]);
            System.out.println(message);
            boolean start = true;
            if (strikeCnt >= 3) {
                strikeCnt = 0;
                ballCnt = 0;
                start = view.restart();
                randomNumber = "";
            }
            if (start) {
                play(input(), strikeCnt, ballCnt, randomNumber);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            value = input();
            play(value, strikeCnt, ballCnt, randomNumber);
        }
    }

    private String getRandomNumber() {
        return model.getRandomNumber();
    }

    private int[] checkScore(Map<BaseBallResult, Integer> result, int strikeCnt, int ballCnt) {
        if (result.containsKey(BaseBallResult.STRIKE)) strikeCnt += result.get(BaseBallResult.STRIKE);
        if (result.containsKey(BaseBallResult.BALL)) ballCnt += result.get(BaseBallResult.BALL);
        return new int[]{strikeCnt, ballCnt};
    }

    public Map<BaseBallResult, Integer> compareNumber(String input, String randomNumber) {
        Map<BaseBallResult, Integer> compare = model.compare(input, randomNumber);
        return compare;
    }

    private String finish(Map<BaseBallResult, Integer> result, int strikeCnt, int ballCnt) {
        String message = model.finish(result, strikeCnt, ballCnt);
        return message;
    }
}
