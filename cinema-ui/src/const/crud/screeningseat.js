export const tableOption = {
  "align": "center",
  "index": true,
  "border": true,
  "column": [{
    "prop": "id",
    "type": "input",
    "label": "主键",
    "display": false,
    "addDisplay": false,
    "editDisabled": false,
    "width": 200,
  }, {
    "prop": "screeningRoomId",
    "type": "input",
    "label": "影厅id",
    "rules": [{"message": "影厅id必须填写", "required": true}],
    "display": true,
    "required": true,
    "addDisplay": false,
    "editDisabled": false,
    "width": 200
  }, {
    "prop": "screeningId",
    "type": "input",
    "label": "影厅id",
    "display": false,
    "required": false,
    "addDisplay": false,
    "editDisabled": false,
    "width": 200
  }, {
    "prop": "siteX",
    "type": "input",
    "label": "横坐标",
    "rules": [{"message": "横坐标必须填写", "required": true}],
    "display": true,
    "required": true,
    "addDisplay": false,
    "editDisabled": false
  }, {
    "prop": "siteY",
    "type": "input",
    "label": "纵坐标",
    "rules": [{"message": "纵坐标必须填写", "required": true}],
    "display": true,
    "required": true,
    "addDisplay": false,
    "editDisabled": false
  }, {
    "prop": "status",
    "type": "select",
    "label": "状态：-1:不可用/0:可购买/1:已预定/2:已付款",
    "props": {"label": "", "value": ""},
    "rules": [{"message": "状态：-1:不可用/0:可购买/1:已预定/2:已付款必须填写", "required": true}],
    "detail": false,
    "dicData": [{"desc": "可购买", "label": "0", "value": "0"}, {
      "desc": "已预订",
      "label": "1",
      "value": "1"
    }, {"desc": "已付款", "label": "2", "value": "2"}, {"desc": "不可用", "label": "-1", "value": "-1"}],
    "display": true,
    "dataType": "number",
    "required": true,
    "addDisplay": false,
    "cascaderItem": [],
    "editDisabled": false
  }, {
    "prop": "createBy",
    "type": "input",
    "label": "创建人",
    "display": false,
    "addDisplay": false,
    "editDisabled": false
  }, {
    "prop": "createTime",
    "type": "input",
    "label": "创建时间",
    "display": false,
    "addDisplay": false,
    "editDisabled": false
  }, {
    "prop": "updateBy",
    "type": "input",
    "label": "更新人",
    "display": false,
    "addDisplay": false,
    "editDisabled": false
  }, {
    "prop": "updateTime",
    "type": "input",
    "label": "修改时间",
    "display": false,
    "addDisplay": false,
    "editDisabled": false
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
