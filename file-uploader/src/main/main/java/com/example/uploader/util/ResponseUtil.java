package com.example.uploader.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 与前端交互的工具类
 *
 * @author nurhier
 * @date 2019/12/4
 */
public class ResponseUtil {

    /**
     * 成功返回有值
     *
     * @param object 返回数据
     * @return
     */
    public static <T> ResponseEntity<?> success(T object) {
        return success(object, null);
    }

    /**
     * 无值成功返回
     *
     * @return
     */
    public static ResponseEntity<?> success() {
        return success(null);
    }

    /**
     * 成功返回有值,并自定义msg信息
     *
     * @param object        返回数据
     * @param customMessage 自定义信息
     * @return
     */
    public static <T> ResponseEntity<?> success(T object, String customMessage) {
        ResultMsgData<T> resultData = new ResultMsgData<>();
        if (StringUtils.isNotEmpty(customMessage)) {
            resultData.setMsg(customMessage);
        } else {
            resultData.setMsg("success");
        }
        resultData.setData(object);
        resultData.setStatus(1);
        return new ResponseEntity<>(resultData, HttpStatus.OK);
    }


    /**
     * 默认错误返回
     *
     * @return
     */
    public static ResponseEntity<?> error() {
        return error(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 自定义错误返回
     *
     * @param httpStatusEnum 错误类型
     * @return
     */
    public static ResponseEntity<?> error(HttpStatus httpStatusEnum) {
        return error(httpStatusEnum, null);
    }

    /**
     * 自定义错误返回信息
     *
     * @return
     */
    public static ResponseEntity<?> error(String customMessage) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, customMessage);
    }

    /**
     * 自定义错误类型和错误信息
     *
     * @param customMessage 自定义错误信息
     * @return
     */
    public static ResponseEntity<?> error(HttpStatus httpStatus, String customMessage) {

        ResultMsgData<?> resultVo = new ResultMsgData<>();
        if (customMessage != null && !customMessage.isEmpty()) {
            resultVo.setMsg(customMessage);
        } else {
            resultVo.setMsg("error");
        }
        resultVo.setStatus(0);
        return new ResponseEntity<>(resultVo, httpStatus);
    }

    /**
     * 自定义错误类型和错误信息
     *
     * @param httpStatus 自定义状态
     * @return
     */
    public static ResponseEntity<?> response(HttpStatus httpStatus) {

        ResultMsgData<?> resultVo = new ResultMsgData<>();
        resultVo.setStatus(1);
        return new ResponseEntity<>(resultVo, httpStatus);
    }
}
