package com.example.checklist;

import com.example.checkmethod.BasicDuplicateKeyChecker;
import com.example.checkmethod.BasicFileChecker;
import com.example.checkmethod.Checker;
import com.example.checkmethod.FirstLineContentChecker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
    * 會有兩個檔案 - .txt 和 .flg
    * txt檔案大概會長這樣 - <table_name>yyyymmdd.txt, yyyymmdd為當天日期 -1 天
    * flg檔案大概會長這樣 - <table_name>.flg
    *
    * 首先要檢查兩個檔案是否都存在，且都不為空檔
    * 然後要檢查flg檔案的第一行是否為"<table_name>yyyymmdd.txt"
    * txt檔案會使用","作為分隔符號，檢查第一個欄位是否有重複
 */
public class StandardBatchChecklist extends AbstractFileChecklist{

    public StandardBatchChecklist(String tableName, String baseDir) {
        super();
        String txtFileName = generateTxtFileName(tableName);
        List<Checker> checkers = new ArrayList<> (List.of(
                new BasicFileChecker (baseDir + txtFileName),
                new BasicFileChecker(baseDir + tableName + ".flg"),
                new FirstLineContentChecker (baseDir + tableName + ".flg", txtFileName),
                new BasicDuplicateKeyChecker (baseDir + tableName + ".txt", 0, ",")
        ));
        setBatchFileChecker(checkers);
    }

    private String generateTxtFileName(String tableName) {
        String yyyymmdd = LocalDate.now().minusDays(1)
                .format (java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd"));
        return tableName + yyyymmdd + ".txt";
    }
}
