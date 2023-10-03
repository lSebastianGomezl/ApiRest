package dto.balotoPVMA;

import java.util.ArrayList;

public class Board {
    public boolean quickpick;
    public int stake;
    public boolean addonPlayed;
    public ArrayList<String> selections;

    public ArrayList<String> getSelections() {
        return selections;
    }

    public void setSelections(ArrayList<String> selections) {
        this.selections = selections;
    }

    public boolean isQuickpick() {
        return quickpick;
    }

    public void setQuickpick(boolean quickpick) {
        this.quickpick = quickpick;
    }

    public int getStake() {
        return stake;
    }

    public void setStake(int stake) {
        this.stake = stake;
    }

    public boolean isAddonPlayed() {
        return addonPlayed;
    }

    public void setAddonPlayed(boolean addonPlayed) {
        this.addonPlayed = addonPlayed;
    }
}
