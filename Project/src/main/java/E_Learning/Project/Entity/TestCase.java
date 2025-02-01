package E_Learning.Project.Entity;

import jakarta.persistence.Embeddable;

import java.util.List;
import java.util.Map;

@Embeddable
public class TestCase {
    private Object inputs;
    private Object expectedOutput;
}
