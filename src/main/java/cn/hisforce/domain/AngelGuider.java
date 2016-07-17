package cn.hisforce.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Created by Johnson on 2016/7/17.
 */
@Entity
public class AngelGuider {
    @Id
    private Long id;
    private String name;
    private Long agency;
    private String agencyName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAgency() {
        return agency;
    }

    public void setAgency(Long agency) {
        this.agency = agency;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }
    @Transient
    public boolean isAgent(){
        return agency == null;
    }
}
