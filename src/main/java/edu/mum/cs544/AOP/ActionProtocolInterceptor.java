package edu.mum.cs544.AOP;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
/**
 *
 * @author Yado
 */
public class ActionProtocolInterceptor {
   @Resource
	private SessionContext sessionCtx;

	@Inject
	private Logger log;

	@AroundInvoke
	protected Object protocolInvocation(final InvocationContext ic)
			throws Exception {
		StringBuilder sb = new StringBuilder("[");
		for (Object obj : ic.getParameters()) {
			sb.append(obj.toString());
			sb.append(", ");
		}
		sb.append("]");
		log.log(Level.INFO,
				"user {0} invoked {1} with method {2} and parameters: {3}",
				new Object[] { sessionCtx.getCallerPrincipal().getName(),
						ic.getTarget().toString(), ic.getMethod().getName(),
						sb.toString() });
		return ic.proceed(); 
}
}
