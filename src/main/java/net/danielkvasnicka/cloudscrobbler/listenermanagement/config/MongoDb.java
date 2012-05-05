/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.listenermanagement.config;

import com.mongodb.Mongo;
import java.lang.annotation.Annotation;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Set;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import org.jboss.solder.resourceLoader.Resource;
import org.jongo.Jongo;
import org.jongo.MongoCollection;

/**
 *
 * @author daniel
 */
public class MongoDb {
    
    @Inject
    @Resource("META-INF/mongodb.properties")
    Properties mongoConfig;

    @Produces @Collection("")
    public MongoCollection getCollection(InjectionPoint point) throws UnknownHostException {
        Mongo mongo = new Mongo(this.mongoConfig.getProperty("mongodb.host"), 
                Integer.parseInt(this.mongoConfig.getProperty("mongodb.port")));
        Jongo jongo = new Jongo(mongo.getDB(this.mongoConfig.getProperty("mongodb.dbname")));
        
        Set<Annotation> qualifiers = point.getQualifiers();
        Collection col = (Collection) qualifiers.iterator().next();
        
        return jongo.getCollection(col.value());
    }
}
