/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		viewer : 'view/viewer.min',
		WdatePicker : 'My97DatePicker/WdatePicker'
	}
});
require([ 'config', 'jquery', 'storm', 'common', 'viewer', 'WdatePicker' ], function(CONFIG, $, S) {
	'use strict';
	
	var $form = $('[data-form="search"]'),// 查询表单
	$formBtn = $form.find(':submit'), //查询按钮
	 $formReset = $form.find(':reset');  //重置用户按钮
	
	var $addTruth = $('#wp_truthmodal'); // 添加真心话
	var $addTruthBtn = $addTruth.find(':submit'); // 添加真心话按钮
	
	var $editTruth = $('#wp_edittruthmodal');
	var $editTruthBtn = $editTruth.find(':submit');
	
	
	
	var docHeight = document.body.clientHeight;//获取页面可视区域高度
	
	/**
	 * 页面初始化
	 */
	function init() {
		
		$formBtn.click();
		
		$addTruthBtn.click(addTruth);
		$editTruthBtn.click(modifyTruth);
		
		$(document).on('click', '.wp_add', addShowTruth);
		$(document).on('click', '.wp_edit', editTruth);
		$(document).on('click', '.wp_delete', deleteTruth);
		//重置刷新页面
	    $formReset.click(function(){
			setTimeout(function(){
				$formBtn.click();
			},500);
		});
		
	}
	
	function addShowTruth() {
		$('#wp_truth').modal('show');
//		alert($('.fixedHeight', window.parent.document).scrollTop());
		S.autoLayer(docHeight,$('.fixedHeight', window.parent.document).scrollTop());
	}
	
	function addTruth(e) {//添加真心话大冒险
		e.stopPropagation();
		$addTruth.validate(function() {
			$addTruth.setForm(function() {
				alert('添加成功!');
				$('#wp_truth').modal('hide');
				$('.truth_input').val('');
				$formBtn.click();
			});
		});
		return false;
	}
	
	function deleteTruth() {//删除真心话大冒险
		var $this = $(this);
		console.log($this.data());
		$this.postBtn(CONFIG.addr.deleteGuessAdventureById, $this.data(), function(data) {
			console.log(data);
			alert('删除成功');
			$formBtn.click();
		});
	}
	
	function editTruth() {//编辑真心话大冒险
		var $this = $(this);
		console.log($this.data());
		$this.postBtn(CONFIG.addr.findGuessAdventureById, $this.data(), function(data) {
			console.log(data);
			$('.truthtype').val(data.adventureType);
			$('.edit_truthinput').val(data.adventureContent);
			$('.truth_id').val(data.adventureId);
			$('#wp_editruth').modal('show');
			S.autoLayer(docHeight,$('.fixedHeight', window.parent.document).scrollTop());
		});
	}
	
	function modifyTruth(e) {//确定修改真心话大冒险
		
		e.stopPropagation();
		$editTruth.validate(function() {
			$editTruth.setForm(function() {
				alert('修改成功!');
				$formBtn.click();
				$('#wp_editruth').modal('hide');
			});
		});
		return false;
	}
	
	// 页面JS初始化
	init();

});