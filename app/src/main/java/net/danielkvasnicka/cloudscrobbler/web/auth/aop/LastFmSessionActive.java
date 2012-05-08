/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.danielkvasnicka.cloudscrobbler.web.auth.aop;

import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;

/**
 *
 * @author daniel
 */
@InterceptorBinding
@Retention(RUNTIME) @Target({ TYPE, METHOD })
public @interface LastFmSessionActive {
    
}
