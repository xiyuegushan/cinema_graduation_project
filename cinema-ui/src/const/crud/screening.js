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
    "prop": "movieId",
    "type": "input",
    "label": "电影id",
    "rules": [{"message": "电影id必须填写", "required": true}],
    "display": true,
    "required": true,
    "addDisplay": false,
    "editDisabled": false,
    "width": 200
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
    "prop": "type",
    "type": "select",
    "label": "放映方式: 0:2D/1:3D/2:IMAX",
    "props": {"label": "label", "value": "value"},
    "rules": [{"message": "放映方式: 0:2D/1:3D/2:IMAX必须填写", "required": true}],
    "dicData": [{"desc": "2D", "label": "0", "value": "0"}, {"desc": "3D", "label": "1", "value": "1"}, {
      "desc": "IMAX",
      "label": "2",
      "value": "2"
    }],
    "display": true,
    "required": true,
    "addDisplay": false,
    "cascaderItem": [],
    "editDisabled": false
  }, {
    "prop": "price",
    "type": "input",
    "label": "影票价格",
    "rules": [{"message": "影票价格必须填写", "required": true}],
    "display": true,
    "required": true,
    "addDisplay": false,
    "editDisabled": false
  }, {
    "prop": "screeningStartTime",
    "type": "datetime",
    "label": "放映开始时间",
    "rules": [{"message": "放映开始时间必须填写", "required": true}],
    "format": "yyyy-MM-dd HH:mm:ss",
    "display": true,
    "required": true,
    "addDisplay": false,
    "valueFormat": "yyyy-MM-dd HH:mm:ss",
    "editDisabled": false
  },
    {
      "prop": "screeningEndTime",
      "type": "datetime",
      "label": "放映结束时间",
      "rules": [{"message": "放映结束时间必须填写", "required": true}],
      "format": "yyyy-MM-dd HH:mm:ss",
      "display": true,
      "required": true,
      "addDisplay": false,
      "valueFormat": "yyyy-MM-dd HH:mm:ss",
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
