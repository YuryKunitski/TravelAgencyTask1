package by.epam.kunitski.travelagency.web.config;

import by.epam.kunitski.travelagency.dao.config.DaoConfig;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DaoConfig.class, SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
//
//    @Override
//    protected Filter[] getServletFilters() {
//        CharacterEncodingFilter cef = new CharacterEncodingFilter();
//        cef.setEncoding("UTF-8");
//        cef.setForceEncoding(true);
//
//        return new Filter[]{new HiddenHttpMethodFilter(), cef};
//    }

//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(getServletConfigClasses());
//        servletContext.addListener(new ContextLoaderListener(ctx));
//        ctx.setServletContext(servletContext);
//        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
//        servlet.addMapping("/");
//        servlet.setLoadOnStartup(1);
//        servlet.setInitParameter("throwExceptionIfNoHandlerFound", "true");
////        servletContext.addFilter(SECURITY_FILTER_NAME, org.springframework.web.filter.DelegatingFilterProxy.class)
////                .addMappingForUrlPatterns(null, false, "/*");
//    }
}
