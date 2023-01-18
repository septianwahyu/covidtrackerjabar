package com.project.responsi.ui.fkedua;

public class DataRowRC {
    String M_NAMA;
    String M_ALAMAT;
    String M_LONGITUDE;
    String M_LANGITUDE;
    String _id;

    void setData(String NAMA,
                 String ALAMAT,
                 String LONGITUDE,
                 String LANGITUDE,
                 String id){
        M_NAMA                  = NAMA;
        M_ALAMAT                = ALAMAT;
        M_LONGITUDE             = LONGITUDE;
        M_LANGITUDE             = LANGITUDE;
        _id                     = id;
    }
}
