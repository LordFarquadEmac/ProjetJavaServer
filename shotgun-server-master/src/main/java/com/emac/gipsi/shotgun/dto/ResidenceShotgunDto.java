package com.emac.gipsi.shotgun.dto;

import java.util.List;

public class ResidenceShotgunDto {
	private int    id;
    private String name;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name of the Residence
     */
    public String getName() {
        return name;
    }

    /**
     * @param nomFamille
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
