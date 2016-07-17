package cn.hisforce.repository;

import cn.hisforce.domain.AngelGuiderShare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Johnson on 2016/7/17.
 */
public interface AngelGuiderShareRepository extends JpaRepository<AngelGuiderShare, Long> {
    @Query(value = "select a from AngelGuiderShare a where a.registrationId = ?1 and a.recommendationFee>0")
    public AngelGuiderShare findByRegistrationId(Long registrationId);
}
