
public class Car {
    private String plateNumber;
    private String carColor;
    private String carBrand;
    private String entryTime;



    public Car(String plateNumber, String carColor, String carBrand, String entryTime) {
        this.plateNumber = plateNumber;
        this.carColor = carColor;
        this.carBrand = carBrand;
        this.entryTime = entryTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }
}
