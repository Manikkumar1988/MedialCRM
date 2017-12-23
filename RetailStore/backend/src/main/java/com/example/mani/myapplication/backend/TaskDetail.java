package com.example.mani.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Created by mani on 21/12/17.
 */

@Entity
public class TaskDetail {

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }

  public long getStartDate() {
    return startDate;
  }

  public void setStartDate(long startDate) {
    this.startDate = startDate;
  }

  public long getEndDate() {
    return endDate;
  }

  public void setEndDate(long endDate) {
    this.endDate = endDate;
  }

  public int getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(int taskStatus) {
    this.taskStatus = taskStatus;
  }

  public String getSalesRep() {
    return salesRep;
  }

  public void setSalesRep(String salesRep) {
    this.salesRep = salesRep;
  }

  public String getPharmacyName() {
    return pharmacyName;
  }

  public void setPharmacyName(String pharmacyName) {
    this.pharmacyName = pharmacyName;
  }

  @Id
  public String taskName;
  public String taskDescription;
  public long startDate, endDate;
  public int taskStatus; //Save as enum
  public String salesRep;
  public String pharmacyName;
}
