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
		upload : 'base/uploadPreview',
		form : 'base/jquery.form',
	},
	shim:{
	    'ddsort':{
	        deps: ['jquery'],
	        exports: 'ddsort'
	    }
	}
});
require(['config', 'jquery', 'storm', 'common', 'base/md5', 'publics', 'ddsort', 'upload', 'form' ], function (CONFIG, $, S) {
  'use strict';

	function init () {//页面初始化
		
		$(document).on('click', '.wp_gift_modify', giftModify);//修改
		$(document).on('click', '.wp_gift_save', giftSave);//修改
		$(document).on('click', '.wp_gift_cancle', giftCancle);//修改
		$(document).on('click', '.wp_up_low', wpUpLow);//上下架修改 
		$(document).on('click', '.gift_upimg', selectImg);//选择图片
		$(document).on('click', '.add_gift_btn', showGift);//新增礼物弹框
		$(document).on('click', '.gift_logo', selectImg);//选择图片
		$(document).on('click', '.wp_delete', giftDelete);//删除礼物
		$('.add_gift_submit').on('click', addGift);
		
		getGiftUpper();
		getGiftLower();
	}
	
	function getGiftUpper() {//上架礼物列表
		var $this = $(this);
		var data = {};
  		data.isEnable = 1;
		console.log(data);
  	  	$this.postBtn(CONFIG.addr.getGiftConfigList, data, function(data) {
  	  		console.log(data);
  	  		$('#content_upper').html(S.template(data, $('#temp_upper').html()));
  	  		
  	  		setTimeout(function(){
				$('#content_upper').DDSort({
					target: 'tr',// 示例而用,默认即 li,
			        delay: 100,// 延时处理,默认为 50ms,防止手抖点击 A链接无效
			        floatStyle: {
			        	'border': '1px solid #ccc',
			            'background-color': '#fff'
			        },
			        changeNum: function(thisId, changeSortNum, changeId, thisSortNum){
			        	var data = {};
						data.giftId1 = thisId;
						data.sort1 = changeSortNum;
						data.giftId2 = changeId;
						data.sort2 = thisSortNum;
						
						console.log(data);
						$.post(CONFIG.addr.exchangeGiftConfigInfo,data,function(result){
							console.log("data----------------------------");
							console.log(result);
						});
			        }
				},function(){
					
		        });
			},1500);
  	  	});
	}
	
	function getGiftLower() {//下架礼物列表
		var $this = $(this);
		var data = {};
		data.isEnable = 2;
		console.log(data);
  	  	$this.postBtn(CONFIG.addr.getGiftConfigList, data, function(data) {
  	  		console.log(data);
  	  		$('#content_lower').html(S.template(data, $('#temp_lower').html()));
  	  	 });
	}
	
	function wpUpLow() {//上下架礼物
		var $this = $(this);
		console.log($this.data());
  	  	$this.postBtn(CONFIG.addr.modifyIsEnable, $this.data(), function(data) {
  	  		console.log(data);
  	  		S.alert('操作成功！');
  	  		getGiftUpper();
  	  		getGiftLower();
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
							if ($("#photo_id").val() == '0') {
								$('.gift_logo').attr('src',r);
								$('.gift_logo').next().val(r);
							} else {
								$(".gift_" + $("#photo_id").val()).attr('src', r);
								$("#gift_img_" + $("#photo_id").val()).val(r);
							}
						} else {
							S.alert('机器人头像上传失败!');
						}
					},
				});
			 },500);
		 }
	});

  	function selectImg() {
  		var $this = $(this);
  		$("#photo_id").val($this.attr("data-id"));
  		$("#photo_upimg").click();
  	}
  	
  	function giftModify() {
  		var $this = $(this);
  		$this.addClass('hide');
	  	$this.next().removeClass('hide');
	  	$this.next().next().removeClass('hide');
	  	$this.parent().parent().find('.gift_val').addClass('hide');
	  	$this.parent().parent().find('.gift_edit').removeClass('hide');
	}
  	
  	function giftCancle() {
	  	var $this = $(this);
		$this.addClass('hide');
	  	$this.prev().addClass('hide');
	  	$this.prev().prev().removeClass('hide');
	  	$this.parent().parent().find('.gift_val').removeClass('hide');
	  	$this.parent().parent().find('.gift_edit').addClass('hide');
	}
  	
  	function giftSave() {
  		var $this = $(this);
  		
  		var giftPicThis = $("#gift_img_" + $this.attr('data-id')).val();
  		var giftNameThis = $("#gift_name_" + $this.attr('data-id')).val();
  		var giftTypeThis = $("#gift_type_" + $this.attr('data-id')).val();
  		var beanThis = $("#gift_bean_" + $this.attr('data-id')).val();
  		
  		if (S.isEmpty(giftNameThis)) {
  			S.alert('请填写礼物名称');
  			return;
  		} else if (S.isEmpty(beanThis)) {
  			S.alert('请填写礼物价格');
  			return;
  		} else {
	  		var giftData = {};
		  	giftData.id = $this.attr('data-id');
	  		giftData.giftPic = giftPicThis;
	  		giftData.giftName = giftNameThis;
	  		giftData.giftType = giftTypeThis;
	  		giftData.bean = beanThis;
	  		var data = {};
	  		data.giftConfigInfo = JSON.stringify(giftData);
	  		console.log(data);
	  		$this.postBtn(CONFIG.addr.saveGiftConfig, data, function(data) {
	  			console.log(data);
	  			S.alert('保存成功');
	  			getGiftLower();
	  		});
  		}
	}
  	
  	function showGift() {//显示礼物弹框
		$('#wp_gift_modal').modal('show');
	}
  	
  	function addGift(e){
		  e.preventDefault();
		  $('#wp_fromgift').validate(function () {
			  $('#wp_fromgift').setForm(function () {
	              S.alert('添加新礼物成功!');
	              $('#wp_gift_modal').trigger('Storm.modal.hide');
	              getGiftLower();
			  });
		  });
		  return false;
	  }
	
  	function giftDelete() {//删除礼物
  		var $this = $(this);
  		S.confirm('你确定要删除该礼物?', function () {
	  	  	$this.postBtn(CONFIG.addr.deleteGiftConfigInfo, $this.data(), function(data) {
	  	  		console.log(data);
	  	  		S.alert('删除礼物成功!');
	  	  		getGiftLower();
	  	  	});
  		});
	}
  	
  	
  // 页面JS初始化
  init();

});