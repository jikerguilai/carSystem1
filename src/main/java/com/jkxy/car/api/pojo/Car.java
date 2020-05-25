package com.jkxy.car.api.pojo;

//设置车的属性

public class Car {
    private int id;
    private String carName;
    private String carType;
    private String price;
    private String carSeries;

    private int carNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCarSeries() {
        return carSeries;
    }

    public void setCarSeries(String carSeries) {
        this.carSeries = carSeries;
    }

    public void setCarNum(int carNum){
        this.carNum=carNum;
    }

    public int getCarNum(){ //返回车的数量
        return carNum;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carName='" + carName + '\'' +
                ", carType='" + carType + '\'' +
                ", price='" + price + '\'' +
                ", carSeries='" + carSeries + '\'' +
                ", carNum='" + carNum+ '\'' +
                '}';
    }
}
