package com.phh.learnDemo;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.io.Files;


/**
 * 
 *处理execl文件 然后生成sql脚本
 *@author: phh
 *@date：2017年2月22日
 *
 */
public class HandlerExcelFile {
	public static void main(String[] args) throws Exception{
		//首先确认公司档案公司的相关信息
		int guestArchiveID = 2;
		String guestArchiveName = "大都市国际旅行社有限公司";
		int sellerID = 285;
		String sellerName = "邱科文";
		int chainID = 5353;
		int ItemID = 2255;
		List<String> headers = new ArrayList<String>();
		headers.add("代码");
		headers.add("业务日期");
		headers.add("项目名称");
		headers.add("房号");
		headers.add("客人姓名");
		headers.add("Tag");
		headers.add("消费");
		headers.add("已核销");
		headers.add("付款");
		headers.add("已核销");
		headers.add("余额");
		headers.add("单号");
		headers.add("摘要");
		headers.add("转账");
		headers.add("用户名");
		headers.add("班别");
		headers.add("入账时间");
		headers.add("结账单号");
		headers.add("营业日期");
//		代码	业务日期	项目名称	房号	客人姓名	Tag	消费	已核销	付款	已核销	余额	单号	摘要	转账	用户名	班别	入账时间	结账单号	营业日期
		String exportName = "F:/yinshozhangexcel/大都市0228.xlsx";
		List<Map<String, String>> fileExcel = ExcelReader.read(new File(exportName), headers);
		System.out.println(fileExcel);
		if(fileExcel != null && fileExcel.size() > 0){
			System.out.println("解析execl名字："+exportName+",总共解析数据:"+fileExcel.size());
			StringBuffer sb = new StringBuffer();
			sb.append(" insert into ac_receiveAccount(folioID,transID,amount,roomNo,guestName,accDate,sellerID, "
					+ " receiveAccountType,guestArchiveID,verificationFlag,receiveAccountCreateTime,"
					+ " receiveAccountRemarks,sellerName,guestArchiveName,chainID,voidFlag,ItemID) values \r\n");
			for (Map<String, String> map : fileExcel) {
				sb.append("( ");
				String folioID = map.get("结账单号");
				String transID = map.get("转账");
				String amount = map.get("消费").replace(",", "");
				String blance = map.get("余额").replace(",", "");
				if(!amount.equals(blance)){
					throw new Exception("结账单号："+folioID+",中的消费金额跟余额不相等，请确认");
				}
				String roomNo = map.get("房号");
				String guestName = map.get("客人姓名"); //客人需要加密
				String decryptName  = CryptUtil.encrypt(guestName);
				String accDate = map.get("营业日期");
//				String sellerID = ""; //销售员
				int receiveAccountType = 1;
//				int guestArchiveID = 
				int verificationFlag = 1;
//				int receiveAccountOprt = 1 ; //操作员
				String receiveAccountCreateTime = map.get("入账时间");
				String receiveAccountRemarks = map.get("摘要");
				int voidFlag = 0;
				sb.append(folioID+",").append(transID+",").append(amount+",").append("'"+roomNo+"',")
				  .append("'"+decryptName+"',").append("'"+accDate+"',").append(sellerID+",")
				  .append(receiveAccountType+",").append(guestArchiveID+",").append(verificationFlag+",")
				  .append("'"+receiveAccountCreateTime+"',").append("'"+receiveAccountRemarks+"',")
				  .append("'"+sellerName+"',").append("'"+guestArchiveName+"',").append(chainID+",")
				  .append(voidFlag+",").append(ItemID+"),\r\n");
			}
			
			String fileName = "F:/yinshozhangexcel/大都市0228ReceiveAccount.sql";
			Files.write(sb.substring(0, sb.length()-3)+";", new File(fileName), Charset.forName("UTF-8"));
			System.out.println("sql已经写到文件 " + fileName);
		}
	}
	
}
