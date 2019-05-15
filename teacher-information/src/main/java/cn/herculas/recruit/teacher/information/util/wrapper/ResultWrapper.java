package cn.herculas.recruit.teacher.information.util.wrapper;

import cn.herculas.recruit.teacher.information.data.VO.ResultVO;
import org.springframework.http.HttpStatus;

public class ResultWrapper {
    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(200);
        resultVO.setMessage("success");
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO error(HttpStatus httpStatus, String detail) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(httpStatus.value());
        String message = String.format("%s. %s", httpStatus.getReasonPhrase(), detail);
        resultVO.setMessage(message);
        return resultVO;
    }
}
