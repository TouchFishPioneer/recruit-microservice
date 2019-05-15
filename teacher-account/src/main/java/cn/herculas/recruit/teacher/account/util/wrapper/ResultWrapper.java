package cn.herculas.recruit.teacher.account.util.wrapper;

import cn.herculas.recruit.teacher.account.data.VO.ResponseVO;
import org.springframework.http.HttpStatus;

public class ResultWrapper {
    public static <T> ResponseVO<T> success(T data) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(200);
        responseVO.setMessage("success");
        responseVO.setData(data);
        return responseVO;
    }

    public static ResponseVO success() {
        return ResultWrapper.success(null);
    }

    public static ResponseVO error(HttpStatus httpStatus, String detail) {
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCode(httpStatus.value());
        String message = String.format("%s. %s", httpStatus.getReasonPhrase(), detail);
        responseVO.setMessage(message);
        return responseVO;
    }
}
