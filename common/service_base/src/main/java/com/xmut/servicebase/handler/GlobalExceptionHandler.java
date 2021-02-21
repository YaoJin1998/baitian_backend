package com.xmut.servicebase.handler;





import com.xmut.commonutils.R;
import com.xmut.servicebase.entity.XmutException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(XmutException.class)
    @ResponseBody
    public R error(XmutException e){
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
