package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Entities {

    private String title;
    private String code;
    private Counts counts;
    private String description;
    private String access;
    private String group;
}
