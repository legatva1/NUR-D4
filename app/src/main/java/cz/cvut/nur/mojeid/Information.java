package cz.cvut.nur.mojeid;

/**
 * Created by Václav Legát on 24.11.14.
 */
public class Information {

    private String name;
    private boolean isSelected;
    private boolean isRequired;

    public Information(String name,boolean isRequired) {
        this.name = name;
        this.isRequired=isRequired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean isRequired) {
        this.isRequired = isRequired;
    }

}
