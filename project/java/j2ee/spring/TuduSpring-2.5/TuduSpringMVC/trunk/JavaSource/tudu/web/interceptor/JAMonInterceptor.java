package tudu.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jamonapi.Monitor;
import com.jamonapi.MonitorFactory;

public class JAMonInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		Monitor monitor = (Monitor)request.getAttribute("monitor");
		monitor.stop();
		request.removeAttribute("monitor");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Monitor monitor = MonitorFactory.start(handler.getClass().getName());
		request.setAttribute("monitor",monitor);
		return true;
	}

}
