/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.siem.controller;

import cn.zhuatech.siem.common.ApiResponse;
import cn.zhuatech.siem.service.IncidentContainmentAuthorizationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/siem")
public class IncidentContainmentAuthorizationController {
    private final IncidentContainmentAuthorizationService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public IncidentContainmentAuthorizationController(IncidentContainmentAuthorizationService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/incident-containment-authorization")
    public ApiResponse<?> assess(@RequestBody IncidentContainmentAuthorizationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
