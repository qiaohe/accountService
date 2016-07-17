package cn.hisforce.domain.settlement;

import cn.hisforce.domain.AngelGuiderShare;
import cn.hisforce.domain.MedicalOrder;
import cn.hisforce.domain.Registration;
import cn.hisforce.repository.AngelGuiderShareRepository;
import cn.hisforce.repository.MedicalOrderRepository;
import cn.hisforce.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Johnson on 2016/7/17.
 */
@Component(value = "settlementCenter")
public class SettlementCenter {
    private static final String ORDER_MESSAGE_PREFIX = "000";
    @Autowired
    private MedicalOrderRepository medicalOrderRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private AngelGuiderShareRepository angelGuiderShareRepository;


    private boolean isOrderMessage(final String referenceNo) {
        return referenceNo.startsWith(ORDER_MESSAGE_PREFIX);
    }

    private void settleOrder(MedicalOrder order) {
        AngelGuiderShare share = new AngelGuiderShare(order);
        share.settle(order.getRegistration());
        angelGuiderShareRepository.save(share);
    }

    private void settleRegistration(Registration registration) {
        AngelGuiderShare share = new AngelGuiderShare(registration);
        share.settle(registration);
        angelGuiderShareRepository.save(share);
    }

    private boolean isSettledBy(Long registrationId) {
        return angelGuiderShareRepository.findByRegistrationId(registrationId) != null;
    }

    public void settle(String message) {
        if (isOrderMessage(message)) {
            settleOrder(medicalOrderRepository.findByOrderNo(message));
            return;
        }
        final Long registrationId = Long.valueOf(message);
        if (!isSettledBy(registrationId))
            settleRegistration(registrationRepository.findOne(registrationId));

    }
}