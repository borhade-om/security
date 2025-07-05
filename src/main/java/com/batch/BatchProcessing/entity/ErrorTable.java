package com.batch.BatchProcessing.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "error_table")
public class ErrorTable {
    @Id
    @Column(name = "error_id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
   private Long  errorId ;

    @Column(name = "message")
    private String message;

    @Column(name = "row_number")
    private Long rowNumber;

    public ErrorTable() {
    }

    public ErrorTable(Long errorId, String message, Long rowNumber) {
        this.errorId = errorId;
        this.message = message;
        this.rowNumber = rowNumber;
    }

    public Long getErrorId() {
        return errorId;
    }

    public void setErrorId(Long errorId) {
        this.errorId = errorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Long rowNumber) {
        this.rowNumber = rowNumber;
    }
}
