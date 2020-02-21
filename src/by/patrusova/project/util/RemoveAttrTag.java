package by.patrusova.project.util;

import by.patrusova.project.util.stringholder.Attributes;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class RemoveAttrTag extends TagSupport {

    @Override
    public int doStartTag() {
        List<String> list = new ArrayList<>();
        list.add(Attributes.ERROR_LOGIN.getValue());
        list.add(Attributes.ERROR_REG.getValue());
        list.add(Attributes.ERROR_ADD_SERVICE.getValue());
        list.add(Attributes.ERROR_CHANGE_CLIENT_ID.getValue());
        list.add(Attributes.ERROR_CHANGE_CLEANER_ID.getValue());
        list.add(Attributes.ERROR_CHANGE_GUEST_ID.getValue());
        list.add(Attributes.ERROR_CHANGE_SERVICE_ID.getValue());
        list.add(Attributes.ERROR_CHANGE_ORDER_ID.getValue());
        list.add(Attributes.ERROR_CHANGE_CLIENT.getValue());
        list.add(Attributes.ERROR_CHANGE_CLEANER.getValue());
        list.add(Attributes.ERROR_CHANGE_BURIAL.getValue());
        list.add(Attributes.ERROR_CHANGE_GUEST.getValue());
        list.add(Attributes.ERROR_CHANGE_ORDER.getValue());
        list.add(Attributes.ERROR_CHANGE_SERVICE.getValue());
        list.add(Attributes.ERROR_CHANGE_USER.getValue());
        list.add(Attributes.ERROR_MAIL.getValue());
        for (String attr : list) {
            pageContext.getSession().removeAttribute(attr);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
