package common.Util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class RequestParameterPrinter implements Filter{
	protected boolean flag = false;
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		if(flag){
			Map<String, String[]> map = arg0.getParameterMap();
			if(!map.isEmpty()){
				for(String strArray : map.keySet()){
					StringBuffer sb = new StringBuffer(" " + strArray + ": ");
					for(String str : map.get(strArray))
						sb.append(str + ", ");
					System.out.println( (sb.lastIndexOf(",") > 0) ? (sb.substring(0, sb.length() - 2)) : (sb.toString()) );
				}
			}
		}
		arg2.doFilter(arg0, arg1);
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	    String flag = arg0.getInitParameter("flag");
	    if (flag == null) this.flag = false;
	    else if (flag.equalsIgnoreCase("true")) this.flag = true;
	    else this.flag = false;
	}
	@Override
	public void destroy() {
		this.flag = false;
	}
}
