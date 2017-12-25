/**
 * Created by Storm on 2016/4/12.
 */
require.config({
  paths: {
	    config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common'
  }
});
require(['config', 'jquery', 'storm', 'common'], function (CONFIG, $, S) {
  'use strict';
  
  var $form = $('[data-form="search"]'),// 查询IOS支付配置表单
  $formBtn = $form.find(':submit');  //查询IOS支付配置按钮
  /**
   * 页面初始化
   */
  function init () {
	  //查询系统配置
	  $formBtn.click();
	  //修改IOS支付配置
	  $(document).on('click', '.J_editBtn', editSys);
  }
  
  function editSys(e) {
	  var $this = $(this);
	  var sysId = $this.attr("data-id");
	  var sysCode = $("#code_" + sysId).val();
	  var sysKey = $("#key_" + sysId).val();
	  var sysVal = $("#val_" + sysId).val();
	  $this.postBtn(CONFIG.addr.updateIosPay, {
			"sysId" : sysId,
			"sysCode" : sysCode,
			"sysKey" : sysKey,
			"sysVal" : sysVal
		}, function(req) {
			if (req) {
				S.alert('修改IOS支付成功!', function() {
					$formBtn.click();
				});
			}
	  });
  }
  
  // 页面JS初始化
  init();

});