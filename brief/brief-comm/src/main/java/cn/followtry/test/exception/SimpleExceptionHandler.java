package cn.followtry.test.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.yonyou.tools.validation.base.common.exception.BusinessException;
import com.yonyou.tools.validation.base.common.exception.ErrorCode;
import com.yonyou.tools.validation.base.common.exception.ErrorMessage;
import com.yonyou.tools.validation.base.common.exception.ResponseResult;

public class SimpleExceptionHandler implements HandlerExceptionResolver {

	private final static Logger LOG = LoggerFactory.getLogger(SimpleExceptionHandler.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ErrorMessage message = null;
		ResponseResult responseResult = null;
		if (ex instanceof BusinessException) {
			BusinessException businessException = (BusinessException) ex;
			message = new ErrorMessage(businessException.getErrorCode(), businessException.getErrorMessage());
		} else if (ex instanceof JSONException) {
			message = new ErrorMessage(ErrorCode.REQUEST_PARAM_FORMAT_ILLEGAL.getValue(), ErrorCode.REQUEST_PARAM_FORMAT_ILLEGAL.getDescription());
		}else{
			LOG.error(ex.getMessage(), ex);
			message = new ErrorMessage(ErrorCode.UNEXPECTED.getValue(), ErrorCode.UNEXPECTED.getDescription());
		}
		responseResult = ResponseResult.createFailureResult(message);
		PrintWriter printWriter;
		try {
			response.setCharacterEncoding("UTF-8");
			printWriter = response.getWriter();
			printWriter.write(JSON.toJSONString(responseResult));
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

}
