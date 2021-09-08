package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseBody {

    private boolean status;
    private Result result;
}
