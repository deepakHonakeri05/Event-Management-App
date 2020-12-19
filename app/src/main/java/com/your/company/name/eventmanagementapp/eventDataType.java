package com.your.company.name.eventmanagementapp;

public class eventDataType {

    //Declare (Strings) eventname, description, registrationlink, phone;

    //create constructor- eventDatatype(eventname, description, link, phone){ this.eventname = eventname etc};

    //TO DO: create get and set methods

    //public string getEventName(){ return eventname;};
    // public void setEventName(String newEventName) {this.eventname = newEventName;}

    private String eventname;
    private String description;
    private String registrationlink;
    private String phone;
    //create constructor- eventDatatype(eventname, description, link, phone){ this.eventname = eventname etc};

    public eventDataType(){}

    public eventDataType(String eventname, String description, String registrationlink, String phone)
    {
        this.eventname = eventname;
        this.description = description;
        this.registrationlink = registrationlink;
        this.phone = phone;
    }
    //TO DO: create get and set methods
    public String getEventName()
    {
        return eventname;
    }

    public void setEventName(String newEventName)
    {
        this.eventname = newEventName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String newDescription)
    {
        this.description = newDescription;
    }

    public String getRegistrationlink()
    {
        return registrationlink;
    }

    public void setRegistrationlink(String newRegistrationlink)
    {
        this.registrationlink = newRegistrationlink;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String newPhone)
    {
        this.phone = newPhone;
    }
}
