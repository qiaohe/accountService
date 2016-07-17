package cn.hisforce.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Johnson on 2016/7/17.
 */
@Entity
public class TransactionFlow implements Serializable {
    private static final long serialVersionUID = 1793374721034690047L;
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
