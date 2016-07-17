package cn.hisforce.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Johnson on 2016/7/17.
 */
@Entity
public class Hospital implements Serializable {
    private static final long serialVersionUID = -6453082215367364662L;
    @Id
    private Long id;
    private Double recipeShare;
    private Double prescriptionShare;
    private Double agentShare;
    private Double angelGuiderShare;
    private Double platformShare;
    private Double recommendationFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
