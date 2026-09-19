/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.siem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class IncidentContainmentAuthorizationService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.incidentId() == null || request.incidentId().isBlank()) blockers.add("安全事件编号不能为空");
        if (!request.severityClassified()) blockers.add("事件严重度未分级");
        if (!request.evidencePreserved()) blockers.add("关键取证材料未保全");
        if (!request.scopeConfirmed()) blockers.add("事件影响范围未确认");
        if (!request.affectedAssetsIdentified()) blockers.add("受影响资产未识别");
        if (!request.containmentPlanApproved()) blockers.add("隔离方案未批准");
        if (request.legalReviewRequired() && !request.legalReviewComplete()) blockers.add("必要法务或监管评审未完成");
        if (!request.commanderSeparated()) blockers.add("处置执行人与事件指挥未职责分离");
        if (!request.auditReady()) blockers.add("事件处置审计证据不完整");
        if (!request.businessOwnerNotified()) actions.add("通知受影响业务责任人");
        if (!request.rollbackReady()) actions.add("准备隔离回退方案");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.CONTAIN : Decision.COORDINATE;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { CONTAIN, COORDINATE, BLOCKED }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(String incidentId, boolean severityClassified, boolean evidencePreserved,
                          boolean scopeConfirmed, boolean affectedAssetsIdentified,
                          boolean containmentPlanApproved, boolean businessOwnerNotified,
                          boolean legalReviewRequired, boolean legalReviewComplete,
                          boolean rollbackReady, boolean commanderSeparated, boolean auditReady) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
