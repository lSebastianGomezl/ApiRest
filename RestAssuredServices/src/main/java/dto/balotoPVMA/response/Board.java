package dto.balotoPVMA.response;

import java.util.ArrayList;

public class Board {

    public Object systemBetType;
    public boolean quickpick;
    public int stake;
    public ArrayList<String> selections;
    public Object soldOut;
    public boolean addonPlayed;
    public Object drawNames;
    public Object taxes;

    public Object getSystemBetType() {
        return systemBetType;
    }

    public void setSystemBetType(Object systemBetType) {
        this.systemBetType = systemBetType;
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

    public ArrayList<String> getSelections() {
        return selections;
    }

    public void setSelections(ArrayList<String> selections) {
        this.selections = selections;
    }

    public Object getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Object soldOut) {
        this.soldOut = soldOut;
    }

    public boolean isAddonPlayed() {
        return addonPlayed;
    }

    public void setAddonPlayed(boolean addonPlayed) {
        this.addonPlayed = addonPlayed;
    }

    public Object getDrawNames() {
        return drawNames;
    }

    public void setDrawNames(Object drawNames) {
        this.drawNames = drawNames;
    }

    public Object getTaxes() {
        return taxes;
    }

    public void setTaxes(Object taxes) {
        this.taxes = taxes;
    }
}
