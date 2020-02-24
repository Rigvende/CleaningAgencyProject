package by.patrusova.project.util.stringholder;

public enum Page {

    PAGE_INDEX("page.index"),
    PAGE_ADD_SERVICE("page.addservice"),
    PAGE_INFO("page.info"),
    PAGE_MAIN_ADMIN("page.mainadmin"),
    PAGE_MAIN_CLIENT("page.mainclient"),
    PAGE_MAIN_CLEANER("page.maincleaner"),
    PAGE_LOGIN("page.login"),
    PAGE_CHANGE("page.changeform"),
    PAGE_CHANGE_BURIAL("page.changeburialform"),
    PAGE_CHANGE_CLEANER("page.changecleaner"),
    PAGE_CHANGE_CLIENT("page.changeclient"),
    PAGE_CHANGE_SERVICE("page.changeservice"),
    PAGE_CHANGE_ORDER("page.changeorder"),
    PAGE_REG("page.registrationform"),
    PAGE_REG_TRUE("page.registrationtrue"),
    PAGE_PROFILE("page.profile"),
    PAGE_MAIL("page.mail"),
    PAGE_SEND("page.sendmail"),
    PAGE_CONFIRM("page.confirm"),
    PAGE_CONFIRMFALSE("page.confirmfalse"),
    PAGE_CATALOGUE("page.catalogue"),
    PAGE_BASKET("page.basket"),
    PAGE_GUESTLIST("page.guestlist"),
    PAGE_ADMINLIST("page.adminlist"),
    PAGE_CLEANERLIST("page.cleanerlist"),
    PAGE_CLIENTLIST("page.clientlist"),
    PAGE_ORDERLIST("page.orderlist"),
    PAGE_CATALOGUELIST("page.cataloguelist");

    private String value;

    Page(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}