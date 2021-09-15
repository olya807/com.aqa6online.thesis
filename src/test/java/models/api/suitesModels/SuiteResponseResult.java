package models.api.suitesModels;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuiteResponseResult {
    @Expose
    boolean status;
    @Expose
    public SuiteResult result;
}
