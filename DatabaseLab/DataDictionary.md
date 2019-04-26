## 数据字典
### USER

| 字段   | 类型 | 约束 | 备注 |
| ----- | ------ | ---- | ---- |
| UID   | BIGINT | 主键，自增 |  |
| NAME  | CHAR(10) | 非空 |  |
| SEX   | CHAR(1) | 非空 {男，女} |  |
| BYEAR | INT | 非空 |  |
| CITY  | CHAR() | 非空 |  |

## LABEL

| 字段  | 类型   | 约束       | 备注 |
| ----- | ------ | ---------- | ---- |
| LID   | BIGINT | 自增，主键 |      |
| LNAME | CHAR() | 非空       |      |

### MBLOG

| 字段   | 类型   | 约束               | 备注 |
| ------ | ------ | ------------------ | ---- |
| BID    | BIGINT | 自增，主键         |      |
| TITLE  | CHAR() | 非空               |      |
| UID    | BIGINT | 非空，外键USER.UID |      |
| PYEAR  | INT    | 非空               |      |
| PMONTH | INT    | 非空               |      |
| PDAY   | INT    | 非空               |      |
| CONT   | CHAR() | 非空               |      |

### B_L

| 字段 | 类型 | 约束                | 备注 |
| ---- | ---- | ------------------- | ---- |
| BID  | LONG | 非空，外键MBLOG.BID |      |
| UID  | LONG | 非空，外键USER.UID  |      |

### FOLLOW

| 字段    | 类型 | 约束               | 备注 |
| ------- | ---- | ------------------ | ---- |
| UID     | LONG | 非空，外键USER.UID |      |
| UIDFLED | LONG | 非空，外键USER.UID |      |

### FRIENDS

| 字段 | 类型 | 约束               | 备注 |
| ---- | ---- | ------------------ | ---- |
| UID  | LONG | 非空，外键USER.UID |      |
| FUID | LONG | 非空，外键USER.UID |      |

### SUB

| 字段 | 类型 | 约束                | 备注 |
| ---- | ---- | ------------------- | ---- |
| UID  | LONG | 非空，外键USER.UID  |      |
| LID  | LONG | 非空，外键LABEL.LID |      |

### THUME

| 字段 | 类型 | 约束                | 备注 |
| ---- | ---- | ------------------- | ---- |
| UID  | LONG | 非空，外键USER.UID  |      |
| BID  | LONG | 非空，外键MBLOG.BID |      |

### TOPDAY

| 字段   | 类型 | 约束                 | 备注 |
| ------ | ---- | -------------------- | ---- |
| TYEAR  | INT  | 非空                 |      |
| TMONTY | INT  | 非空                 |      |
| TDAY   | INT  | 非空                 |      |
| BID    | INT  | 非空，外键MBLOG.BID  |      |
| TNO    | TNO  | 非空，唯一 >0 && <10 |      |