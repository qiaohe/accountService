package cn.hisforce.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Johnson on 2016/7/17.
 */
@Entity
public class Registration implements Serializable {
    private static final long serialVersionUID = 7156914353350576010L;
    @Id
    private Long id;
    private String hospitalName;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;
    private String patientName;
    private Integer registrationType;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "businessPeopleId")
    private AngelGuider angelGuider;
    private String businessPeopleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(Integer registrationType) {
        this.registrationType = registrationType;
    }

    public AngelGuider getAngelGuider() {
        return angelGuider;
    }

    public void setAngelGuider(AngelGuider angelGuider) {
        this.angelGuider = angelGuider;
    }

    public String getBusinessPeopleName() {
        return businessPeopleName;
    }

    public void setBusinessPeopleName(String businessPeopleName) {
        this.businessPeopleName = businessPeopleName;
    }
    @Transient
    public boolean isRecommendedByAgency(){
        return angelGuider.isAgent();
    }
}
