# 企业安全事件隔离授权

`POST /api/enterprise/siem/incident-containment-authorization` 在隔离主机、账号或网络前检查严重度、证据保全、影响范围、资产、隔离方案、法务评审、回退与职责分离。

- `CONTAIN`：处置和治理条件完整，可以执行隔离。
- `COORDINATE`：不存在硬性阻断，但需通知业务责任人或准备回退。
- `BLOCKED`：分级、证据、范围、资产、方案、法务或审计控制失败。

接口可连接 SOAR、EDR、IAM 和网络控制设备，但隔离动作始终经过统一授权。
