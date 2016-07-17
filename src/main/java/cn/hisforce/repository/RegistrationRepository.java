package cn.hisforce.repository;

import cn.hisforce.domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Johnson on 2016/7/17.
 */
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
}
