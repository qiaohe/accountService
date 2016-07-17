package cn.hisforce.repository;

import cn.hisforce.domain.MedicalOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Johnson on 2016/7/17.
 */
public interface MedicalOrderRepository extends JpaRepository<MedicalOrder, Long> {
    public MedicalOrder findByOrderNo(String orderNo);
}
