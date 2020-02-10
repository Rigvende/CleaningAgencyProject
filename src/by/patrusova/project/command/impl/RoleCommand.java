//package by.patrusova.project.command.impl;
//
//import by.patrusova.project.command.ActionCommand;
//import by.patrusova.project.entity.impl.User;
//import by.patrusova.project.exception.CommandException;
//import by.patrusova.project.exception.ServiceException;
//import by.patrusova.project.service.LoginService;
//import by.patrusova.project.service.RoleService;
//import by.patrusova.project.util.ConfigurationManager;
//import by.patrusova.project.util.MessageManager;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//public class RoleCommand implements ActionCommand {
//
//    private static final String PARAM_NAME_ROLE = "role";
//
//    @Override
//    public String execute(HttpServletRequest request) throws CommandException {
//        String page;
//        String role = request.getParameter(PARAM_NAME_ROLE);
//        User user = (User)request.getAttribute("user");
//        user.setRole(role);
//        try {
//            if (RoleService.updateRole(user)) {
//                request.setAttribute("client", user);
//                request.getSession().setAttribute("client", user);//todo правильно сессия установлена?
//
//            } else  {
//                request.setAttribute("errorLoginPassMessage",
//                        MessageManager.getProperty("message.loginerror"));
//                page = ConfigurationManager.getProperty("page.mainAdmin");
//            }
//        } catch (ServiceException e) {
//            throw new CommandException(e);
//        }
//        return page;
//    }
//}
//}
