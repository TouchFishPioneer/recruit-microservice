package cn.herculas.recruit.captcha.data.VO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseVO<T> {
    private Integer code;
    private String message;
    private T data;
}

