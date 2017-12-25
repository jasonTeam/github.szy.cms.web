/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths: {
	    config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		ddsort : 'base/ddsort',
		publics: 'base/public',
	},
	shim:{
	    'ddsort':{
	        deps: ['jquery'],
	        exports: 'ddsort'
	    }
	}
});
require(['config', 'jquery', 'storm', 'common', 'base/md5', 'publics', 'ddsort' ], function (CONFIG, $, S) {
  'use strict';


	var $form = $('[data-form="search"]'); // 查询表单
	var $formBtn = $form.find(':submit');
	var $formReset = $form.find(':reset');

	function init () {//页面初始化
		$formBtn.click();
		$(document).on('click', '.wp_task_modify', taskModify);//修改
		$(document).on('click', '.wp_up_low', wpUpLow);//上下架修改 
		
		setTimeout(function(){
			$('#content').DDSort({
				target: 'tr',// 示例而用,默认即 li,
		        delay: 100,// 延时处理,默认为 50ms,防止手抖点击 A链接无效
		        floatStyle: {
		        	'border': '1px solid #ccc',
		            'background-color': '#fff'
		        },
		        changeNum: function(thisId, changeSortNum, changeId, thisSortNum){
		        	var data = {};
					data.assignmentId1 = thisId;
					data.assignmentId2 = changeId;
					
					console.log(data);
					$.post(CONFIG.addr.exchangeAssignmentSort,data,function(result){
						console.log("data----------------------------");
						console.log(result);
					});
		        }
			},function(){
				
	        });
		},2000);
	}
  
	function taskModify() {
	  	var $this = $(this);
		var assignmentId = $this.attr('data-assignmentId');
		$(window.parent.document).find("#mainFrame").attr("src","?assignmentId=" + assignmentId);
		window.location.href = "/taskSetChild";
	}
	
	function wpUpLow() {
		var $this = $(this);
		var data = {};
  		data.assignmentId = $this.attr('data-assignmentId');
		console.log(data);
  	  	$this.postBtn(CONFIG.addr.assignmentEnable, data, function(data) {
  	  		console.log(data);
  	  		S.alert('操作成功');
  	  		$formBtn.click();
  	  	 });
	}
	
  // 页面JS初始化
  init();

});