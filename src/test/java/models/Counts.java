package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Counts {

    private int cases;
    private int suites;
    private int milestones;
}
