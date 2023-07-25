package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goodsLog;


import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.DataNotFoundException;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.Customer;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer.CustomerService;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.GoodsRepository;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.orders.Orders;
import jakarta.persistence.ManyToOne;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GoodsLogService {
    private final GoodsLogRepository goodsLogRepository;
    private final CustomerService customerService;
    private final GoodsRepository goodsRepository;

    public List<GoodsLog> getGoodsLogList(Integer id){
        List<GoodsLog> goodsLogList = this.goodsLogRepository.findByGoodsGoodsId(id);
        if (true){
            return goodsLogList;
        } else {
            throw new DataNotFoundException("logs not found");
        }
    }
    public List<GoodsLog> getGoodsLogCBB(){
        List<GoodsLog> goodsLogList = this.goodsLogRepository.findByGoodsGoodsCategoryAndGoodsLogTimeBetween("SIDE_COOKIE",LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue()-1,1,0,0).withDayOfMonth(1), LocalDateTime.now().withDayOfMonth(31));
        goodsLogList.addAll(this.goodsLogRepository.findByGoodsGoodsCategoryAndGoodsLogTimeBetween("SIDE_BOTTLE",LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue()-1,1,0,0).withDayOfMonth(1), LocalDateTime.now().withDayOfMonth(31)));
        goodsLogList.addAll(this.goodsLogRepository.findByGoodsGoodsCategoryAndGoodsLogTimeBetween("BAKERY",LocalDateTime.of(LocalDateTime.now().getYear(),LocalDateTime.now().getMonthValue()-1,1,0,0).withDayOfMonth(1), LocalDateTime.now().withDayOfMonth(31)));

        return goodsLogList;
    }

    public void createNewGoodsLog(String goodsLogWho, Integer goodsLogChange, Integer goodsLogNow, Goods goods) {
        GoodsLog gl = new GoodsLog();
        gl.setGoodsLogWho(goodsLogWho);
        gl.setGoodsLogChange(goodsLogChange);
        gl.setGoodsLogNow(goodsLogNow);
        gl.setGoodsLogTime(LocalDateTime.now());
        gl.setGoods(goods);
        this.goodsLogRepository.save(gl);

    }
    //엑셀파일 다운로드 기능
    public void excelDownloadCBB(HttpServletResponse response, HttpServletRequest req, List<Goods> goodsList) throws IOException {
        //        Workbook wb = new HSSFWorkbook();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(+(LocalDateTime.now().getMonthValue()-1)+"월 ~"+LocalDateTime.now().getMonthValue()+"월달 쿠키, 병음료, 베이커리 판매내역");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;
        CellStyle defaultStyle = wb.createCellStyle();

        // 테두리 설정
        defaultStyle.setBorderTop(BorderStyle.MEDIUM);
        defaultStyle.setBorderLeft(BorderStyle.MEDIUM);
        defaultStyle.setBorderRight(BorderStyle.MEDIUM);
        defaultStyle.setBorderBottom(BorderStyle.MEDIUM);

        // Header
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        defaultStyle.setWrapText(true);
        cell.setCellStyle(defaultStyle);
        cell.setCellValue("쿠키, 병음료, 베이커리 판매내역("+LocalDateTime.now().getYear()+"년 "+(LocalDateTime.now().getMonthValue()-1)+"월 ~"+LocalDateTime.now().getMonthValue()+"월 분)");
        for(int i =1; i<4;i++){
            row.createCell(i).setCellStyle(defaultStyle);
        }
        row = sheet.createRow(rowNum++);

        for(int i =0; i<4;i++){
            sheet.setColumnWidth(i,3500);
        }
        row = sheet.createRow(rowNum++);

        //body
        for (int i =0; i<goodsList.toArray().length; i++){
            List<GoodsLog> goodsLogList = goodsList.get(i).getGoodsLogList();
            row = sheet.createRow(rowNum);
            sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,1,2));rowNum++;
            cell = row.createCell(0);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue(goodsList.get(i).getGoodsCategory());
            cell = row.createCell(1);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue(goodsList.get(i).getGoodsName());
            cell = row.createCell(2);
            cell.setCellStyle(defaultStyle);
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue("주문자");
            cell = row.createCell(1);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue("주문일시");
            cell = row.createCell(2);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue("주문수량");

            int sum = 0;
            for (int j = 0; j<goodsLogList.toArray().length;j++){
                if(goodsLogList.get(j).getGoodsLogTime().getMonthValue()<LocalDateTime.now().getMonthValue()-1){continue;}
                if(goodsLogList.get(j).getGoodsLogTime().getYear()<LocalDateTime.now().getYear()){
                    if(goodsLogList.get(j).getGoodsLogTime().getMonthValue()<12){continue;}
                    if(LocalDateTime.now().getMonthValue()!=1){continue;}
                }
                row = sheet.createRow(rowNum++);
                cell = row.createCell(0);
                cell.setCellStyle(defaultStyle);
                cell.setCellValue(goodsLogList.get(j).getGoodsLogWho());    //주문자
                cell = row.createCell(1);
                cell.setCellStyle(defaultStyle);
                cell.setCellValue(goodsLogList.get(j).getGoodsLogTime().getYear()+"년"+goodsLogList.get(j).getGoodsLogTime().getMonthValue()+"월"+goodsLogList.get(j).getGoodsLogTime().getDayOfMonth()+"일");
                cell = row.createCell(2);
                cell.setCellStyle(defaultStyle);
                cell.setCellValue(-goodsLogList.get(j).getGoodsLogChange());
                sum -= goodsLogList.get(j).getGoodsLogChange();
            }
            row = sheet.createRow(rowNum);
            sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum,0,1));rowNum++;
            cell = row.createCell(0);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue("총주문 수량");
            cell = row.createCell(1);
            cell.setCellStyle(defaultStyle);
            cell = row.createCell(2);
            cell.setCellStyle(defaultStyle);
            cell.setCellValue(sum);
            rowNum++;

        }

        // 컨텐츠 타입과 파일명 지정
        String fileNm = LocalDateTime.now().getYear()+"년"+(LocalDateTime.now().getMonthValue()-1)+"월 ~"+LocalDateTime.now().getMonthValue()+"월 쿠키 병음료 베이커리 판매기록.xlsx";
        String browser = this.customerService.getBrowser(req);
        response.setContentType("ms-vnd/excel; charset=UTF-8");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Description", "file download");
        response.setHeader("Content-Disposition", "attachment; filename=\"".concat(this.customerService.getFileNm(browser, fileNm)).concat("\""));
        response.setHeader("Content-Transfer-Encoding", "binary");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }
}
