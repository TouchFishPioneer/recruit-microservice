package cn.herculas.recruit.visualization.util.wrapper;

import cn.herculas.recruit.visualization.data.VO.ResponseVO;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper {
    public static <T> ResponseVO<T> success(T data) {
        ResponseVO<T> resultVO = new ResponseVO<>();
        resultVO.setCode(200);
        resultVO.setMessage("success");
        resultVO.setData(data);
        return resultVO;
    }

    private static ResponseVO error(HttpStatus httpStatus, Map<String, String> errorMessageMap) {
        ResponseVO<Map<String, String>> responseVO = new ResponseVO<>();
        responseVO.setCode(httpStatus.value());
        responseVO.setMessage(httpStatus.getReasonPhrase());
        responseVO.setData(errorMessageMap);
        return responseVO;
    }

    public static ResponseVO error(HttpStatus httpStatus, Exception e) {
        Map<String, String> errorMessageMap = new HashMap<>();
        errorMessageMap.put(String.valueOf(e.getClass()), e.getMessage());
        return ResponseWrapper.error(httpStatus, errorMessageMap);
    }
}
