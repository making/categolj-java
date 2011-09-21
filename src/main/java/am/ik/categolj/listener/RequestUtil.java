package am.ik.categolj.listener;

import java.util.concurrent.atomic.AtomicReference;

import am.ik.yalf.logger.Logger;

public class RequestUtil {

    private static final Logger LOGGER = Logger.getLogger(RequestUtil.class);

    private static AtomicReference<String> contextRoot = new AtomicReference<String>();

    static void setContextRoot(String contextRoot) {
        RequestUtil.contextRoot.set(contextRoot);
        LOGGER.debug(false, "[CTGLXX3] set contextRoot={0}",
                RequestUtil.contextRoot);
    }

    public static String getContextRoot() {
        return contextRoot.get();
    }
}
