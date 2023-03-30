package com.truonggiang.core.web.utils;

import com.truonggiang.core.web.common.WebConstant;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class WebCommonUtil {
    public static void addRedirectMessage(HttpServletRequest request, String crudaction, Map<String, String> mapMessage) {
        if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstant.REDIRECT_INSERT)) {
            request.setAttribute(WebConstant.ALERT, WebConstant.SUCCESS);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_INSERT));
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstant.REDIRECT_UPDATE)) {
            request.setAttribute(WebConstant.ALERT, WebConstant.SUCCESS);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_UPDATE));
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstant.REDIRECT_DELETE)) {
            request.setAttribute(WebConstant.ALERT, WebConstant.SUCCESS);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_DELETE));
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstant.REDIRECT_ERROR)) {
            request.setAttribute(WebConstant.ALERT, WebConstant.DANGER);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.REDIRECT_ERROR));
        } else if (StringUtils.isNotBlank(crudaction) && crudaction.equals(WebConstant.NOTHING_MESSAGE)) {
            request.removeAttribute(WebConstant.ALERT);
            request.setAttribute(WebConstant.MESSAGE_RESPONSE, mapMessage.get(WebConstant.NOTHING_MESSAGE));
        }
    }
}
