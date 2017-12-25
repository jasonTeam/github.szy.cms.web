(function() {
	'use strict';

	var CONFIG = {

		// /////////
		// 是否在本地 //
		// 如果是后端开发人员, 并且域名为localhost 请修改为false
		// true: 并且域名为localhost, ajax执行get操作, ajax地址执行"前端专用"
		// flase: ajax执行post操作, ajax地址执行"后端专用"
		// /////////
		local : false,

		// ajax请求方式
		ajaxType : 'post',
		selectType : [ '是', '否' ],
		auditStatus : [ '待审核', '审核中', '审核通过', '审核失败' ]
	};

	// 判断是否为localhost, 并且CONFIG.local为true
	console.log("2017-12-15-jason-测试: " +location.hostname);
	if (location.hostname === 'localhost' && CONFIG.local) { // true
		
		// 前端接口
		/*
		 * CONFIG.addr = { login : '/loginSubmit', // 登入 //verifyCode :
		 * '/code?t=' // 验证码 };
		 */

		// 本地ajax请求方式为post会报错
		// CONFIG.ajaxType = 'get';
	} else { // false

		// 后端接口
		CONFIG.addr = {
			login : '/login', // 登入
			// verifyCode : '/code?t=', // 验证码
			logout : '/loginOut', // 登出
			adminList : '/findAdminInfos', // 管理员列表
			adminCreate : '/saveAdminInfo', // 新增管理员
			adminEdit : '/updateAdmin', // 管理员修改保存
			adminDetail : '/adminInfoSet', // 管理员详情
			adminDelete : '/deleteAdminById', // 管理员删除
			roleAuthority : '/roleAuthority',// 角色管理
			saveRole : '/saveRole',// 新增角色
			deleteRole : '/deleteRole',// 删除角色
			roleEdit : '/roleEdit',// 加载需要修改的角色
			updateRole : '/updateRole',// 保存修改的角色
			auth : '/auth',// 加载需要修改的角色权限
			saveAuth : '/saveAuth',// 保存修改的角色权限
			prentMenu : '/prentMenu',// 查询所有父类菜单列表
			editMenu : '/editMenu',// 菜单编辑
			delMenu : '/delMenu',// 删除菜单
			subList : '/subList',// 获取所有的子菜单
			adminAuth : '/adminAuth',// 加载需要修改的用户权限
			saveAdminAuth : '/saveAdminAuth',// 保存修改的用户权限
			leftRight : '/leftRight',// 左边菜单查询权限
			menuSort : '/menuSort',// 菜单排序
			findAnchor : '/findAnchor',// 主播列表
			findAllOrder : '/findAllOrder',// 订单列表
			anchorLog : '/anchorLog',// 通过ID查找主播日志
			editAnchorLive : '/editAnchorLive',// 通过ID编辑主播
			extractCash : '/extractCash',// 提现
			cashExprot:'/cashExprot',//提现导出
			findUserGradeRule : '/findUserGradeRule',// 等级规则列表
			saveUserGradeRule : '/saveUserGradeRule',// 新增等级规则
			findGradeRule : '/findGradeRule',// 通过ID查找等级
			getGuessById : '/getGuessById',//通过id查找竞猜
			findSysConfig : '/findSysConfig',//查询系统配置
			fileupload : '/fileupload', //上传banner图片
			deleteTvBanner : '/deleteTvBanner', //删除banner
			findTvBannerById : '/findTvBannerById', //查询单张banner图
			findTvFeedbackById : '/findTvFeedbackById', //查询单个反馈详情
			updateTvFeedback : '/updateTvFeedback', //处理单个反馈
			findTvIllegalReportById : '/findTvIllegalReportById', //查询单个举报详情
			deleteLiveH5 : '/deleteLiveH5', //删除单个活动
			findLiveH5 : '/findLiveH5',//查询单个活动
			cancelAnchor : '/cancelAnchor',// 通过ID取消主播身份
			guessExprot:'/guessExprot',//竞猜导出
			deleteGoodsCategoryInfo : '/deleteGoodsCategoryInfo', //删除商品（类别）
			findGoodsCategoryInfo : '/findGoodsCategoryInfo',//查找单个商品（类别）
			deleteGoodsTypeInfo : '/deleteGoodsTypeInfo', //删除商品（类型）
			findGoodsTypeInfo : '/findGoodsTypeInfo', //查找单个商品（类型）
			findAllGoodsTypeInfo2 : '/findAllGoodsTypeInfo2', //查询所有的类型
			findOrderInfoById : '/findOrderInfoById',//查找单个公众号订单
			findGoodsInfoById : '/findGoodsInfoById',//查找单个公众号商品
			cancelAnchor : '/cancelAnchor',// 通过ID取消主播身份
			editSysConfig : '/updateSysConfig',//修改系统配置
			findAccount : '/findAccount',//账户信息(龙猫豆/龙猫币)
			userBanned : '/userBanned',//用户封禁/解禁
			updateIosPay : '/updateIosPay',//修改IOS支付配置
			androidLog : '/androidLog',//Android版本详情
			anchorMessageExprot : '/anchorMessageExprot',//主播信息导出
			exchangeTvBanner : '/exchangeTvBanner',//banner拖动交换位置
			getAllGuessSubject : 'getAllGuessSubject',//获取竞猜题库的所有数据
			deleteGuessSubject : 'deleteGuessSubject',//删除竞猜题库单条数据
			upOrDownGuessSubject : 'upOrDownGuessSubject',//上架或者下架竞猜题库单条数据
			findOneGuessSubject : 'findOneGuessSubject',//查询竞猜话题单条数据
			isOrNotGuess : 'isOrNotGuess',//是否允许主播开竞猜
			getRemark : 'getRemark',//获取直播间分享描述的remark
			updateLiveRoomRemark : 'updateLiveRoomRemark',//
			findGuessConfig : 'findGuessConfig',//
			getIntelGuessNum : 'getIntelGuessNum',//获取收割盘数
			findWinMachineRandom : 'findWinMachineRandom',//查询收割信息
			updateWinMachineRandom : 'updateWinMachineRandom',//修改收割开关信息
			GuessGuardInfoExprot : 'GuessGuardInfoExprot',//导出竞猜数据列表
			findTotalPalyGuessGuardInfo : 'findTotalPalyGuessGuardInfo',//查询竞猜列表累计总数据
			newAnchorMessageExprot : 'newAnchorMessageExprot',//主播信息导出（新）
			findGuessGuardByPlayInfo : 'findGuessGuardByPlayInfo',//查询单次开播竞猜记录
			findGuessGuardByGuessId : 'findGuessGuardByGuessId',//查询单次直播竞猜数据
			findTotalGuessGuardByPlayInfo : 'findTotalGuessGuardByPlayInfo',//查询竞猜详情页的头数据
			exchangeGuessSubject : 'exchangeGuessSubject',//拖动交换竞猜题库顺序
			upOrDownTvBanner : 'upOrDownTvBanner',//上下架banner
			deleteGuessAdventureById : 'deleteGuessAdventureById',//删除真心话大冒险
			findGuessAdventureById : 'findGuessAdventureById',//根据ID查询
			tableView : 'tableView',// 查看表字段属性
			tableDdl : 'tableDdl',// 查看创建表脚本按钮
			genCreate : 'genCreate',// 生成代码
			getBrokerConfraternityAndAccount : 'getBrokerConfraternityAndAccount',//根据ID查询公会信息 
			auditBrokerConfraternity : 'auditBrokerConfraternity',//审核通过不通过
			getUserAudit : 'getUserAudit',//通过ID查询用户基本信息
			editAnchor : 'editAnchor',//开通主播权限
			modifyUserIsGag : 'modifyUserIsGag',//用户禁言
			modifyUserIsClosure : 'modifyUserIsClosure',//用户封禁
			modifySuperAdministrator : 'modifySuperAdministrator',//是否设为app超管
			getAnchorMessageTotal : 'getAnchorMessageTotal',//查询直播卡路里
			getOneAnchorMessageTotal : 'getOneAnchorMessageTotal',//查询直播数据单条详情
			getExtractMessage : 'extract/getExtractMessage',//提现详情页的详情内容
			updateExtractDetail : 'extract/updateExtractDetail',//提现申请明细保存
			extractMessageExport: 'extract/extractMessageExport',//提现申请导出
			modifyRoomRobotNum : 'modifyRoomRobotNum',//直播列表编辑
			removeList : '/removeList',//直播列表移除
			findRobotRegInfo : '/robot/findRobotRegInfo',//查找机器人注册列表
			findRobot : '/robot/findRobot',//查找机器人列表
			robotReg : '/robot/robotReg',//创建注册机器人
			editRobotData : '/robot/editRobotData',//修改机器人注册资料
			findRobotConfig : '/robotConfig/findRobotConfig',//查询机器人配置
//			editRobotConfig : '/robot/editRobotConfig',//编辑机器人配置
			updateRobotConfig : '/robotConfig/updateRobotConfig',//修改机器人配置
			findActAssignmentById : 'findActAssignmentById',//根据id查找签到详情
			addRobotTalkInfo : '/robotTalkInfo/addRobotTalkInfo',//新增机器人对话
			deleteRobotTalkInfo : '/robotTalkInfo/deleteRobotTalkInfo',//删除机器人对话
			updateRobotTalkInfo : '/robotTalkInfo/updateRobotTalkInfo',//修改机器人对话
			findAllRobotTalkInfo : '/robotTalkInfo/findAllRobotTalkInfo',//查询所有机器人对话
			getOneRobotTalkInfo : '/robotTalkInfo/getOneRobotTalkInfo',//根据id查询机器人对话信息
			importRobotTalkInfo : '/robotTalkInfo/importRobotTalkInfo',//导入机器人对话信
            getAllConfName : 'getAllConfName',//公会列表
			updateActAssignment : 'updateActAssignment',//修改签到配置
			findActAssignmentMsg : 'findActAssignmentMsg',//查询任务配置信息
			updateActAssignmenta : 'updateActAssignmenta',//修改任务配置信息
			updateActAssignmentDetail : 'updateActAssignmentDetail',//修改任务阶段配置信息
			addNewAssignmentLevel : 'addNewAssignmentLevel',//添加任务阶段配置信息
			deleteActAssignmentDetail : 'deleteActAssignmentDetail',//删除任务阶段
			assignmentEnable : 'assignmentEnable',//任务配置上下架
			exchangeAssignmentSort : 'exchangeAssignmentSort',//任务配置拖动交换
			getGiftConfigList : 'giftConfig/getGiftConfigList',//查询礼物列表
			modifyIsEnable : 'giftConfig/modifyIsEnable',//礼物上下架
			saveGiftConfig : 'giftConfig/saveGiftConfig',//礼物新增或者修改
			exchangeGiftConfigInfo : 'giftConfig/exchangeGiftConfigInfo',//礼物配置拖动交换
			deleteGiftConfigInfo : 'giftConfig/deleteGiftConfigInfo',//删除下架礼物
			deleteRobotTalkInfo : 'robotTalkInfo/deleteRobotTalkInfo',//上下架机器人语句
			getOneRobotTalkInfo : 'robotTalkInfo/getOneRobotTalkInfo',//查询机器人语句
			
		};
	}

	window.CONFIG = CONFIG;

	// 模块化写法
	if (typeof define === 'function' && define.amd) {
		define([], function() {
			return CONFIG;
		});
	}
}());