package by.epam.kunitski.travelagency.web.config;

import by.epam.kunitski.travelagency.dao.config.DaoConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses () {
        return new Class<?>[]{DaoConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses () {
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings () {
        return new String[]{"/"};
    }
}
