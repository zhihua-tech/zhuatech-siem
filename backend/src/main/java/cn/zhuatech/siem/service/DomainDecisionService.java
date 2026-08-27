/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.siem.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { int score=Math.min(100,request.severity()*12+request.assetCriticality()*10+(int)Math.round(request.confidence()/5)+Math.min(15,request.affectedAccounts()*3)+(request.privilegedAccount()?15:0));List<String> actions=new ArrayList<>();if(score>=70&&!request.containmentStarted())actions.add("立即启动账号与终端遏制");if(request.privilegedAccount())actions.add("吊销并轮换特权凭据");if(request.affectedAccounts()>0)actions.add("核查受影响账号活动时间线");return riskResult(score,actions,"MONITOR","INVESTIGATE","CRITICAL",Map.of("severity",request.severity(),"assetCriticality",request.assetCriticality(),"confidence",request.confidence())); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String eventNo,
        @Min(1) @Max(5) int severity,
        @Min(1) @Max(5) int assetCriticality,
        @DecimalMin("0") @DecimalMax("100") double confidence,
        @PositiveOrZero int affectedAccounts,
        boolean privilegedAccount,
        boolean containmentStarted) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
