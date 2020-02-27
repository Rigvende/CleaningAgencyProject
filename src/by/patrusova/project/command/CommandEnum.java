package by.patrusova.project.command;

import by.patrusova.project.command.impl.*;
import by.patrusova.project.command.impl.add.AddServiceCommand;
import by.patrusova.project.command.impl.add.AddOrderCommand;
import by.patrusova.project.command.impl.add.AddServiceRedirectCommand;
import by.patrusova.project.command.impl.change.*;
import by.patrusova.project.command.impl.delete.DeleteEntityCommand;
import by.patrusova.project.command.impl.menu.*;
import by.patrusova.project.command.impl.order.OrderRedirectCommand;
import by.patrusova.project.command.impl.order.PlaceOrderCommand;
import by.patrusova.project.command.impl.order.SelectCommand;
import by.patrusova.project.command.impl.registration.RegistrationCommand;
import by.patrusova.project.command.impl.registration.RegistrationRedirectCommand;
import by.patrusova.project.command.impl.show.*;

public enum CommandEnum {

    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    CATALOGUE {
        {
            this.command = new CatalogueCommand();
        }
    },
    INFO {
        {
            this.command = new InfoCommand();
        }
    },
    BASKET {
        {
            this.command = new BasketCommand();
        }
    },
    REGREDIRECT {
        {
            this.command = new RegistrationRedirectCommand();
        }
    },
    BACKTOMAIN {
        {
            this.command = new BackToMainCommand();
        }
    },
    ADDORDER {
        {
            this.command = new AddOrderCommand();
        }
    },
    CHANGEREDIRECT {
        {
            this.command = new ChangeRedirectCommand();
        }
    },
    CHANGEBURIALREDIRECT {
        {
            this.command = new ChangeBurialRedirectCommand();
        }
    },
    CHANGECLEANERREDIRECT {
        {
            this.command = new ChangeCleanerRedirectCommand();
        }
    },
    CHANGECLIENTREDIRECT {
        {
            this.command = new ChangeClientRedirectCommand();
        }
    },
    CHANGESERVICEREDIRECT {
        {
            this.command = new ChangeServiceRedirectCommand();
        }
    },
    ADDSERVICEREDIRECT {
        {
            this.command = new AddServiceRedirectCommand();
        }
    },
    CHANGEORDERREDIRECT {
        {
            this.command = new ChangeOrderRedirectCommand();
        }
    },
    CHANGEUSER {
        {
            this.command = new ChangeUserCommand();
        }
    },
    CHANGEBURIAL {
        {
            this.command = new ChangeBurialCommand();
        }
    },
    LOCALE {
        {
            this.command = new LocaleCommand();
        }
    },
    SHOWGUESTS {
        {
            this.command = new ShowGuestsCommand();
        }
    },
    SHOWADMINS {
        {
            this.command = new ShowAdminsCommand();
        }
    },
    SHOWCATALOGUE {
        {
            this.command = new ShowCatalogueCommand();
        }
    },
    SHOWCLEANERS {
        {
            this.command = new ShowCleanersCommand();
        }
    },
    SHOWCLIENTS {
        {
            this.command = new ShowClientsCommand();
        }
    },
    SHOWORDERS {
        {
            this.command = new ShowOrdersCommand();
        }
    },
    SHOWORDERSCLEANER {
        {
            this.command = new ShowOrdersCleanerCommand();
        }
    },
    SHOWORDERSCLIENT {
        {
            this.command = new ShowOrdersClientCommand();
        }
    },
    CHANGEGUEST {
        {
            this.command = new ChangeGuestCommand();
        }
    },
    CHANGECLIENT {
        {
            this.command = new ChangeClientCommand();
        }
    },
    CHANGECLEANER {
        {
            this.command = new ChangeCleanerCommand();
        }
    },
    CHANGEORDER {
        {
            this.command = new ChangeOrderCommand();
        }
    },
    CHANGESERVICE {
        {
            this.command = new ChangeServiceCommand();
        }
    },
    DELETEENTITY {
        {
            this.command = new DeleteEntityCommand();
        }
    },
    ADDSERVICE {
        {
            this.command = new AddServiceCommand();
        }
    },
    SETMARK {
        {
            this.command = new SetMarkCommand();
        }
    },
    SETNOTES {
        {
            this.command = new SetNotesCommand();
        }
    },
    CANCEL {
        {
            this.command = new CancelCommand();
        }
    },
    SELECT {
        {
            this.command = new SelectCommand();
        }
    },
    PLACEORDER {
        {
            this.command = new PlaceOrderCommand();
        }
    },
    ORDERREDIRECT {
        {
            this.command = new OrderRedirectCommand();
        }
    },
    MAIL {
        {
            this.command = new MailCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}