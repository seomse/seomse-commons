package com.seomse.jdbc.example.objects;

import com.seomse.jdbc.annotation.*;

import java.io.File;

/**
 * @author macle
 */
@Table(name="attach_file")
public class AttachFile {

    @PrimaryKey(seq = 1)
    @Column(name = "file_no") @Sequence(name = "seq_attach_file")
    Long fileNo;

    @Column(name = "file_name")
    String fileName;

    @Column(name = "attach_file")
    byte[] attachFile;

}
