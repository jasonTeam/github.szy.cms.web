/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : 'base/jquery.min',
		storm : 'base/storm-1.5',
		common : 'base/common',
		upload : 'base/uploadPreview',
		form : 'base/jquery.form',
		ddsort : 'base/ddsort',
		publics: 'base/public',
		WdatePicker : 'My97DatePicker/WdatePicker'
	},
	shim:{
	    'ddsort':{
	        deps: ['jquery'],
	        exports: 'ddsort'
	    }
	}
});
require([ 'config', 'jquery', 'storm', 'common', 'upload', 'form', 'publics', 'WdatePicker', 'ddsort'], function(CONFIG, $, S) {
	'use strict';

	var $tvBanner = $('#J_tvBanner'); // 查询banner表单
	var $tvBannerBtn = $tvBanner.find(':submit'); // 查询banner按钮
	var $tvBannerReset = $tvBanner.find(':reset'); // 重置banner按钮
	var $addsaveBtn = $('.J_addsave'); // 头部banner按钮
	var $showAddBanner = $('#J_showAddBanner'); // 显示banner表单

	var $AddBanner = $('#J_AddBanner'); // 添加banner表单
	var $AddBannerBtn = $AddBanner.find(':submit'); // 添加banner按钮
	
	var $ModifyBanner = $('#J_ModifyBanner'); // 修改banner表单
	var $ModifyBannerBtn = $ModifyBanner.find(':submit'); // 修改banner按钮
	
	var $BannerImg = $('#J_bannerimg'); // 层banner表单
	var $BannerShowimg = $('#banner_showimg'); // 显示banner img
	var $ShareShowimg = $('#share_showimg'); // 显示banner img

	/**
	 * 页面初始化
	 */
	function init() {

		// 表单按钮事件
		$tvBannerBtn.click();
		
		// 显示banner添加modal
		$(document).on('click', '.J_addsave', modalAddBannerShow);

		// banner添加按钮事件
		$AddBannerBtn.click(tvBannerAdd);
		$addsaveBtn.click(cleanBanner);
		
		// banner修改按钮事件
		$ModifyBannerBtn.click(bannerModifyTrue);
		
		// 删除banner
		$(document).on('click', '.wp_deleteBtn', bannerDelete);
		 
		//修改banner弹框
		$(document).on('click', '.wp_modifyBtn', bannerModify);
		
		//上下架banner
		$(document).on('click', '.wp_shelf', shelfBanner);
		
		//重置刷新列表
		$tvBannerReset.click(function(){
			setTimeout(function(){
				$tvBannerBtn.click();
			},500);
		});
		
		$(document).on("mouseover mouseout", '.showload_img', function(event){//banner 查看大图
			if(event.type == "mouseover"){//鼠标悬浮
				$(this).next().show();
			}else if(event.type == "mouseout"){ //鼠标离开
				$(this).next().hide();
			}
		});
		
		setTimeout(function(){
			$('#drag_tr').DDSort({
				target: 'tr',// 示例而用,默认即 li,
		        delay: 100,// 延时处理,默认为 50ms,防止手抖点击 A链接无效
		        floatStyle: {
		        	'border': '1px solid #ccc',
		            'background-color': '#fff'
		        },
		        changeNum: function(thisId, changeSortNum, changeId, thisSortNum){
		        	var data = {};
					data.bannerId1 = thisId;
					data.sortNum1 = changeSortNum;
					data.bannerId2 = changeId;
					data.sortNum2 = thisSortNum;
					
					console.log(data);
					$.post(CONFIG.addr.exchangeTvBanner,data,function(result){
						console.log("data----------------------------");
						console.log(result);
					});
		        }
			},function(){
				
	        });
		},3000);
		
		
	}
	
	 function shelfBanner(){//上下架banner
		  var $this = $(this);
		  console.log($this.data());
		  
		  $this.postBtn(CONFIG.addr.upOrDownTvBanner, $this.data(), function(data) {
	    	  console.log(data);
	    	  if (data.bannerStatus == 1) {//被上架
	    		  S.alert('上架banner成功');
	    	  } else if (data.bannerStatus == 2) {//被下架
	    		  S.alert('下架banner成功');
	    	  }
	    	  $tvBannerBtn.click();
	      });
	 }
	
	function cleanBanner(){//清空banner表单
		$showAddBanner.find('[data-insert]').val('');
		$BannerShowimg.attr('src','http://opq77mx3q.bkt.clouddn.com/170510-640-150.png');
	}
	
	 new uploadPreview({//本地预览上传banner图片
		 UpBtn : "banner_upimg",
		 DivShow : "banner_divimg",
		 ImgShow : "banner_showimg",
		 callback: function () {
			 setTimeout(function(){
				var data = {};
				$("#J_bannerimg").ajaxSubmit({
					type: "post", // 提交方式 get/post
					url: CONFIG.addr.fileupload, // 需要提交的 url
					data: data,
					success: function(data) {
						console.log(data);
						if(data.code = '000'){
							var r = data.data.downurl;
							r = r.substring(0,r.indexOf("?"));
							$("input[name=bannerPicUrl]").val(r);
						}
					},
				});
			 },500);
		 }
	 });

	 new uploadPreview({// 本地预览上传banner图片
		 UpBtn : "share_upimg",
		 DivShow : "share_divimg",
		 ImgShow : "share_showimg",
		 callback: function () {
			 setTimeout(function(){
				var data = {};
				$("#wp_shareimg").ajaxSubmit({
					type: "post", // 提交方式 get/post
					url: CONFIG.addr.fileupload, // 需要提交的 url
					data: data,
					success: function(data) {
						console.log(data);
						if(data.code = '000'){
							var r = data.data.downurl;
							r = r.substring(0,r.indexOf("?"));
							$("input[name=sharePicUrl]").val(r);
						}
					},
				});
			 },500);
		 }
	 });
	
	$('.add_isShare').change(function() {
		var isShare = $(this).val();
		if (isShare == "1") {//分享
			$('#wp_shareimg').show();
			$('.add_share_logo').show();
			
			$('input[name=sharePicUrl]').attr('required','required');
			$('input[name=shareTitle]').attr('required','required');
			$('input[name=backUrl]').attr('required','required');
			$('textarea[name=shareRemark]').attr('required','required');
			
        } else if (isShare == "2") {//不分享 
        	$('#wp_shareimg').hide();
			$('.add_share_logo').hide();
			
			$('input[name=sharePicUrl]').removeAttr('required');
			$('input[name=shareTitle]').removeAttr('required');
			$('input[name=backUrl]').attr('required','required');
			$('textarea[name=shareRemark]').removeAttr('required');
        }  
	});
	
	/**
	 * banner添加
	 * 
	 * @param e
	 * @returns {boolean}
	 */
	function tvBannerAdd(e) {
		e.stopPropagation();
		$AddBanner.validate(function() {
			$AddBanner.setForm(function() {
				S.alert('添加banner成功!');
				$showAddBanner.modal('hide');
				$tvBannerBtn.click();
			});
		});
		return false;
	}
	
	/**
	 * banner修改数据回填
	 */
	function bannerModify() {
		$showAddBanner.modal('show');
		
		$ModifyBanner.modal('show')
		$AddBanner.modal('hide');
		
		var $this = $(this);
		$this.postBtn(CONFIG.addr.findTvBannerById, $this.data(), function(data) {
			
			var appSit = data.appSit;
			var bannerStatus = data.bannerStatus;
			
			var bannerPicUrl = data.bannerPicUrl;
			var bannerId = data.bannerId;
			
			var sharePicUrl = data.sharePicUrl;
			var isShare = data.isShare;
			
			if(isEmpty(sharePicUrl)) {
				$ShareShowimg.attr('src','http://opq77mx3q.bkt.clouddn.com/20170710-share_logo.png');
			} else {
				$ShareShowimg.attr('src',sharePicUrl);
			}
			
			$BannerShowimg.attr('src',bannerPicUrl);
			
			$('input[name=bannerId]').val(bannerId);
			$ModifyBanner.find('[data-insert]').html('');
			$ModifyBanner.insert(data);
			
			$('.appsit').val(appSit);
			$('.bannerStatus').val(bannerStatus);
			$('.modify_isShare').val(isShare);
			
			if (isShare == "1") {//分享
				$('#wp_shareimg').show();
				$('.add_share_logo').show();
				
				$('input[name=sharePicUrl]').attr('required','required');
				$('input[name=shareTitle]').attr('required','required');
				$('textarea[name=shareRemark]').attr('required','required');
				
	        } else if (isShare == "2") {//不分享  
	        	$('#wp_shareimg').hide();
				$('.add_share_logo').hide();
				
				$('input[name=sharePicUrl]').removeAttr('required');
				$('input[name=shareTitle]').removeAttr('required');
				$('textarea[name=shareRemark]').removeAttr('required');
	        } 
			
		});
	}
	
	/**
	 * banner修改数据
	 */
	function bannerModifyTrue(e) {
		e.stopPropagation();
		$ModifyBanner.validate(function() {
			$ModifyBanner.setForm(function() {
				S.alert('修改banner成功!');
				$showAddBanner.modal('hide');
				$tvBannerBtn.click();
			});
		});
		return false;
	}

	/**
	 * banner删除
	 */
	function bannerDelete() {
		var $this = $(this);
		S.confirm('你确定要删除该banner?', function() {
			$this.postBtn(CONFIG.addr.deleteTvBanner, $this.data(), function() {
				S.alert('删除成功!');
//				$form.trigger('Storm.search.get');
				$tvBannerBtn.click();
			})
		});
	}

	
	/**
	 * 模态框显示
	 */
	function modalAddBannerShow() {
		$showAddBanner.modal('show');
		$ModifyBanner.modal('hide')
		$AddBanner.modal('show');
	}
	
	var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
	
//	$("#banner_upimg").change(function() {//banner图片上传
//		var data = {};
//		$("#J_bannerimg").ajaxSubmit({
//			type: "post", // 提交方式 get/post
//			url: CONFIG.addr.fileupload, // 需要提交的 url
//			data: data,
//			success: function(data) {
//				console.log(data);
//				if(data.code = '000'){
//					var r = data.data.downurl;
//					r = r.substring(0,r.indexOf("?"));
//					$("input[name=bannerPicUrl]").val(r);
//				}
//			},
//			complete: function() {
//				
//			},
//			error: function() {
//				
//			}
//		});
//	});
	

	// 页面JS初始化
	init();

});