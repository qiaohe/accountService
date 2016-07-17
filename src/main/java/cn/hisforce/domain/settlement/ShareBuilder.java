package cn.hisforce.domain.settlement;

import cn.hisforce.domain.AngelGuiderShare;
import cn.hisforce.domain.MedicalOrder;
import cn.hisforce.domain.Registration;

/**
 * Created by Johnson on 2016/7/17.
 */
public final class ShareBuilder {


    public AngelGuiderShare build(Registration registration) {
        AngelGuiderShare share = new AngelGuiderShare(registration);
        share.settle(registration);
        return share;
    }

    public AngelGuiderShare build(MedicalOrder order) {
        AngelGuiderShare share = new AngelGuiderShare(order);
        share.settle(order.getRegistration());
        return share;
    }

}
