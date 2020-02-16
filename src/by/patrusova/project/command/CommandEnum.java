package by.patrusova.project.command;

import by.patrusova.project.command.impl.*;

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
    CHANGEINFO {
        {
            this.command = new ChangeInfoCommand();
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
    ADDENTITY{
        {
            this.command = new AddEntityCommand();
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