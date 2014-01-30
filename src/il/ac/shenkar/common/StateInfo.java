package il.ac.shenkar.common;

/**
 * Created by Amsalem on 30/01/14.
 */
public class StateInfo
{
    private String stateName = "United State";
    private String stateCurrencyCode = "USD";
    private String stateCapitalCity = "Washington D.C.";
    private long statePopulation = 317566000;
    private double  stateArea = 9826675;

    public StateInfo(String stateName, String stateCurrencyCode, String stateCapitalCity, long statePopulation, double stateArea) {
        this.stateName = stateName;
        this.stateCurrencyCode = stateCurrencyCode;
        this.stateCapitalCity = stateCapitalCity;
        this.statePopulation = statePopulation;
        this.stateArea = stateArea;
    }

    public StateInfo()
    {
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCurrencyCode() {
        return stateCurrencyCode;
    }

    public void setStateCurrencyCode(String stateCurrencyCode) {
        this.stateCurrencyCode = stateCurrencyCode;
    }

    public String getStateCapitalCity() {
        return stateCapitalCity;
    }

    public void setStateCapitalCity(String stateCapitalCity) {
        this.stateCapitalCity = stateCapitalCity;
    }

    public long getStatePopulation() {
        return statePopulation;
    }

    public void setStatePopulation(long statePopulation) {
        this.statePopulation = statePopulation;
    }

    public double getStateArea() {
        return stateArea;
    }

    public void setStateArea(double stateArea) {
        this.stateArea = stateArea;
    }
}
