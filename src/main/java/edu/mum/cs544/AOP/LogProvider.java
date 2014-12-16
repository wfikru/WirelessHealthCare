package edu.mum.cs544.AOP;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
/**
 *
 * @author Yado
 */
public class LogProvider {
  @Produces
	public Logger createLogger(InjectionPoint ip) {
		return Logger.getLogger(ip.getMember().getDeclaringClass().getName());
	}  
}
