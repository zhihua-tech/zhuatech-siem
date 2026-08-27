/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.siem.domain;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    public DomainCatalog() {
        actions.put("TRIAGE", new WorkflowAction("TRIAGE", "完成告警研判", List.of("草稿"), "调查中", "OPERATOR"));
        actions.put("CONTAIN", new WorkflowAction("CONTAIN", "执行遏制", List.of("调查中"), "已遏制", "ADMIN"));
        actions.put("CLOSE", new WorkflowAction("CLOSE", "复盘关闭", List.of("已遏制"), "已关闭", "ADMIN"));
    }
    public String systemName() { return "知华科技安全信息与事件管理系统"; }
    public String scene() { return "数据源、日志管道、检测规则、告警、事件、调查、响应、威胁情报与合规"; }
    public String initialStatus() { return "草稿"; }
    public String partyLabel() { return "资产/安全事件"; }
    public String amountLabel() { return "风险损失"; }
    public String quantityLabel() { return "事件数量"; }
    public String dueLabel() { return "响应期限"; }
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("DATA_SOURCE", "日志数据源", "接入终端、网络、云、应用和身份日志"),
            new ModuleDefinition("PIPELINE", "日志管道", "解析、标准化、富化、脱敏和存储"),
            new ModuleDefinition("DETECTION", "检测规则", "管理关联、阈值、行为和威胁规则"),
            new ModuleDefinition("ALERT", "安全告警", "聚合、去重、分级和分派告警"),
            new ModuleDefinition("INCIDENT", "安全事件", "记录范围、影响、证据和响应状态"),
            new ModuleDefinition("INVESTIGATION", "调查分析", "构建时间线、实体关系和攻击路径"),
            new ModuleDefinition("RESPONSE", "响应编排", "执行隔离、封禁、重置和通知流程"),
            new ModuleDefinition("THREAT_INTEL", "威胁情报", "管理IOC、信誉和命中记录"),
            new ModuleDefinition("COMPLIANCE", "合规报告", "输出控制证据、留存和审计报表")
        ); }
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    public record ModuleDefinition(String code,String name,String description) {}
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
