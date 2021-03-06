package cn.hisforce.domain;

import cn.hisforce.domain.transaction.TransactionCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

/**
 * Created by Johnson on 2016/7/17.
 */
@Entity
@Table(name = "AngelGuiderTransactionFlow")
public class TransactionFlow implements Serializable {
    private static final long serialVersionUID = 1793374721034690047L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flowNo;
    private Long uid;
    private String accountNo;
    private Long accountId;
    private Date createDate = new Date();
    private String comment;
    private Integer transactionCode;
    private Double amount;
    private Long shareId;
    private Double currentBalance;
    private Integer type = 0;
    private Integer status = 2;


    public TransactionFlow() {
    }

    public TransactionFlow(String flowNo, Long uid, String accountNo, Long accountId, String comment, Integer transactionCode, Double amount, Long shareId, Double currentBalance) {
        this();
        this.flowNo = flowNo;
        this.uid = uid;
        this.accountNo = accountNo;
        this.accountId = accountId;
        this.comment = comment;
        this.transactionCode = transactionCode;
        this.amount = amount;
        this.shareId = shareId;
        this.currentBalance = currentBalance;
    }

    public TransactionFlow(Account account, TransactionCode code, AngelGuiderShare share, Double amount, String flowNo) {
        this(flowNo, account.getUid(), account.getAccountNo(), account.getId(), code.getComment(), code.getCode(), amount, share != null ? share.getId() : null, account.getBalance() + amount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(Integer transactionCode) {
        this.transactionCode = transactionCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
