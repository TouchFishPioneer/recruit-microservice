package cn.herculas.recruit.student.account.data.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {
    private Integer code;
    private String message;
    private T data;
}
