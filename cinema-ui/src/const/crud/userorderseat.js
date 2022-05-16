export const tableOption ={"align": "center", "index": true, "border": true, "column": [{"prop": "id", "type": "input", "label": "主键", "rules": [], "display": false, "required": false, "addDisplay": false, "editDisabled": true}, {"prop": "userOrderId", "type": "input", "label": "用户订单id", "rules": [{"message": "用户订单id必须填写", "required": true}], "display": true, "required": true, "addDisplay": false, "editDisabled": true}, {"prop": "screeningSeatId", "type": "input", "label": "座位id", "rules": [{"message": "座位id必须填写", "required": true}], "display": true, "required": true, "addDisplay": false, "editDisabled": true}, {"prop": "createBy", "type": "input", "label": "创建人", "rules": [], "display": false, "required": false, "addDisplay": false, "editDisabled": true}, {"prop": "createTime", "type": "input", "label": "创建时间", "rules": [], "display": false, "required": false, "addDisplay": false, "editDisabled": true}, {"prop": "updateBy", "type": "input", "label": "更新人", "rules": [], "display": false, "required": false, "addDisplay": false, "editDisabled": true}, {"prop": "updateTime", "type": "input", "label": "修改时间", "rules": [], "display": false, "required": false, "addDisplay": false, "editDisabled": true}], "gutter": 0, "stripe": true, "menuBtn": true, "emptyBtn": true, "emptyText": "清空", "menuAlign": "center", "submitBtn": true, "indexLabel": "序号", "labelWidth": 120, "submitText": "提交", "labelSuffix": "：", "menuPosition": "center", "labelPosition": "left", "searchMenuSpan": 6}