package com.batch.BatchProcessing.config;

import com.batch.BatchProcessing.dto.PinCodeBulkDto;
import com.batch.BatchProcessing.entity.ErrorTable;
import com.batch.BatchProcessing.repository.ErrorRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
@Component
@StepScope
public class   JobReader implements ItemReader<PinCodeBulkDto> {
    private Iterator<Row>  rowIterator;

    @Autowired
    private ErrorRepository errorRepository;



    public JobReader(@Value("#{jobParameters['filePath']}") String filePath) throws IOException {
        InputStream inputStream=new FileInputStream(filePath);
        Workbook workbook=new XSSFWorkbook(inputStream);
        Sheet sheet=workbook.getSheetAt(0);
        this.rowIterator=sheet.iterator();
        rowIterator.next();
    }

    @Override
    public PinCodeBulkDto read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        while (rowIterator.hasNext()){
            Row row=rowIterator.next();
            int rownum=row.getRowNum();

            try{
                Cell pinCell=row.getCell(0);
                Cell stateCell=row.getCell(1);
                Cell CityCell=row.getCell(2);


                if (pinCell==null || stateCell==null || CityCell==null){
                    ErrorTable errorTable = new ErrorTable();
                    errorTable.setRowNumber((long) rownum);
                    errorTable.setMessage("Required cell Missing");
                    errorRepository.save(errorTable);
                    continue;
                }
                String stateName=stateCell.getStringCellValue();
                String cityname=CityCell.getStringCellValue();
                Long pincode= (long) pinCell.getNumericCellValue();

                return new PinCodeBulkDto(pincode,stateName,cityname);

            }
            catch (Exception exception){
                System.err.println("Error reading row " + rownum + ": " + exception.getMessage());
                continue;
            }

        }
        return null;

    }
}
