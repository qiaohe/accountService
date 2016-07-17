package cn.hisforce.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Johnson on 2016/7/17.
 */
@Entity
public class MedicalOrder implements Serializable {
    private static final long serialVersionUID = -1094483133640923720L;
    @Id
    private Long id;
    private String orderNo;
    private Double paidAmount;
    private Integer type;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "registrationId")
    private Registration registration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }
}
