package baseball;

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
        System.out.println("start game!");
        return view.input();
    }

    public void start(String value) {
        try {
            model.inputCheck(value);
            compareNumber(value);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            value = input();
            start(value);
        }
    }

    public void compareNumber(String input) {
        System.out.println("start model");
        model.compare(input);
    }
}
