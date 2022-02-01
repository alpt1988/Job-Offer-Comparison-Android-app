package edu.gatech.seclass.jobcompare6300.models;

import java.util.Objects;

public class Location {

    private String city;
    private String state;

    public Location() {

    }

    public Location(String city, String state) throws IllegalArgumentException {
        String errorText = "";
        if (city != null && !city.isEmpty())
            this.city = city;
        else
            errorText += "City cannot be blank";
        if (!(state == null) && !state.isEmpty()) {
            this.state = state;
        } else {
            if (!errorText.isEmpty()) { errorText += "\n"; }
            errorText += "State cannot be blank";
        }
        if (!errorText.isEmpty())
            throw new IllegalArgumentException(errorText);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if (city != null && !city.isEmpty())
            this.city = city;
        else
            throw new IllegalArgumentException("City cannot be blank");
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if (state != null && !state.isEmpty())
            this.state = state;
        else
            throw new IllegalArgumentException("State cannot be blank");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(city, location.city) &&
                Objects.equals(state, location.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, state);
    }
}
