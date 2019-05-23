package cn.herculas.recruit.captcha.util.wrapper;

import cn.herculas.recruit.captcha.data.VO.ResponseVO;

public class ResponseWrapper {
    public static <T> ResponseVO<T> success(T data) {
        ResponseVO<T> responseVO = new ResponseVO<>();
        responseVO.setCode(200);
        responseVO.setMessage("success");
        responseVO.setData(data);
        return responseVO;
    }
}
