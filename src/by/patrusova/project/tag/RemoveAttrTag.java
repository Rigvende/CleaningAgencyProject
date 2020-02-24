package by.patrusova.project.tag;

import by.patrusova.project.util.stringholder.Attribute;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class RemoveAttrTag extends TagSupport {

    @Override
    public int doStartTag() {
        List<String> list = new ArrayList<>();
        list.add(Attribute.EMPTY_LIST.getValue());
        list.add(Attribute.ERROR_LOGIN.getValue());
        list.add(Attribute.ERROR_REG.getValue());
        list.add(Attribute.ERROR_ADD_SERVICE.getValue());
        list.add(Attribute.ERROR_CHANGE_CLIENT_ID.getValue());
        list.add(Attribute.ERROR_CHANGE_CLEANER_ID.getValue());
        list.add(Attribute.ERROR_CHANGE_GUEST_ID.getValue());
        list.add(Attribute.ERROR_CHANGE_SERVICE_ID.getValue());
        list.add(Attribute.ERROR_CHANGE_ORDER_ID.getValue());
        list.add(Attribute.ERROR_CHANGE_CLIENT.getValue());
        list.add(Attribute.ERROR_CHANGE_CLEANER.getValue());
        list.add(Attribute.ERROR_CHANGE_BURIAL.getValue());
        list.add(Attribute.ERROR_CHANGE_GUEST.getValue());
        list.add(Attribute.ERROR_CHANGE_ORDER.getValue());
        list.add(Attribute.ERROR_CHANGE_SERVICE.getValue());
        list.add(Attribute.ERROR_CHANGE_USER.getValue());
        list.add(Attribute.ERROR_MAIL.getValue());
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