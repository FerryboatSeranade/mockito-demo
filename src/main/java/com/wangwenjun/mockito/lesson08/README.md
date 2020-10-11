mock的好习惯,destroy,防止方法间冲突！！！
```java
import org.junit.After;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class goodHabit() {
    @Mock
    private SampleService sampleService;
    @After
    public void destory() {
        reset(sampleService);
    }
}
```