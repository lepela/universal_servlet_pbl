package listener;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import util.PropsLoaderUtil;

@WebListener
public class S3UrlListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		Properties props = PropsLoaderUtil.getProperties("secret/aws_s3.properties");
		String s3url = String.format("https://%s.s3.%s.amazonaws.com/upload/", props.get("bucket-name"), props.get("region-name"));
		sc.setAttribute("s3url", s3url);
	}
}