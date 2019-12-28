package es.tappx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

import es.tappx.domain.Ad;
import es.tappx.domain.AdSize;
import es.tappx.domain.Webhook;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(Webhook.class);
        config.exposeIdsFor(Ad.class);
        config.exposeIdsFor(AdSize.class);
    }
}