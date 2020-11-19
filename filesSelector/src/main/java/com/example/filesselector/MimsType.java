package com.example.filesselector;

public enum MimsType {
    docType, docsType, pptType, pptxType,excelType,excelxType,textType,pdfType,zipType,archiveType,imageType,anyType;

    public String getType() {
        switch (this) {
            case docType:
                return "application/msword";
            case pptType:
                return "application/vnd.ms-powerpoint";
            case docsType:
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case pptxType:
                return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            case archiveType:
                return "application/vnd.android.package-archive";
            case excelType:
                return "application/vnd.ms-excel";
            case imageType:
                return "image/*";
            case pdfType:
                return "application/pdf";
            case excelxType:
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case textType:
                return "text/plain";
            case zipType:
                return "application/zip";
            default:
                return "";
        }
    }
}
