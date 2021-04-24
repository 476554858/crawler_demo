package webmagic.demo;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

public class CarPipeline implements Pipeline{
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Car> carList = resultItems.get("carList");
        System.out.println(carList);
    }
}
