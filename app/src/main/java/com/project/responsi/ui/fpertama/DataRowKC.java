package com.project.responsi.ui.fpertama;

public class DataRowKC {
    String M_TANGGAL;
    String M_SEMBUH;
    String M_MENINGGAL;
    String M_KONFIRMASI;
    String _id;

    void setData(String TANGGAL,
                 String SEMBUH,
                 String MENINGGAL,
                 String KONFIRMASI,
                 String id){
        M_TANGGAL               = TANGGAL;
        M_SEMBUH                = SEMBUH;
        M_MENINGGAL             = MENINGGAL;
        M_KONFIRMASI            = KONFIRMASI;
        _id                     = id;
    }
}
