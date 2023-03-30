package com.truonggiang.core.web.utils;

import com.truonggiang.core.web.command.AbstractCommand;
import org.apache.commons.lang.StringUtils;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.jboss.jandex.Index;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static void initSearchBeanUtil (HttpServletRequest request, AbstractCommand bean) {
        //Cot muon sort
        String sortExpression = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_SORT));

        //Kieu sort
        String sortDirection = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_ORDER));

        //page phan trang
        String pageStr = request.getParameter(new ParamEncoder(bean.getTableId()).encodeParameterName(TableTagParameters.PARAMETER_PAGE));

        //Ep kieu int cho trang + kiem tra du dau vao
        int page = 1;
        try {
            if (StringUtils.isNotBlank(pageStr)) {
                page = Integer.valueOf(pageStr);
            }
        } catch (Exception e) {
            throw e;
        }

        //set du lieu cho Model
        bean.setPage(page);
        bean.setSortExpression(sortExpression);
        bean.setSortDirection(sortDirection);

        bean.setFirstItem((bean.getPage() - 1) * bean.getMaxPageItems());
    }

    public static void initSearchBeanUtilManual (AbstractCommand command) {
        if (command != null) {
            Integer page = 1;
            if (command.getPage() != 0) {
                page = command.getPage();
            }

            command.setPage(page);
            command.setFirstItem((command.getPage()-1) * command.getMaxPageItems());
        }
    }
}
