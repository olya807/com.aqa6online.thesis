package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
public class Project{
    @Expose
    String title;
    @Expose
    String code;
    String description;
    String access;
    String group;
}
