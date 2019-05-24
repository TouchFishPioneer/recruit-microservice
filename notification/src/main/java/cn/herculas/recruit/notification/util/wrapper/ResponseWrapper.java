package cn.herculas.recruit.notification.util.wrapper;

import cn.herculas.recruit.notification.data.VO.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

public class ResponseWrapper {
    public static <T> ResponseVO<T> success(T data) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(200);
        responseVO.setMessage("success");
        responseVO.setData(data);
        return responseVO;
    }

    private static ResponseVO error(HttpStatus httpStatus, Map<String, String> errorMessageMap) {
        ResponseVO<Map<String, String>> responseVO = new ResponseVO<>();
        responseVO.setCode(httpStatus.value());
        responseVO.setMessage(httpStatus.getReasonPhrase());
        responseVO.setData(errorMessageMap);
        return responseVO;
    }

    public static ResponseVO error(HttpStatus httpStatus, BindingResult bindingResult) {
        Map<String, String> errorMessageMap = new HashMap<>();
        for (ObjectError objectError : bindingResult.getAllErrors()) {
            errorMessageMap.put(objectError.getObjectName(), objectError.getDefaultMessage());
        }
        return ResponseWrapper.error(httpStatus, errorMessageMap);
    }

    public static ResponseVO error(HttpStatus httpStatus, String key, String description) {
        Map<String, String> errorMessageMap = new HashMap<>();
        errorMessageMap.put(key, description);
        return ResponseWrapper.error(httpStatus, errorMessageMap);
    }
}
