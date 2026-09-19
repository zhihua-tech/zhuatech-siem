/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.siem.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class IncidentContainmentAuthorizationServiceTest {
    private final IncidentContainmentAuthorizationService service = new IncidentContainmentAuthorizationService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void authorizesControlledContainment() {
        var result = service.assess(new IncidentContainmentAuthorizationService.Request("INC-100", true, true,
                true, true, true, true, false, true, true, true, true));
        assertThat(result.decision()).isEqualTo(IncidentContainmentAuthorizationService.Decision.CONTAIN);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void routesCoordinationGapsToIncidentCommander() {
        var result = service.assess(new IncidentContainmentAuthorizationService.Request("INC-101", true, true,
                true, true, true, false, false, true, false, true, true));
        assertThat(result.actions()).hasSize(2);
        assertThat(result.decision()).isEqualTo(IncidentContainmentAuthorizationService.Decision.COORDINATE);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUnsafeContainment() {
        var result = service.assess(new IncidentContainmentAuthorizationService.Request("", false, false,
                false, false, false, false, true, false, false, false, false));
        assertThat(result.blockers()).hasSize(9);
        assertThat(result.decision()).isEqualTo(IncidentContainmentAuthorizationService.Decision.BLOCKED);
    }
}
