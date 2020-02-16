package by.patrusova.project.util.stringholder;

public enum Pages {

    PAGE_INDEX("page.index"),
    PAGE_INFO("page.info"),
    PAGE_MAIN_ADMIN("page.mainadmin"),
    PAGE_MAIN_CLIENT("page.mainclient"),
    PAGE_MAIN_CLEANER("page.maincleaner"),
    PAGE_LOGIN("page.login"),
    PAGE_CHANGE("page.changeform"),
    PAGE_CHANGE_BURIAL("page.changeburialform"),
    PAGE_REG("page.registrationform"),
    PAGE_REG_TRUE("page.registrationtrue"),
    PAGE_PROFILE("page.profile"),
    PAGE_MAIL("page.sendmail"),
    PAGE_CATALOGUE("page.catalogue"),
    PAGE_BASKET("page.basket"),
    PAGE_GUESTLIST("page.guestlist"),
    PAGE_ADMINLIST("page.adminlist"),
    PAGE_CLEANERLIST("page.cleanerlist"),
    PAGE_CLIENTLIST("page.clientlist"),
    PAGE_ORDERLIST("page.orderlist"),
    PAGE_CATALOGUELIST("page.cataloguelist"),
    PAGE_CHANGE_GUEST("page.changeguest");

    private String value;

    Pages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}