package models.projectModels;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result{
    @Expose
    String code;
    @Expose
    String title;
}
