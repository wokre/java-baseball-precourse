package baseball;

import nextstep.utils.Console;

/**
 * Created By bohyun on 2021/09/30
 */
public class BaseBallView {

    public String input() {
        System.out.println("숫자를 입력해주세요: ");
        return Console.readLine();
    }

    public boolean restart() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String result = Console.readLine();
        System.out.println(result);
        return !(result.equals("2")) ? true : false;
    }
}
