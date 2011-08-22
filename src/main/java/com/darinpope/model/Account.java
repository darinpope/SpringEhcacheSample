package com.darinpope.model;

import java.io.Serializable;
import java.util.List;

public class Account implements Serializable {
  private String firstName;
  private String lastName;
  private List<String> appointmentIds;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public List<String> getAppointmentIds() {
    return appointmentIds;
  }

  public void setAppointmentIds(List<String> appointmentIds) {
    this.appointmentIds = appointmentIds;
  }
}
