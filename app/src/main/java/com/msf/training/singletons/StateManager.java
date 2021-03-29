package com.msf.training.singletons;

public class StateManager {
    private static StateManager instance;
    Boolean switchState1 = false, switchState2 = false, switchState3 = false;


    private StateManager() {

    }

    public static StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
        }
        return instance;
    }

    public Boolean getSwitchState1() {
        return switchState1;
    }

    public void setSwitchState1(Boolean bool) {
        this.switchState1 = bool;
    }

    public Boolean getSwitchState2() {
        return switchState2;
    }

    public void setSwitchState2(Boolean bool) {
        this.switchState2 = bool;
    }

    public Boolean getSwitchState3() {
        return switchState3;
    }

    public void setSwitchState3(Boolean bool) {
        this.switchState3 = bool;
    }
}
