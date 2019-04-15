package com.tortoiselala.entity;

public class IncomeUnitForShow {
     private String labName;
     private String doctorID;
     private String doctorName;
     private String type;
     private int num;
     private double income;

     public IncomeUnitForShow() {
     }

     public IncomeUnitForShow(String labName, String doctorID, String doctorName, String type, int num, double income) {
          this.labName = labName;
          this.doctorID = doctorID;
          this.doctorName = doctorName;
          this.type = type;
          this.num = num;
          this.income = income;
     }

     public String getLabName() {
          return labName;
     }

     public void setLabName(String labName) {
          this.labName = labName;
     }

     public String getDoctorID() {
          return doctorID;
     }

     public void setDoctorID(String doctorID) {
          this.doctorID = doctorID;
     }

     public String getDoctorName() {
          return doctorName;
     }

     public void setDoctorName(String doctorName) {
          this.doctorName = doctorName;
     }

     public String getType() {
          return type;
     }

     public void setType(String type) {
          this.type = type;
     }

     public int getNum() {
          return num;
     }

     public void setNum(int num) {
          this.num = num;
     }

     public double getIncome() {
          return income;
     }

     public void setIncome(double income) {
          this.income = income;
     }

     @Override
     public String toString() {
          return "IncomeUnitForShow{" +
                  "labName='" + labName + '\'' +
                  ", doctorID='" + doctorID + '\'' +
                  ", doctorName='" + doctorName + '\'' +
                  ", type='" + type + '\'' +
                  ", num=" + num +
                  ", income=" + income +
                  '}';
     }
}
