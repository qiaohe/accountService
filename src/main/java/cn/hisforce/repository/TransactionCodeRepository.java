package cn.hisforce.repository;

import cn.hisforce.domain.transaction.TransactionCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Johnson on 2016/7/18.
 */
public interface TransactionCodeRepository extends JpaRepository<TransactionCode, Long> {
}
