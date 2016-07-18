package cn.hisforce.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Johnson on 2016/7/17.
 */
@Entity
public class AngelGuiderShare implements Serializable {
    private static final long serialVersionUID = 1253903799438056733L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long hospitalId;
    private String hospitalName;
    private String patientName;
    private Long angelGuider;
    private String angelGuiderName;
    private Long registrationId;
    private String orderNo;
    private Double amount;
    private Integer type;
    private Double platformShareAmount;
    private Double angelGuiderShareAmount;
    private Double agentShareAmount;
    private Double hospitalPayableAmount;
    private Date createDate = new Date();
    private Double recipeShare;
    private Double prescriptionShare;
    private Double agentShare;
    private Double angelGuiderShare;
    private Double platformShare;
    private Double recommendationFee;
    private Long agency;

    public AngelGuiderShare() {
    }

    public AngelGuiderShare(MedicalOrder order) {
        this(order.getRegistration());
        this.amount = order.getPaidAmount();
        this.orderNo = order.getOrderNo();
        this.type = order.getType();
    }

    public AngelGuiderShare(Registration registration) {
        this(registration.getHospital());
        this.registrationId = registration.getId();
        this.angelGuider = registration.getAngelGuider().getId();
        this.angelGuiderName = registration.getBusinessPeopleName();
        this.patientName = registration.getPatientName();
        this.hospitalName = registration.getHospitalName();
        this.type = 0;
        this.agency = registration.getAngelGuider().getAgency();
        this.amount = registration.getHospital().getRecommendationFee();
    }

    private AngelGuiderShare(Hospital hospital) {
        this.hospitalId = hospital.getId();
        this.agentShare = hospital.getAgentShare();
        this.angelGuiderShare = hospital.getAngelGuiderShare();
        this.platformShare = hospital.getPlatformShare();
        this.recipeShare = hospital.getRecipeShare();
        this.recommendationFee = hospital.getRecommendationFee();
        this.prescriptionShare = hospital.getPrescriptionShare();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Long hospitalId) {
        this.hospitalId = hospitalId;
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

    public Long getAngelGuider() {
        return angelGuider;
    }

    public void setAngelGuider(Long angelGuider) {
        this.angelGuider = angelGuider;
    }

    public String getAngelGuiderName() {
        return angelGuiderName;
    }

    public void setAngelGuiderName(String angelGuiderName) {
        this.angelGuiderName = angelGuiderName;
    }

    public Long getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPlatformShareAmount() {
        return platformShareAmount;
    }

    public void setPlatformShareAmount(Double platformShareAmount) {
        this.platformShareAmount = platformShareAmount;
    }

    public Double getAngelGuiderShareAmount() {
        return angelGuiderShareAmount;
    }

    public void setAngelGuiderShareAmount(Double angelGuiderShareAmount) {
        this.angelGuiderShareAmount = angelGuiderShareAmount;
    }

    public Double getAgentShareAmount() {
        return agentShareAmount;
    }

    public void setAgentShareAmount(Double agentShareAmount) {
        this.agentShareAmount = agentShareAmount;
    }

    public Double getHospitalPayableAmount() {
        return hospitalPayableAmount;
    }

    public void setHospitalPayableAmount(Double hospitalPayableAmount) {
        this.hospitalPayableAmount = hospitalPayableAmount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getRecipeShare() {
        return recipeShare;
    }

    public void setRecipeShare(Double recipeShare) {
        this.recipeShare = recipeShare;
    }

    public Double getPrescriptionShare() {
        return prescriptionShare;
    }

    public void setPrescriptionShare(Double prescriptionShare) {
        this.prescriptionShare = prescriptionShare;
    }

    public Double getAgentShare() {
        return agentShare;
    }

    public void setAgentShare(Double agentShare) {
        this.agentShare = agentShare;
    }

    public Double getAngelGuiderShare() {
        return angelGuiderShare;
    }

    public void setAngelGuiderShare(Double angelGuiderShare) {
        this.angelGuiderShare = angelGuiderShare;
    }

    public Double getPlatformShare() {
        return platformShare;
    }

    public void setPlatformShare(Double platformShare) {
        this.platformShare = platformShare;
    }

    public Double getRecommendationFee() {
        return recommendationFee;
    }

    public void setRecommendationFee(Double recommendationFee) {
        this.recommendationFee = recommendationFee;
    }


    @Transient
    private boolean isRecommendationFee() {
        return type == 0;
    }

    @Transient
    public Integer getOutTransactionCode() {
        if (type == 0) return 103;
        if (type == 1) return 101;
        if (type == 2) return 102;
        return null;
    }

    @Transient
    public Integer getInTransactionCode() {
        if (type == 0) return 205;
        if (type == 1) return 203;
        if (type == 2) return 204;
        return null;
    }

    public void settle(Registration registration) {
        this.setAgentShareAmount(registration.isRecommendedByAgency() ? amount * agentShare : 0);
        this.setAngelGuiderShareAmount(amount * angelGuiderShare);
        this.setHospitalPayableAmount(isRecommendationFee() ? this.recommendationFee : (type == 1 ? amount * recipeShare : prescriptionShare * amount));
        this.setPlatformShareAmount(amount - getAgentShareAmount() - getAngelGuiderShareAmount());
    }

    public Long getAgency() {
        return agency;
    }

    public void setAgency(Long agency) {
        this.agency = agency;
    }
}
