package tomweb.xyz.bjcms;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tomweb.xyz.bjcms.utils.JwtUtils;
import tomweb.xyz.bjcms.vo.BaseVo;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.LogRecord;

@WebFilter(filterName = "AdminFilter", urlPatterns = "/adminApi/*")
@Component
public class AdminFilter implements Filter {
    @Autowired
    JwtUtils jwtUtils;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        if ("OPTIONS".equals(req.getMethod().toUpperCase())) {
            filterChain.doFilter(req, servletResponse);
        }
        String url = req.getRequestURI();
        if (!url.startsWith("/adminApi")) {
            filterChain.doFilter(req, servletResponse);
            return;
        }
        if (url.contains("/adminApi/login")){
            filterChain.doFilter(req, servletResponse);
            return;
        }
        String token = req.getHeader("token");
        if (token != null) {
            Integer userId = jwtUtils.getUserIdByToken(token);
            if (userId==null){

                BaseVo baseVo =new BaseVo();
                baseVo.setData(null);
                baseVo.setCode("LOGIN_ERROR");
                baseVo.setMsg("登陆失效，重新登陆");
                //Content-Type: application/json
                servletResponse.setContentType("application/json;charset=UTF-8");
                servletResponse.setContentType("application/json;charset=UTF-8");
                OutputStream outputStream= servletResponse.getOutputStream();
                outputStream.write(JSONObject.toJSONString(baseVo).getBytes("utf-8"));
                outputStream.close();
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (url.contains("/adminApi/file")){
            token=servletRequest.getParameter("token");
            Integer userId = jwtUtils.getUserIdByToken(token);
            if (userId==null){

                BaseVo baseVo =new BaseVo();
                baseVo.setData(null);
                baseVo.setCode("LOGIN_ERROR");
                baseVo.setMsg("登陆失效，重新登陆");
                //Content-Type: application/json
                servletResponse.setContentType("application/json;charset=UTF-8");
                servletResponse.setContentType("application/json;charset=UTF-8");
                OutputStream outputStream= servletResponse.getOutputStream();
                outputStream.write(JSONObject.toJSONString(baseVo).getBytes("utf-8"));
                outputStream.close();
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            BaseVo baseVo =new BaseVo();
            baseVo.setData(null);
            baseVo.setCode("LOGIN_ERROR");
            baseVo.setMsg("登陆失效，重新登陆");
            servletResponse.setContentType("application/json;charset=UTF-8");
            OutputStream outputStream= servletResponse.getOutputStream();
            outputStream.write(JSONObject.toJSONString(baseVo).getBytes("utf-8"));
            outputStream.close();
            return;
        }

    }
}
