package baseball;

public class Application {
    public static void main(String[] args) {
        // TODO 숫자 야구 게임 구현
        BaseBallView view = new BaseBallView();
        BaseBallModel model = new BaseBallModel();
        BaseBallController controller = new BaseBallController(view, model);

        String input = controller.input();
        controller.start(input);
    }
}
