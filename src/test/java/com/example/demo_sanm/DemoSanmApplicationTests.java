package com.example.demo_sanm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chinatelecom.bo.flowchg.cardettyalter.CardBillStatuBo;
import com.chinatelecom.bo.flowchg.cardettyalter.MssCardResRelaUpdateBo;
import com.chinatelecom.bo.flowchg.cardettyalter.MssCardResRelaUpdateResponseBo;
import com.chinatelecom.grp.support.bo.comm.PageRowBounds;
import com.chinatelecom.grp.svc.flowchg.CardBillStatuQrySvc;
import com.chinatelecom.grp.svc.flowchg.MssCardResRelaUpateSvc;
import com.chinatelecom.prov.strongAssetOrder.checkObject.impl.StrongMssOverallPo;
import com.example.demo_sanm.contro.rubo.AssetsCardItems;
import com.example.demo_sanm.contro.rubo.PhysicalIds;
import com.example.demo_sanm.contro.rubo.StrongMssChekbo;
import com.sinovate.ngrms.comm.ptfcore.web.pagination.Page;
import com.sinovate.ngrms.gcbscsvr.mdl.bo.itspqry.GetResourceBo;
import com.sinovate.ngrms.gcbscsvr.mdl.vo.ResourceVo;
import com.sinovate.ngrms.gcbuapp.abl.gcodemix.svc.intqry.AblMarResoureIntQrySvc;
import com.sinovate.ngrms.gcbuapp.abl.gcodemix.svc.trafixast.AblAssetDetailSvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
public  class DemoSanmApplicationTests {


	@Autowired
	private AblAssetDetailSvc ablAssetDetailSvc;


	@Autowired
	private CardBillStatuQrySvc cardBillStatuQrySvc;


	@Autowired
	private MssCardResRelaUpateSvc mssCardResRelaUpateSvc;


	//String domain, List<MssCardResRelaUpdateBo> mssCardResRelaUpdateList
	@Test
	public  void mssCardResRelaUpateSvcTest(){
		String domain="31";
		List<MssCardResRelaUpdateBo > list = new ArrayList <>();
		MssCardResRelaUpdateBo  bo = new MssCardResRelaUpdateBo();
		bo.setBukrs("A009");
		bo.setAssetsCardCode("000101292087");
		list.add(bo);
		List<MssCardResRelaUpdateResponseBo>  list1=	mssCardResRelaUpateSvc.mssCardResRelaUpate(domain,list);
		System.out.println("========"+list1);
	}


	/**
	 * http://10.128.90.56:10217/gcodemix/ordflow/zgorderdetail?
	 * ordId=450000070000000000172795&posId=20GX0100228001&orderType=1001362&proInstId=711381&staffId=100000400000000000227524&wordItemIds=1502685&optStatus=1
	 */
	@Test
	public  void Test1666666(){
		BigInteger orderid=new BigInteger("450000070000000000172795");
		System.out.println( ablAssetDetailSvc.getPhyCode("20GX0100228001", orderid));
	}




	@Test
	public  void Test1(){
		StrongMssChekbo strongMssChekbo = new StrongMssChekbo();
		strongMssChekbo.setAppSystemId("100001");//应用系统ID
		strongMssChekbo.setUserId("sunwuk@123");
		strongMssChekbo.setPassword("123");//密码
		strongMssChekbo.setProcessCode("zc_tc_bf");//
		strongMssChekbo.setInstanceId("8a0c988364bdd340016541f3eb4438c3");//流程
		strongMssChekbo.setOrgCode("2713010115");//orgCode
		strongMssChekbo.setEndMark("1");//结束
		strongMssChekbo.setOrgName("网络运营部");//发起组织名称
		strongMssChekbo.setAppDate("2019-01-01");//申请日期 必填
		strongMssChekbo.setAccount("13020359@HE"); //发起人账号
		strongMssChekbo.setPrincipalName("张秀君");
		strongMssChekbo.setMoveOut("2713010115");//调出部门
		strongMssChekbo.setMoveIn("2713010039");
		strongMssChekbo.setSapCompanyName("B003"); //sapCompanyName
		strongMssChekbo.setReason("模拟数据");
		strongMssChekbo.setNote("备注2020.03.10");
		strongMssChekbo.setAssetsCardItems(getListAssetsCardItems());

		String json= JSON.toJSONString(strongMssChekbo);
		System.out.println(json);

		//   String json= JSON.toJSONString(chekbo);
		StrongMssChekbo chekbo1 = JSONArray.parseObject(json, StrongMssChekbo.class);

		StrongMssOverallPo mssOverallPo = JSONArray.parseObject(json, StrongMssOverallPo.class);
		System.out.println("A参:"+chekbo1);

		System.out.println("入参:"+mssOverallPo);

		/**
		 * 将
		 */
	}



	public  List<AssetsCardItems>  getListAssetsCardItems(){
		List<AssetsCardItems>  assetsCardItems = new ArrayList<>();
		AssetsCardItems  a1  = new AssetsCardItems();
		a1.setAssetsCardCode("000016797196");
		a1.setBukrs("A001");
		List <PhysicalIds> physicalIdsList  = new ArrayList <>();
		physicalIdsList.add(new PhysicalIds("131000000000004244617656"));
		physicalIdsList.add(new PhysicalIds("1310000000000042446176566"));


		a1.setPhysicalIds(physicalIdsList);


		AssetsCardItems  a2  = new AssetsCardItems();
		a2.setAssetsCardCode("000016797199");
		a2.setBukrs("A002");
		List <PhysicalIds> physicalIdsList1 = new ArrayList <>();
		physicalIdsList1.add(new PhysicalIds("131000000000004244617009"));
		a2.setPhysicalIds(physicalIdsList1);

		assetsCardItems.add(a2);
		assetsCardItems.add(a1);

		return  assetsCardItems;
	}



}
