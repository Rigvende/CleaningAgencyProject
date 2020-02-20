package by.patrusova.project.util;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

@SuppressWarnings("serial")
public class InfoTimeTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        LocalDateTime dateTime = LocalDateTime.now();
        StringBuilder builder = new StringBuilder();
        builder.append("<hr/><b> ").append(dateTime.getDayOfWeek()).append(" || ")
                .append(dateTime.getDayOfMonth()).append(" ").append(dateTime.getMonth()).append(" ")
                .append(dateTime.getYear()).append(" || ").append(dateTime.getHour())
                .append("h:").append(dateTime.getMinute()).append("min </b><hr/>");
        String time = builder.toString();
        try {
            JspWriter out = pageContext.getOut();
            out.write(time);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}