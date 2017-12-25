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

  //查询表单
  var $form = $('[data-form="search"]'); 
  
  //查询按钮
  var $formBtn = $form.find(':submit');
  //重置按钮
  var $formReset = $form.find(':reset');
  
  //查看创建表脚本层ID
  var $J_tableDdl = $('#J_tableDdl');
  
  //生成代码的弹层ID
  var $J_genCreate = $('#J_genCreate');
  
  /**
   * 页面初始化
   */
  function init () {

    // 删除表结构按钮
    $(document).on('click', '.J_tableDeleteBtn', tableDelete);
    // 查看表字段属性按钮
    $(document).on('click', '.J_tableViewBtn', tableView);
    // 查看创建表脚本按钮
    $(document).on('click', '.J_tableDdlBtn', tableDdl); 
    // 单个表生成代码按钮
    $(document).on('click', '.J_tableGenBtn', tableGen);
    //复制按钮
    $(document).on('click', '.J_tableCopyBtn', tableCopy);
    //配置完开始生成
    $(document).on('click', '.J_genCreate', genCreate);
    
	//重置刷新页面
	$formReset.click(function(){
		setTimeout(function(){
			$formBtn.click();
		},500);
	});
  }

  /**
   * 删除表结构
   */
  function tableDelete () {
    var $this = $(this);
    S.confirm('你确定要删除该表结构?', function () {
       S.alert('您无数据库表结构删除权限!');
    });
  }
  
  /**
   * 查看表字段属性
   */
  function tableView() {
		var $this = $(this);
		var tableName = $this.attr('data-table-name');
		
		if ($this.parent().parent().next().css('display') == 'none') {
			var data = {};
		    data.tableName = tableName;
		    $this.postBtn(CONFIG.addr.tableView, data, function(data) {
		    	$('#view_data_' + tableName).html(S.template(data, $('#view_temp').html()));
		    	$('.view_trclass').hide();
		    	$('#view_tr_' + tableName).show();
		    });
		} else {
			$('.view_trclass').hide();
		}
	}

  /**
   * 查看创建表脚本
   */
  function tableDdl() {
    var $this = $(this);
    $this.postBtn(CONFIG.addr.tableDdl, $this.data(), function (data) {
    	$J_tableDdl.find('[data-insert]').html('');
    	$J_tableDdl.insert(data);
    	$J_tableDdl.insert(data, 'name');
    	// 显示模态框
    	$J_tableDdl.modal('show');
    });
  }
  
  /**
   * 单个表生成代码
   */
  function tableGen() {
    var $this = $(this);
    var tableName = $this.attr('data-table-name');
    var comment = $this.attr('data-comment');
    $('#tableName').text(tableName);
    $('#comment').val(comment);
	// 显示模态框
    $J_genCreate.modal('show');
  }
  
  /**
   * 配置完开始生成代码
   */
  function genCreate() {
	  var $this = $(this);
	  var vals = '';
	  var length = 0;
      $('div.frame input[type=checkbox]:checked').each(function(){
          vals += $(this).val() + ';';
      });
      if ((length = vals.length) > 0){ 
    	  vals = vals.substr(0, length -1);
      }

	  var data = {};
	  data.vals = vals;
	  data.tableName = $('#tableName').text();
	  data.comment = $('#comment').val();
	  
    S.confirm('你确定要对这个表生成代码?', function () {
        $this.postBtn(CONFIG.addr.genCreate, data, function (data) {
      	  // 显示模态框
          $J_genCreate.modal('hide');
          S.alert('代码已生成，请关闭服务后刷新项目!');
          $form.trigger('Storm.search.get');
        })
      });
  }
  
  /**
   * 代码复制
   */
  function tableCopy() {
	  var ddl = document.getElementById("ddl");
	  // 选择对象
	  ddl.select();
	  // 执行浏览器复制命令
	  document.execCommand("Copy"); 
	  S.alert('代码已复制!');
  }
  
  // 页面JS初始化
  init();

});