package com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.customer;

import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.DataNotFoundException;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.Store;
import com.DazzleAdvancePaymentManagement.DazzleAdvancePaymentManagement.goods.Goods;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getList(){
        return this.customerRepository.findAll();
    }
    public Page<Customer> getPageList(int page){
        Pageable pageable = PageRequest.of(page,15);
        return this.customerRepository.findAll(pageable);
    }

    public void delete(Customer customer) {
        this.customerRepository.delete(customer);
    }
    public Page<Customer> getCustomerList(int page){
        List<Customer> customerList = this.customerRepository.findAll();
        Pageable pageable = PageRequest.of(page,15);
        // 페이지 기능 구현 필요
        int cusLen = customerList.toArray().length;
        if (cusLen > 1) {
            for (int i = 0; i < customerList.toArray().length; i++) {
                for (int j = i+1; j< customerList.toArray().length;j++ ){
                    if (customerList.get(i).getCustomerId() == customerList.get(j).getCustomerId()){
                        if(customerList.get(i).getCustomerOrderId()<customerList.get(j).getCustomerOrderId()){
                            customerList.remove(i);
                            j--;
                            i--;
                            break;
                        }
                        else if((customerList.get(i).getCustomerOrderId()>customerList.get(j).getCustomerOrderId())){
                            customerList.remove(j);
                            j--;
                        }
                    }
                    else if(customerList.get(i).getCustomerId() > customerList.get(j).getCustomerId()){
                        Collections.swap(customerList, i, j);
                    }
                }
            }
        }

        //이전달 기록들 0으로 "표기" (값변경 x)
        for (int i = 0; i < customerList.toArray().length; i++) {
            if(customerList.get(i).getCustomerDate().getYear() <= LocalDateTime.now().getYear()){
                if(customerList.get(i).getCustomerDate().getMonthValue()<LocalDateTime.now().getMonthValue()){
                    customerList.get(i).setCustomerMonthlyIn(0);
                    customerList.get(i).setCustomerMonthlyOut(0);
                }
            }
        }
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), customerList.size());
        Page<Customer> customerPage = new PageImpl<>(customerList.subList(start, end), pageable, customerList.size());
        return customerPage;
    }



    public List<Customer> getCustomerList(){
        List<Customer> customerList = this.customerRepository.findAll();
        // 페이지 기능 구현 필요
        int cusLen = customerList.toArray().length;
        if (cusLen > 1) {
            for (int i = 0; i < customerList.toArray().length; i++) {
                for (int j = i+1; j< customerList.toArray().length;j++ ){
                    if (customerList.get(i).getCustomerId() == customerList.get(j).getCustomerId()){
                        if(customerList.get(i).getCustomerOrderId()<customerList.get(j).getCustomerOrderId()){
                            customerList.remove(i);
                            j--;
                            i--;
                            break;
                        }
                        else if((customerList.get(i).getCustomerOrderId()>customerList.get(j).getCustomerOrderId())){
                            customerList.remove(j);
                            j--;
                        }
                    }
                    else if(customerList.get(i).getCustomerId() > customerList.get(j).getCustomerId()){
                        Collections.swap(customerList, i, j);
                    }
                }
            }
        }

        //이전달 기록들 0으로 "표기" (값변경 x)
        for (int i = 0; i < customerList.toArray().length; i++) {
            if(customerList.get(i).getCustomerDate().getYear() <= LocalDateTime.now().getYear()){
                if(customerList.get(i).getCustomerDate().getMonthValue()<LocalDateTime.now().getMonthValue()){
                    customerList.get(i).setCustomerMonthlyIn(0);
                    customerList.get(i).setCustomerMonthlyOut(0);
                }
            }
        }
        return customerList;
    }

    public List<Customer> getPersonalList(Integer id){
        List<Customer> customer = this.customerRepository.findByCustomerId(id);
        if (true){
            return customer;
        } else {
            throw new DataNotFoundException("customer not found");
        }
    }
    public Customer getCustomer(Integer id){
        Optional<Customer> customer = this.customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }else {
            throw new DataNotFoundException("customer not found");
        }
    }

    //고객 선수금 입력(결재)
    public void create(Customer customer, Integer changePaymentBalance){
        //customer는 고객의 가장 최근 기록임
        Customer customer1 = new Customer();
        customer1.setCustomerId(customer.getCustomerId());
        customer1.setCustomerName(customer.getCustomerName());
        customer1.setCustomerPaymentBalance(customer.getCustomerPaymentBalance()+changePaymentBalance);
        customer1.setCustomerDate(LocalDateTime.now());
        customer1.setStore(customer.getStore());
        customer1.setChangePaymentBalance(changePaymentBalance);
        customer1.setCustomerJob(customer.getCustomerJob());
        customer1.setCustomerMonthlyOut(customer.getCustomerMonthlyOut());
        if(LocalDateTime.now().getMonthValue()==customer.getCustomerDate().getMonthValue()){
            customer1.setCustomerMonthlyIn(customer.getCustomerMonthlyIn()+changePaymentBalance);
        }
        else {
            customer1.setCustomerMonthlyIn(changePaymentBalance);
        }
        this.customerRepository.save(customer1);
    }
    public void createNewCustomer(Integer customerId,String customerName,String customerJob,Integer customerPaymentBalance){
        Customer c = new Customer();
        c.setCustomerId(customerId);
        c.setCustomerName(customerName);
        c.setCustomerJob(customerJob);
        c.setCustomerPaymentBalance(customerPaymentBalance);
        c.setCustomerMonthlyIn(0);
        c.setCustomerMonthlyOut(0);
        c.setCustomerDate(LocalDateTime.now());
        this.customerRepository.save(c);
    }

    public void excelDownload(HttpServletResponse response,HttpServletRequest req,List<Customer> customerList) throws IOException {
        //        Workbook wb = new HSSFWorkbook();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(LocalDateTime.now().getMonthValue()+"월달 선수금 내역");
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
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,7));
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        defaultStyle.setWrapText(true);
        cell.setCellStyle(defaultStyle);
        cell.setCellValue("커피점 선수금 내역("+LocalDateTime.now().getYear()+"년 "+LocalDateTime.now().getMonthValue()+"월 분)");

        row = sheet.createRow(rowNum++);
        cell = row.createCell(7);
        cell.setCellValue("단위:원");

        for(int i =0; i<8;i++){
            sheet.setColumnWidth(i,3000);
        }
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("고객번호");
        cell = row.createCell(1);
        cell.setCellValue("성명");
        cell = row.createCell(2);
        cell.setCellValue("소속(신분)");
        cell = row.createCell(3);
        cell.setCellValue("이월금액");
        cell = row.createCell(4);
        cell.setCellValue("당월결재금액");
        cell = row.createCell(5);
        cell.setCellValue("당월매출금");
        cell = row.createCell(6);
        cell.setCellValue("선수금잔액");
        cell = row.createCell(7);
        cell.setCellValue("비고");

        // Body
        for (int i=0; i<customerList.toArray().length; i++) {
            row = sheet.createRow(rowNum++);
            Customer thisCustomer = customerList.get(i);
            int cIn = thisCustomer.getCustomerMonthlyIn();
            int cOut = thisCustomer.getCustomerMonthlyOut();
            cell = row.createCell(0);
            cell.setCellValue(thisCustomer.getCustomerId());
            cell = row.createCell(1);
            cell.setCellValue(thisCustomer.getCustomerName());
            cell = row.createCell(2);
            cell.setCellValue(thisCustomer.getCustomerJob());
            cell = row.createCell(3);
            cell.setCellValue(thisCustomer.getCustomerPaymentBalance()-cIn-cOut);
            cell = row.createCell(4);
            cell.setCellValue(thisCustomer.getCustomerMonthlyIn());
            cell = row.createCell(5);
            cell.setCellValue(thisCustomer.getCustomerMonthlyOut());
            cell = row.createCell(6);
            cell.setCellValue(thisCustomer.getCustomerPaymentBalance());
            cell = row.createCell(7);
            cell.setCellValue("");
        }

        // 컨텐츠 타입과 파일명 지정
        String fileNm = LocalDateTime.now().getYear()+"년"+LocalDateTime.now().getMonthValue()+"월 대즐 선수금 관리대장.xlsx";
        String browser = getBrowser(req);
        response.setContentType("ms-vnd/excel; charset=UTF-8");
//        response.setHeader("Content-Disposition", "attachment;filename=example.xls");
        response.setHeader("Content-Description", "file download");
        response.setHeader("Content-Disposition", "attachment; filename=\"".concat(getFileNm(browser, fileNm)).concat("\""));
        response.setHeader("Content-Transfer-Encoding", "binary");

        // Excel File Output
        wb.write(response.getOutputStream());
        wb.close();
    }

    public String getFileNm(String browser, String fileNm){
        String reFileNm = null;
        try {
            if (browser.equals("MSIE") ||
                    browser.equals("Trident") ||
                    browser.equals("Edge")) {
                reFileNm = URLEncoder.encode(fileNm, "UTF-8").replaceAll("\\+", "%20");
            } else {
                if(browser.equals("Chrome")){
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < fileNm.length(); i++) {
                        char c = fileNm.charAt(i);
                        if (c > '~') {
                            sb.append(URLEncoder.encode(Character.toString(c), "UTF-8"));
                        } else {
                            sb.append(c);
                        }
                    } reFileNm = sb.toString();
                } else{
                    reFileNm = new String(fileNm.getBytes("UTF-8"), "ISO-8859-1");
                }
                if(browser.equals("Safari") || browser.equals("Firefox"))
                    reFileNm = URLDecoder.decode(reFileNm);
            }
        } catch(Exception e){}
        return reFileNm;
    }

    public String getBrowser(HttpServletRequest req) {
        String userAgent = req.getHeader("User-Agent");
        if(userAgent.indexOf("MSIE") > -1
                || userAgent.indexOf("Trident") > -1 //IE11
                || userAgent.indexOf("Edge") > -1) {
            return "MSIE";
        } else if(userAgent.indexOf("Chrome") > -1) {
            return "Chrome";
        } else if(userAgent.indexOf("Opera") > -1) {
            return "Opera";
        } else if(userAgent.indexOf("Safari") > -1) {
            return "Safari";
        } else if(userAgent.indexOf("Firefox") > -1){
            return "Firefox";
        } else{
            return null;
        }
    }
}
