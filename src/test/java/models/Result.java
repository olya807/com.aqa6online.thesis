package models;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Builder
@Data
public class Result {

    private int total;
    private int filtered;
    private int count;
    private List<Entities> entities;
}
