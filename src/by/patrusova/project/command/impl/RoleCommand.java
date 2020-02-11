//package by.patrusova.project.command.impl; todo при регистрации роли параллельно должны добавляться в бд записи клиент\клинер
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
//    @Override
//    public String execute(HttpServletRequest request) throws CommandException {
//        String page;
//        String role = request.getParameter(AttributesEnum.ROLE.getValue());
//        User user = (User)request.getAttribute(AttributesEnum.USER.getValue());
//        user.setRole(role);
//        try {
//            if (RoleService.updateRole(user)) {
//                request.getSession().setAttribute(AttributesEnum.CLIENT.getValue(), user);
//
//            } else  {
//                request.setAttribute(AttributesEnum.ERROR_LOGIN.getValue(),
//                        MessageManager.getProperty(AttributesEnum.MESSAGE_ERROR_LOGIN.getValue()));
//                page = ConfigurationManager.getProperty(AttributesEnum.PAGE_MAIN_ADMIN.getValue());
//            }
//        } catch (ServiceException e) {
//            throw new CommandException(e);//todo logger
//        }
//        return page;
//    }
//}
//}
