package daag.web.config;

import daag.model.v1.ResultJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yq on 2018/3/30.
 */
public class LocalhostFilter extends AuthenticationFilter {

    /**
     * 未登录提示
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException{
        Subject subject = this.getSubject(request, response);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        if(subject.getPrincipal() == null) {
//            this.saveRequestAndRedirectToLogin(request, response);
            ResultJson result = new ResultJson(-1,"未登录");
            httpServletResponse.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType("application/json; charset=utf-8");
            ObjectMapper mapper = new ObjectMapper();
            httpServletResponse.getOutputStream().write(mapper.writeValueAsString(result).getBytes("utf-8"));
            httpServletResponse.setStatus(HttpStatus.OK.value());
        }
        return false;
    }

}
