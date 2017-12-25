/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths: {
	    config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		jqueryztree: 'zTree/jquery.ztree-2.6.min',
		upload : 'base/uploadPreview',
		form : 'base/jquery.form'
	},
	shim: {
		md5: {
			exports: 'md5'
		},
		'jqueryztree':{
			deps: ['jquery'],
			exports: 'jqueryztree'
		}
	}
});
require(['config', 'jquery', 'storm', 'common', 'base/md5', 'jqueryztree', 'upload', 'form'], function (CONFIG, $, S) {
  'use strict';


	var $form = $('[data-form="search"]'); // 查询表单
	var $formBtn = $form.find(':submit');
	var $formReset = $form.find(':reset');
	
	var stageNum = 0;
	
	
	function init () {//页面初始化
		taskInfo();
		$('.assignment_id').val(S.getQueryString('assignmentId'));
		$formBtn.click();
		$(document).on('click', '.task_sure_btn', updateInfo);//修改
		$(document).on('click', '.task_cancle_btn', taskBack);//取消修改
		$(document).on('click', '.task_logo', selectImg);//取消修改
		
		$(document).on('click', '.wp_job_modify', taskEdit);//修改
		$(document).on('click', '.wp_job_save', taskUpdate);//取消修改
		$(document).on('click', '.wp_job_cancle', taskCancle);//取消修改
		
		$(document).on('click', '.add_stage_btn', addStage);//添加新阶段html
		
		$(document).on('click', '.wp_add_save', saveStage);//添加新阶段
		$(document).on('click', '.wp_job_delete', deleteStage);//取消任务阶段
		$(document).on('click', '.wp_add_cancle', cancleStage);//取消任务阶段
		
		
	}
  
	
  function taskInfo() {//查询任务配置信息
	  var $this = $(this);
	  var data = {};
	  data.assignmentId = S.getQueryString('assignmentId');
	  console.log(data);
	  $this.postBtn(CONFIG.addr.findActAssignmentMsg, data, function(data) {
		  	console.log(data);
		  	$('.task_logo').attr('src', data.assignmentHeadUrl);
		  	$('.task_logo_val').val(data.assignmentHeadUrl);
		  	$('.task_name').val(data.assignmentName);
		  	$('.task_type').val(data.isActivity);
	  });
  }
  
  function updateInfo() {//查询任务配置信息
	  var $this = $(this);
	  var taskData = {};
	  taskData.assignmentId = S.getQueryString('assignmentId');
	  taskData.assignmentHeadUrl = $('.task_logo_val').val();
	  taskData.assignmentName = $('.task_name').val();
	  taskData.isActivity = $('.task_type').val();
	  
	  var data = {};
	  data.actAssignment = JSON.stringify(taskData);
	  
	  console.log(data);
	  $this.postBtn(CONFIG.addr.updateActAssignmenta, data, function(data) {
		  	console.log(data);
		  	S.alert('修改成功！');
		  	taskInfo();
	  });
  }
  
  new uploadPreview({//本地预览上传banner图片
		 UpBtn : "photo_upimg",
		 DivShow : "photo_divimg",
		 ImgShow : "photo_showimg",
		 callback: function () {
			 setTimeout(function(){
				var data = {};
				var $this = $(this);
				$("#J_photoImg").ajaxSubmit({
					type: "post", // 提交方式 get/post
					url: CONFIG.addr.fileupload, // 需要提交的 url
					data: data,
					success: function(data) {
						console.log(data);
						if(data.code = '000'){
							var r = data.data.downurl;
							r = r.substring(0,r.indexOf("?"));
							$(".task_logo").attr('src', r);
							$(".task_logo_val").val(r);
						} else {
							S.alert('任务配置Logo上传失败!');
						}
					},
				});
			 },500);
		 }
	});
  
  	function selectImg() {
  		var $this = $(this);
  		$("#photo_upimg").click();
  	}
  	
  	function taskBack() {//取消返回上一页
		window.location.href = '/taskSet';
	}
  	
  	function taskEdit() {
  		var $this = $(this);
  		$this.parent().parent().find('.task_val').addClass('hide'); 
  		$this.parent().parent().find('.task_edit').removeClass('hide');
  		
  		$this.addClass('hide');
  		$this.next().removeClass('hide');
  		$this.next().next().removeClass('hide');
	}
  	
  	function taskCancle() {
  		var $this = $(this);
//  		$this.parent().parent().find('.task_val').removeClass('hide'); 
//  		$this.parent().parent().find('.task_edit').addClass('hide');
//  		$this.addClass('hide');
//  		$this.prev().addClass('hide');
//  		$this.prev().prev().removeClass('hide');
  		stageNum = 0;
  		$formBtn.click();
	}
  	
  	function taskUpdate() {//查询任务配置信息
  	  var $this = $(this);
  	  var taskData = {};
  	  taskData.id = $this.attr('data-id');
  	  taskData.executeTimes = $('.task_one_' + $this.attr('data-id')).val();
  	  taskData.assignmentReward = $('.task_two_' + $this.attr('data-id')).val();
  	  taskData.remark = $('.task_three_' + $this.attr('data-id')).val();
  	  
  	  var data = {};
  	  data.actAssignment = JSON.stringify(taskData);
  	  
  	  console.log(data);
  	  $this.postBtn(CONFIG.addr.updateActAssignmentDetail, data, function(data) {
  		  	console.log(data);
  		  	S.alert('修改阶段信息成功！');
  		  	stageNum = 0;
  		  	$formBtn.click();
  	  });
    }
  	
  	function addStage() {//添加新阶段
  		var $this = $(this);
  		
//  		alert($('#content tr').length)
  		var numStage = $('#content tr').length;
  		
  		if (numStage == '3') {
  			S.alert('阶段不能超过3个');
  		} else {
  			var numMoment = 0;
//  			if (numStage == 1) {
  				numMoment = Number(numStage) + 1;
//  			}
		
			if (stageNum != 1) {
  				
		  		$('#content').append('<tr>' +
					'<td>' + numMoment + '</td>' +
					'<td>' +
						'<input type="text" class="input task_one_executeTimes" value="" placeholder="请输入任务" />' +
					'</td>' +
					'<td>' +
						'<input type="text" class="input task_two_assignmentReward" placeholder="请输入奖励" />' +
					'</td>' +
					'<td>' +
						'<input type="text" class="input task_three_remark width400" value="" placeholder="请输入任务描述" />' +
					'</td>' +
					'<td>' +
						'<button type="button" class="btn wp_add_save">保存</button>' +
						' <button type="button" class="btn wp_add_cancle">取消</button>' +
					'</td>' +
					'</tr>');
			} else {
				S.alert('请先保存添加的新阶段！');
			}
			
	  		stageNum = 1;
	  		
  		}
  		
	}
  	
  	function saveStage() {//添加新阶段
  		 var $this = $(this);
  	  	  
  	  	 if ($('.task_one_executeTimes').val() == '') {
  	  		 S.alert('任务不能为空');
  	  		 return;
  	  	 } else  if ($('.task_two_assignmentReward').val() == '') {
  	  		 S.alert('奖励不能为空');
  	  		return;
  	  	 } else  if ($('.task_three_remark').val() == '') {
  	  		 S.alert('任务描述不能为空');
  	  		return;
  	  	 } else {
  	  		 var data = {};
  	  		 data.assignmentId = S.getQueryString('assignmentId');
  	  	  	 data.executeTimes = $('.task_one_executeTimes').val();
  	  	  	 data.assignmentReward = $('.task_two_assignmentReward').val();
  	  	  	 data.remark = $('.task_three_remark').val();
  	  	  			
  	  	  	 console.log(data);
  	  	  	 $this.postBtn(CONFIG.addr.addNewAssignmentLevel, data, function(data) {
  	  	  		 console.log(data);
  	  	  		 S.alert('添加新阶段信息成功！');
  	  	  		 stageNum = 0;
  	  	  		 $formBtn.click();
  	  	  	  });
  	  	 }
	}
  	
  	function cancleStage() {//删除新阶段
  		var $this = $(this);
  		$this.parent().parent().remove();
  		stageNum = 0;
	}
  	
	function deleteStage() {//删除任务阶段
  		var $this = $(this);
  		
  		var data = {};
  		data.id = $this.attr('data-id');
  	  			
  	  	console.log(data);
  	  	$this.postBtn(CONFIG.addr.deleteActAssignmentDetail, data, function(data) {
  	  		console.log(data);
  	  		S.alert('删除任务阶段成功！');
  	  		stageNum = 0;
  	  		$formBtn.click();
  	  	 });
	}
  	
//  function modalRightsShow () {
//    $adminRights.find(':reset').trigger('click');
//    $adminRights.modal('show');
//  }
  
  // 页面JS初始化
  init();

});