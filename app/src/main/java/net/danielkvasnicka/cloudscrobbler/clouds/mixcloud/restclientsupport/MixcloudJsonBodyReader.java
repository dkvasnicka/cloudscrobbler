/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.clouds.mixcloud.restclientsupport;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJacksonProvider;

/**
 *
 * @author daniel
 */
@Consumes("text/javascript; charset=utf-8")
public class MixcloudJsonBodyReader extends ResteasyJacksonProvider {

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }
    
    
}
