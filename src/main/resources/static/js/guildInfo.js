/**
 * Created by Storm on 2016/4/12.
 */
require.config({
	paths : {
		config : 'base/config',
		jquery : [ 'base/jquery.min', 'jquery.min' ],
		storm : 'base/storm-1.5',
		common : 'base/common',
		upload : 'base/uploadPreview',
		form : 'base/jquery.form',
	}
});
require([ 'config', 'jquery', 'storm', 'common', 'upload', 'form' ], function(CONFIG, $, S) {
	'use strict';

	var $form = $('[data-form="search"]'), // 查询用户表单
	$formBtn = $form.find(':submit'), // 查询用户按钮
	$formReset = $form.find(':reset'), // 重置用户按钮
	
	$wpCheckFrom = $("#wp_check_from"),//回填审核modal
	$wpCheckBtn = $wpCheckFrom.find(':submit'), //回填审核btn
	
	$wpModifyFrom = $("#wp_modify_from"),//回填修改modal
	$wpModifyBtn = $wpModifyFrom.find(':submit'); //回填修改btn
	
	var docHeight = document.body.clientHeight;//获取页面可视区域高度
	var imgNum = 1;
	
	/**
	 * 页面初始化
	 */
	function init() {
		// 查询
//		$formBtn.click();
		
		$(document).on('click', '.wp_examine', checkMoadl);//审核按钮
		
		$(document).on('click', '.wp_see', modifyMoadl);//查看修改按钮
		
		$('.audit_btn').on('click', auditBtn);//通过，不通过
		
		$('.code_img').on('click', uploadImg);//上传图片
		
		$wpModifyBtn.click(modifyTrue);//修改
		
		//重置刷新页面
	    $formReset.click(function(){
			setTimeout(function(){
				$formBtn.click();
			},500);
		});
	    
	    $(document).on("mouseover mouseout", '.code_just', function(event){//banner 查看大图
			if(event.type == "mouseover"){//鼠标悬浮
				$(this).next().removeClass('hide');
			}else if(event.type == "mouseout"){ //鼠标离开
				$(this).next().addClass('hide');
			}
		});
	    $(document).on("mouseover mouseout", '.code_back', function(event){//banner 查看大图
			if(event.type == "mouseover"){//鼠标悬浮
				$(this).next().removeClass('hide');
			}else if(event.type == "mouseout"){ //鼠标离开
				$(this).next().addClass('hide');
			}
		});
	    $(document).on("mouseover mouseout", '.reg_num', function(event){//banner 查看大图
			if(event.type == "mouseover"){//鼠标悬浮
				$(this).next().removeClass('hide');
			}else if(event.type == "mouseout"){ //鼠标离开
				$(this).next().addClass('hide');
			}
		});
	}
	
	function checkMoadl() {//显示审核弹框
		var $this = $(this);
		console.log($this.data())
		$this.postBtn(CONFIG.addr.getBrokerConfraternityAndAccount, $this.data(), function (data) {
			$('#wp_modal_check').modal('show');
			
			if (data.confraternityType == 2) {
				$('.reg_num').attr('src',data.businessLicenceUrl);
				$('.company_names').html('企业名称：');
				$('.company_names').next().attr('data-insert','companyName');
				$('.company_nums').html('营业执照注册号：');
				$('.company_nums').next().attr('data-insert','businessLicenceNum');
				$('.company_codes').html('营业执照扫描件：');
			} else if (data.confraternityType == 3) {
				$('.reg_num').attr('src',data.organizationCertificateUrl);
				$('.company_names').html('组织名称：');
				$('.company_names').next().attr('data-insert','organizationName');
				$('.company_nums').html('组织机构代码：');
				$('.company_nums').next().attr('data-insert','organizationCode');
				$('.company_codes').html('组织机构代码证扫描件：');
			}
			
			$wpCheckFrom.find('[data-insert]').html('');
			$wpCheckFrom.insert(data);
			if (data.confraternityType == 1) {//个人
				$('.dis_enterprise').hide();
				$('.alipay_name').show();
				$('.bank_name').hide();
				$('.accout_num').html('支付宝账号：');
			} else {//2企业3个人
				$('.dis_enterprise').show();
				$('.bank_name').show();
				$('.alipay_name').hide();
				$('.accout_num').html('银行卡号：');
				if (data.confraternityType == 2) {
					$('.reg_num').attr('src',data.businessLicenceUrl);
					$('.big_reg_num').attr('src',data.businessLicenceUrl);
				} else if (data.confraternityType == 3) {
					$('.reg_num').attr('src',data.organizationCertificateUrl);
					$('.big_reg_num').attr('src',data.organizationCertificateUrl);
				}
			}
			$('.code_just').attr('src',data.frontIdentityUrl);
			$('.big_just').attr('src',data.frontIdentityUrl);
			
			$('.code_back').attr('src',data.oppositeIdentityUrl);
			$('.big_back').attr('src',data.oppositeIdentityUrl);
		});
	}
	
	function modifyMoadl() {//显示查看弹框
		
		var $this = $(this);
		console.log($this.data());
		$this.postBtn(CONFIG.addr.getBrokerConfraternityAndAccount, $this.data(), function (data) {
			$('#wp_modal_modify').modal('show');
			
			if (data.confraternityType == 2) {
				$('.scanning').attr({
					'name' : 'businessLicenceUrl',
					'data-insert' : 'businessLicenceUrl',
				});
				$('.company_names').html('企业名称：');
				$('.company_names').next().attr('data-insert','companyName');
				$('.company_nums').html('营业执照注册号：');
				$('.company_nums').next().attr('data-insert','businessLicenceNum');
				$('.company_codes').html('营业执照扫描件：');
			} else if (data.confraternityType == 3) {
				$('.scanning').attr({
					'name' : 'organizationCertificateUrl',
					'data-insert' : 'organizationCertificateUrl',
				});
				$('.company_names').html('组织名称：');
				$('.company_names').next().attr('data-insert','organizationName');
				$('.company_nums').html('组织机构代码：');
				$('.company_nums').next().attr('data-insert','organizationCode');
				$('.company_codes').html('组织机构代码证扫描件：');
			}
			
			$('#wp_modify_from').find('[data-insert]').html('');
			$('#wp_modify_from').insert(data);
			
			$('input[name=confraternityId]').val(data.confraternityId);
			
			if (data.confraternityType == 1) {//个人
				$('.dis_prise').hide();
				$('.accout_num').html('支付宝账号：');
				$('.alipay_input_name').show();
				$('.bank_input_name').hide();
			} else {//2企业3个人
				$('.dis_prise').show();
				$('.accout_num').html('银行卡号：');
				$('.alipay_input_name').hide();
				$('.bank_input_name').show();
				$('.con_type').val(data.confraternityType);
				if (data.confraternityType == 2) {
					$('.reg_num').attr('src',data.businessLicenceUrl);
					$('.big_reg_num').attr('src',data.businessLicenceUrl);
				} else if (data.confraternityType == 3) {
					$('.reg_num').attr('src',data.organizationCertificateUrl);
					$('.big_reg_num').attr('src',data.organizationCertificateUrl);
				}
			}
			
			$('.code_just').attr('src',data.frontIdentityUrl);
			$('.big_just').attr('src',data.frontIdentityUrl);
			$('.code_back').attr('src',data.oppositeIdentityUrl);
			$('.big_back').attr('src',data.oppositeIdentityUrl);
		});
	}
	
	function auditBtn() {//审核通过，不通过
		var $this = $(this);
		
		
		var data = {
			status : $this.attr('data-status'),
			confraternityId : $('#confr_id').text(),
		};
		console.log(data);
		$this.postBtn(CONFIG.addr.auditBrokerConfraternity, data, function (data) {
			if (data.status == 5) {
				S.alert('审核通过成功');
			} else if (data.status == 6) {
				S.alert('审核不通过成功');
			}
			$formBtn.click();
			$('#wp_modal_check').modal('hide');
		});
		
	}
	
	function modifyTrue(e) {
		e.stopPropagation();
		$wpModifyFrom.validate(function() {
			$wpModifyFrom.setForm(function() {
				S.alert('修改成功!');
				$('#wp_modal_modify').modal('hide');
				$formBtn.click();
			});
		});
		return false;
	}
	
	function uploadImg() {
		var $this = $(this);
		imgNum = $this.attr('data-img');
		$('#upload_img').click();
	}
	
	new uploadPreview({//本地预览上传banner图片
		 UpBtn : "upload_img",
		 DivShow : "upload_from",
		 ImgShow : "showload_img",
		 callback: function () {
			 setTimeout(function(){
				 var data = {};
				 $("#upload_from").ajaxSubmit({
					 type: "post", // 提交方式 get/post
					 url: CONFIG.addr.fileupload, // 需要提交的 url
					 data: data,
					 success: function(data) {
						 console.log(data);
						 if(data.code = '000'){
							 var r = data.data.downurl;
							 r = r.substring(0,r.indexOf("?"));
							 if (imgNum == 1) {
								 $('.code_just').attr('src',r);
								 $('input[name=frontIdentityUrl]').val(r);
							 } else if (imgNum == 2) {
								 $('.code_back').attr('src',r);
								 $('input[name=oppositeIdentityUrl]').val(r);
							 } else if (imgNum == 3) {
								 $('.reg_num').attr('src',r);
								 $('.big_reg_num').attr('src',r);
								 $('input[name=businessLicenceUrl]').val(r);
							 }
							 $("#upload_img").val('');
						 }
					 },
				 });
			 },500);
		 }
	 });
	
	// 页面JS初始化
	init();

});