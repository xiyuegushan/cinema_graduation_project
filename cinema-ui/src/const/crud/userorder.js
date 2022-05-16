export const tableOption = {
  "align": "center",
  "index": true,
  "border": true,
  "column": [{
    "prop": "id",
    "type": "input",
    "label": "主键",
    "rules": [],
    "display": false,
    "required": false,
    "addDisplay": false,
    "editDisabled": true,
    "width": 200,
  }, {
    "prop": "screeningId",
    "type": "input",
    "label": "排片id",
    "rules": [{"message": "排片id必须填写", "required": true}],
    "display": true,
    "required": true,
    "addDisplay": false,
    "editDisabled": true,
    "width": 200
  }, {
    "prop": "userId",
    "type": "input",
    "label": "用户id",
    "rules": [{"message": "用户id必须填写", "required": true}],
    "display": true,
    "required": true,
    "addDisplay": false,
    "editDisabled": true,
    "width": 200
  }, {
    "prop": "status",
    "type": "select",
    "label": "状态：-1:订单已取消/0:订单已创建/1:订单已付款",
    "props": {"label": "label", "value": "value"},
    "rules": [{"message": "请选择状态：-1:订单已取消/0:订单已创建/1:订单已付款", "required": true}],
    "dicData": [{"desc": "已取消", "label": "-1", "value": "-1"}, {
      "desc": "已创建",
      "label": "0",
      "value": "0"
    }, {"desc": "已付款", "label": "1", "value": "1"}],
    "display": true,
    "dataType": "number",
    "required": true,
    "addDisplay": false,
    "cascaderItem": [],
    "editDisabled": true
  }, {
    "prop": "createBy",
    "type": "input",
    "label": "创建人",
    "display": false,
    "addDisplay": false,
    "editDisabled": true
  }, {
    "prop": "createTime",
    "type": "input",
    "label": "创建时间",
    "display": false,
    "addDisplay": false,
    "editDisabled": true
  }, {
    "prop": "updateBy",
    "type": "input",
    "label": "更新人",
    "display": false,
    "addDisplay": false,
    "editDisabled": true
  }, {
    "prop": "updateTime",
    "type": "input",
    "label": "修改时间",
    "display": false,
    "addDisplay": false,
    "editDisabled": true
  }],
  "gutter": 0,
  "stripe": true,
  "menuBtn": true,
  "emptyBtn": true,
  "emptyText": "清空",
  "menuAlign": "center",
  "submitBtn": true,
  "indexLabel": "序号",
  "labelWidth": 120,
  "submitText": "提交",
  "labelSuffix": "：",
  "menuPosition": "center",
  "labelPosition": "left",
  "searchMenuSpan": 6
}
