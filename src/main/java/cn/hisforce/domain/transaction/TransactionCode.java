package cn.hisforce.domain.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Johnson on 2016/7/18.
 */
@Entity
public class TransactionCode implements Serializable {
    private static final long serialVersionUID = 6556119174698187457L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer code;
    private String name;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
