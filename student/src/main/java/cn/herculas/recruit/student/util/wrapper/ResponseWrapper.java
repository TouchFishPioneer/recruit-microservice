package cn.herculas.recruit.student.util.wrapper;

import cn.herculas.recruit.student.data.VO.ResponseVO;
import cn.herculas.recruit.student.exception.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

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

    public static ResponseVO success() {
        return ResponseWrapper.success(null);
    }

    private static ResponseVO error(HttpStatus httpStatus, Map<String, String> errorMessageMap) {
        ResponseVO<Map<String, String>> responseVO = new ResponseVO<>();
        responseVO.setCode(httpStatus.value());
        responseVO.setMessage(httpStatus.getReasonPhrase());
        responseVO.setData(errorMessageMap);
        return responseVO;
    }

    public static ResponseVO error(HttpStatus httpStatus, StudentException e) {
        Map<String, String> errorMessageMap = new HashMap<>();
        errorMessageMap.put(String.valueOf(e.getExceptionCode()), e.getMessage());
        return ResponseWrapper.error(httpStatus, errorMessageMap);
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
