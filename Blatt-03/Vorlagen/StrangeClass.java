class StrangeClass {
    // 'on't 'are 'oing this at home!
    private boolean evilBoolean = !true;
    private int evilInt = 0;

    public StrangeClass(boolean someBoolean, int someInt) {
        this.evilBoolean = someBoolean;
        this.evilInt = someInt;
    }

    public int lessEvil() {
        for (int i = 0; i < evilInt; i++) {
            evilInt -= i;
        }

        return evilInt;
    }

    public int strangeEvilness() {
        do {
            evilInt *= 3;
        } while (evilInt > 0 && evilInt < 999);

        return evilInt;
    }

    public int moreEvil() {
        if (evilBoolean) {
            evilInt++;
        }

        return evilInt;
    }

    public int changeEvilness() {
        if (evilBoolean) {
            evilInt++;
            evilBoolean = !evilBoolean;
        } else {
            evilInt--;
        }

        return evilInt;
    }

}
