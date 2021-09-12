package models.ui;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestCase {
    @Expose
    int id;
    @Expose
    String title;
    @Expose
    String description;
    @Expose(serialize = false)
    String preconditions;
    @Expose(serialize = false)
    String postconditions;
    @Expose(serialize = false)
    String severity;
    @Expose(serialize = false)
    String priority;
    @Expose(serialize = false)
    String type;
    @Expose(serialize = false)
    String behavior;
    @Expose(serialize = false)
    String automationStatus;
    @Expose(serialize = false)
    String status;
    @Expose(serialize = false)
    String milestone;
    @Expose(serialize = false)
    String suite;
    String layer;
    String isFlaky;
}
